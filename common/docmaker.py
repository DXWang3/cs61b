#!/usr/bin/env python
# Docmaker turns `---`-macros in Markdown into pretty things.
# Created Jan. 2015 by Sean Zhu for CSM Berkeley.
# To report bugs, file a GitHub issue or contact sean.zhu@berkeley.edu.

ABSOLUTE_URL_PREFIX = 'http://csmberkeley.github.io/cs61b/'
HEADER_PREFIX = """# CSM Berkeley 61B, Spring 2015: Week"""
PDF_PROB_LINK_TEXT = 'Printable PDF'
PDF_SOLN_LINK_TEXT = 'Solutions PDF'

from subprocess import check_output
PDF_PROB, PDF_SOLN, MD_PROB, MD_SOLN = check_output(['make', '-s', 'echo']).splitlines()



def make_soln_header_suffix(env):
    if 'soln' in env.mode:
        if 'md' in env.mode:
            return " Solutions"
        elif 'tex' in env.mode:
            return " \\textcolor{answer_frame_color}{Solutions}"
    return ''


def make_tex_preamble(env):
    return (
        '\\lhead{CSM Berkeley 61B, Spring 2015}\n' +
        '\\rhead{Week %d%s}\n' % (get_weeknum(), make_soln_header_suffix(env) if 'soln' in env.mode else '')
    )


def make_absolute_url(path3):
    from os.path import join, basename
    from os import getcwd
    path1 = ABSOLUTE_URL_PREFIX
    path2 = basename(getcwd())
    return join(path1, path2, path3)


def readfile(fname):
    with open(fname) as f:
        return f.read()


class FileSegment(object):
    def __init__(self, lang, soln):
        self.text = ''
        self.lang = lang
        self.soln = soln

    def append(self, new_text):
        self.text += new_text

    def chomp(self):
        if self.text[-1] == '\n':
            self.text = self.text[:-1]
            return '\n'
        else:
            return ''

    def to_md(self, env):
        result = ''
        if 'tex' in env.mode:
            result += '\\vspace{-0.5em}\n'
        if self.soln and 'tex' in env.mode:
            result += '\n\\answerbegin\n\n'
        result += cat('```', self.lang, '\n', self.text, '```', '\n')
        if self.soln and 'tex' in env.mode:
            result += '\n\\vspace{-0.6em}\\answerend\n\n'
        return result

def file_segments(file, lang, opts, env):
    from re import match
    inside_method = False
    segments = [FileSegment(lang, False)]
    for line in file:
        if '-- -- --' in line:
            yield segments
            segments = [FileSegment(lang, False)]
            continue
        if '// [ //' in line:
            segments.append(FileSegment(lang, 'tex' in env.mode))
            continue
        if '// ] //' in line:
            segments.append(FileSegment(lang, False))
            continue
        if inside_method:
            m = match(r'^    (\}\s*)$', line)
            if m:
                segments[-1].append(' ... ' + m.group(1))
                inside_method = False
            continue
        elif 'outline' in opts:
            m = match(r'^(    \w.*\{)\s*$', line)
            if m:
                segments[-1].append(m.group(1))
                inside_method = True
                continue

        segments[-1].append(line)
    yield segments


def formatted_file_segments(file, lang, opts, env):
    for segments in file_segments(file, lang, opts, env):
        yield cat(*(segment.to_md(env) for segment in segments))


def mdlink(text, url):
    return cat('[', text, '](', url, ')')


def get_weeknum():
    from re import match
    from os import getcwd
    from os.path import basename
    n = match(r'.*?(\d+)\s*$', basename(getcwd()))
    return int(n.group(1))


def cat(*args):
    return ''.join(map(str, args))


def out(*args):
    from sys import stdout
    stdout.write(cat(*args))


rules = []


def add_rule(directive=None, nargs=0):
    if directive is None:
        regex = r'(?P<opts>)'
    else:
        regex = cat(
            r'^---\s+',
            directive,
            r'\s+(.+?)' * nargs,
            r'(?:\s+\((?P<opts>.*?)\))?',
            r'\s*\n$')

    def decorator(f):
        rules.append((regex, f))
        return f
    return decorator


class Env(object):
    pass


@add_rule('begin onlyfor', 1)
def rule_begin_onlyfor(line, m, opts, env):
    env.required_mode = parse_mode(m.group(1))
    return True


@add_rule('end onlyfor')
def rule_end_onlyfor(line, m, opts, env):
    env.required_mode = None
    return True


