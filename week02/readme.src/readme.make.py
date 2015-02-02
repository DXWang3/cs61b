#!/usr/bin/env python

def main():
    from sys import stdin, stdout
    from re import match
    from os import chdir


    chdir('..')


    for line in stdin:
        m = match(r'---\s+include\s+(.*?)\s+', line)
        if m:
            fname = m.group(1)
            stdout.write(open(fname).read())
            continue

        m = match(r'---\s+code\s+(.*?)\s+(.*?)\s+', line)
        if m:
            lang = m.group(1)
            fname = m.group(2)
            stdout.write('''File: [`%(fname)s`](%(fname)s)\n\n''' % locals())
            stdout.write('''```%(lang)s\n''' % locals())
            stdout.write(open(fname).read())
            stdout.write('''```\n''')
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
