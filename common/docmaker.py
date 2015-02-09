#!/usr/bin/env python

ABSOLUTE_URL_PREFIX = 'http://csmberkeley.github.io/cs61b/'
HEADER_PREFIX = """# CSM Berkeley 61B, Spring 2015: Week"""
PDF_LINK_TEXT = 'Printable PDF'


PREAMBLE = r'''
\definecolor{answer_frame_color}{rgb}{0.8, 0.0, 0.0}
\definecolor{answer_color}{rgb}{0.3, 0.0, 0.0}

\newsavebox{\ansbox}
\newenvironment{answer}
{\color{answer_frame_color}\begin{lrbox}{\ansbox}\begin{minipage}{\linewidth}
\setlength{\parskip}{0.8em} % \vspace{-0.8em}
\color{answer_color}
\vspace{0.2em}
}
{
\vspace{0.2em}
\end{minipage}\end{lrbox}\fbox{\usebox{\ansbox}}}
\newcommand{\answerbegin}{\begin{answer}}
\newcommand{\answerend}{\end{answer}}
'''


def make_absolute_url(path3):
    from os.path import join, split, dirname, realpath
    path1 = ABSOLUTE_URL_PREFIX
    path2 = split(dirname(realpath(__file__)))[1]
    return join(path1, path2, path3)


def readfile(fname):
    with open(fname) as f:
        return f.read()


def file_segments(file, lang, no_method_bodies):
    from re import match
    inside_method = False
    text = ''
    for line in file:
        if '-- -- --' in line:
            yield text
            text = ''
            continue
        if inside_method:
            m = match(r'^    (\}\s*)$', line)
            if m:
                text += ' ... ' + m.group(1)
                inside_method = False
            continue
        elif no_method_bodies:
            m = match(r'^(    \w.*\{)\s*$', line)
            if m:
                text += m.group(1)
                inside_method = True
                continue

        text += line
    yield text


def formatted_file_segments(file, lang, no_method_bodies):
    for segment in file_segments(file, lang, no_method_bodies):
        yield cat('```', lang, '\n', segment, '```', '\n')


def mdlink(text, url):
    return cat('[', text, '](', url, ')')


def mdcodelink(text, url):
    return cat('[`', text, '`](', url, ')')


def get_weeknum():
    from re import match
    from os.path import dirname, realpath
    n = match(r'.*?(\d+)\s*$', dirname(realpath(__file__)))
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
    env.onlyfor_mode = m.group(1)
    # if env.onlyfor_mode == 'soln':
    #     out('\\answerbegin\n')
    return True


@add_rule('end onlyfor')
def rule_end_onlyfor(line, m, opts, env):
    # if env.onlyfor_mode == 'soln':
    #     out('\\answerend\n')
    env.onlyfor_mode = None
    return True


@add_rule()
def rule_inside_onlyfor(line, m, opts, env):
    if env.onlyfor_mode is not None and env.onlyfor_mode not in env.mode:
        return True


@add_rule('onlyfor', 2)
def rule_onlyfor(line, m, opts, env):
    target_mode = m.group(1)
    target_line = m.group(2)
    if target_mode in env.mode:
        out(target_line, '\n')
    return True


@add_rule('header')
def rule_header(line, m, opts, env):
    weeknum = get_weeknum()

    if not 'tex' in env.mode:
        pdf_url = make_absolute_url('readme.pdf')
        out('**', mdlink(PDF_LINK_TEXT, pdf_url), '**', '\n\n')

    out(HEADER_PREFIX, ' ', weeknum, '\n\n')
    return True


@add_rule('include', 1)
def rule_include(line, m, opts, env):
    fname = m.group(1)
    out(readfile(fname))
    return True


@add_rule('code', 2)
def rule_code(line, m, opts, env):
    from os.path import exists, join

    lang = m.group(1)
    fname = m.group(2)
    no_method_bodies = 'outline' in opts
    soln = ''
    if 'tex' in env.mode:
        url = make_absolute_url(fname)
    else:
        url = fname
        soln_fname = join('soln', fname)
        if exists(soln_fname):
            soln = cat(' &middot; ', mdcodelink(soln_fname, soln_fname))
    out('File: ', mdcodelink(fname, url), soln, '\n\n')
    env.yield_segment = formatted_file_segments(open(fname), lang, no_method_bodies)
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
    return split(r',\s*', mode_string)


def main(mode_string):
    from sys import stdin
    from re import match
    from os import chdir
    chdir('..')

    env = Env()
    env.yield_segment = None
    env.mode = parse_mode(mode_string)
    env.onlyfor_mode = None

    out(PREAMBLE, '\n')
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