@add_rule('begin soln')
def rule_begin_soln(line, m, opts, env):
    env.required_mode = parse_mode('soln')
    if parse_mode('soln,tex') <= env.mode:
        out('\n\\vspace{-0.5em}\\answerbegin\\vspace{-0.5em}\n\n')
    return True


@add_rule('end soln')
def rule_end_soln(line, m, opts, env):
    if parse_mode('soln,tex') <= env.mode:
        out('\n\\answerend\n\n')
    env.required_mode = None
    return True


@add_rule()
def rule_inside_onlyfor(line, m, opts, env):
    if env.required_mode and not (env.required_mode <= env.mode):
        return True


@add_rule('soln', 1)
def rule_soln(line, m, opts, env):
    target_line = m.group(1)
    if 'soln' in env.mode:
        if 'tex' in env.mode:
            out('\n\\vspace{-0.5em}\\answerbegin\\vspace{-0.5em}\n\n')
        out(target_line, '\n')
        if 'tex' in env.mode:
            out('\n\\answerend\n\n')
    return True


@add_rule('onlyfor', 2)
def rule_onlyfor(line, m, opts, env):
    required_mode = parse_mode(m.group(1))
    target_line = m.group(2)
    if required_mode <= env.mode:
        if target_line == 'yield':
            out(next(env.yield_segment))
        else:
            out(target_line, '\n')
    return True


@add_rule('newpage')
def rule_newpage(line, m, opts, env):
    if 'tex' in env.mode:
        out('\n\\newpage\n')
    return True


@add_rule('newpage soln')
def rule_newpage_soln(line, m, opts, env):
    if parse_mode('tex,soln') <= env.mode:
        out('\n\\newpage\n')
    return True


@add_rule('header')
def rule_header(line, m, opts, env):
    weeknum = get_weeknum()

    if 'md' in env.mode:
        out('**', mdlink(PDF_PROB_LINK_TEXT, make_absolute_url(PDF_PROB)), '**')
        out(' &middot; ')
        out('**', mdlink(PDF_SOLN_LINK_TEXT, make_absolute_url(PDF_SOLN)), '**')
        out('\n\n')

    out(HEADER_PREFIX, ' ', weeknum)
    if 'soln' in env.mode:
        out(make_soln_header_suffix(env))
    out('\n\n')
    return True


@add_rule('include', 1)
def rule_include(line, m, opts, env):
    fname = m.group(1)
    out(readfile(fname))
    return True


def mdcode(text):
    return cat('`', text, '`')


def mdurl(fpath, env):
    if 'tex' in env.mode:
        return make_absolute_url(fpath)
    else:
        return fpath


@add_rule('code', 2)
def rule_code(line, m, opts, env):
    from os.path import exists, join
    lang = m.group(1)
    fname = m.group(2)
    prob_fpath = join('code', fname)
    soln_fpath = join('code-soln', fname)
    if 'soln' in env.mode and exists(soln_fpath):
        prob_fpath = soln_fpath
        soln_fpath = None
    out('File: ')
    out(mdlink(mdcode(fname), mdurl(prob_fpath, env)))
    if parse_mode('md,prob') <= env.mode and soln_fpath and exists(soln_fpath):
        out(' &middot; ')
        out(mdlink('solution', mdurl(soln_fpath, env)))
    out('\n\n')
    env.yield_segment = formatted_file_segments(open(prob_fpath), lang, opts, env)
    out(next(env.yield_segment))
    return True


@add_rule('yield')
def rule_yield(line, m, opts, env):
    out(next(env.yield_segment))
    return True


def parse_opts(opts_string):
    from re import split
    if opts_string is None:
        return []
    return split(r',\s*', opts_string)


def parse_mode(mode_string):
    from re import split
    return set(split(r',\s*', mode_string))


def main(mode_string):
    from sys import stdin
    from re import match
    # from os import chdir
    # chdir('..')

    env = Env()
    env.yield_segment = None
    env.mode = parse_mode(mode_string)
    env.required_mode = None

    if 'tex' in env.mode:
        out(make_tex_preamble(env), '\n')
    should_continue = False
    for line in stdin:
        for (regex, f) in rules:
            m = match(regex, line)
            if m and f(line, m, parse_opts(m.group('opts')), env):
                should_continue = True
                break
        if should_continue:
            should_continue = False
            continue
        out(line)


##


class ProgramError(Exception):
    pass


def run():
    from sys import argv, stderr
    try:
        exit(main(*argv[1:]) or 0)
    except ProgramError, exc:
        print >> stderr, exc
    except TypeError, exc:
        if exc.message.startswith("main() takes"):
            print >> stderr, exc
        else:
            raise

if __name__ == '__main__':
    run()
