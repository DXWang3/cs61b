#!/usr/bin/env python

ABSOLUTE_URL_PREFIX = 'http://csmberkeley.github.io/cs61b/'
HEADER_PREFIX = '''# CSM Berkeley 61B, Spring 2015: Week'''
PDF_LINKNAME = 'Printable PDF'


def make_absolute_url(path3):
    from os.path import join, split, dirname, realpath
    path1 = ABSOLUTE_URL_PREFIX
    path2 = split(dirname(realpath(__file__)))[1]
    return join(path1, path2, path3)


def main(mode='md'):
    from sys import stdin, stdout
    from re import match
    from os import chdir
    from os.path import dirname, realpath, exists, join


    chdir('..')


    for line in stdin:
        m = match(r'---\s+header\s+', line)
        if m:
            n = match(r'.*?(\d+)\s*$', dirname(realpath(__file__)))
            weeknum = int(n.group(1))

            if mode != 'tex':
                pdf_url = make_absolute_url('readme.pdf')
                pdf_link = PDF_LINKNAME
                stdout.write('''[[%(pdf_link)s](%(pdf_url)s)]\n\n''' % locals())

            stdout.write(HEADER_PREFIX + ''' %(weeknum)s\n\n''' % locals())
            continue

        m = match(r'---\s+include\s+(.*?)\s+', line)
        if m:
            fname = m.group(1)
            stdout.write(open(fname).read())
            continue

        m = match(r'---\s+code\s+(.*?)\s+(.*?)\s+', line)
        if m:
            lang = m.group(1)
            fname = m.group(2)
            soln = ''
            if mode == 'tex':
                url = make_absolute_url(fname)
            else:
                url = fname
                soln_fname = join('soln', fname)
                if exists(soln_fname):
                    soln = ''' &middot; [`%(soln_fname)s`](%(soln_fname)s)''' % locals()
            stdout.write('''File: [`%(fname)s`](%(url)s)%(soln)s\n\n''' % locals())
            stdout.write('''```%(lang)s\n''' % locals())
            stdout.write(open(fname).read())
            stdout.write('''```\n''')
            continue

        m = match(r'---\s+onlyin\s+(.*?)\s+(.*)$', line)
        if m:
            target_mode = m.group(1)
            target_line = m.group(2)
            if mode == target_mode:
                stdout.write(target_line)
                stdout.write('\n')
            continue

        stdout.write(line)


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
