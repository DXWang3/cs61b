# CSM Berkeley 61B

## For CSM Students

Welcome to the CSM 61B repo! Click a `week` directory above to see CSM material from that week. You'll find links to the worksheets and solved worksheets.

In addition, we try to make sure all the code we show you really works! Feel free to clone this repo and play around with the `.java` files you find -- once you fill in the answers (or copy the answers from the files in the `code-soln` directory), you should be able to compile and successfully run the code.

### Related links

- [CS 61B Website](http://berkeley-cs61b.github.io/public_html/)
- [CSM Facebook](https://www.facebook.com/BerkeleyCSM)


## Technical information for contributors

### About docmaker

In each subdirectory, you'll find various `.md` and `.pdf` files with nearly the same content. These are all generated from the `readme.src.md` file and various `.java` files in `code` and `code-soln`.

A tool called **docmaker**, which exists in `/common/docmaker.py`, is used to help convert `readme.src.md` into the other files. Here are some workflows currently in use:

    readme.src.md  --(docmaker)-->  readme.md
    readme.src.md  --(docmaker)-->  csm61b-weekXX.pdf.md  --(pandoc)-->  csm61b-weekXX.pdf

You'll need this stuff installed in order to `make docs`:
- [Pandoc], a popular document conversion tool (written by [one of our philosphy professors]!). On Mac, `brew install pandoc` should do the trick.
- TeX. Pandoc relies on `pdflatex` to generate the actual PDFs. On Mac, the easiest way to install TeX is to install [Homebrew Cask] and then `brew cask install basictex`. (`brew cask install mactex` instead if you also want a whole suite of TeX apps installed; note that this install will take significantly longer to download.)
- If you're on Windows, you'll also need to make sure you have Make and Python 2 installed.

[Pandoc]: http://johnmacfarlane.net/pandoc/
[one of our philosphy professors]: http://johnmacfarlane.net/
[Homebrew Cask]: http://caskroom.io/


### About the `gh-pages` branch

This repo has two branches, `master` and `gh-pages`. Publishing to `gh-pages` makes all the files available at [csmberkeley.github.io/cs61b](https://csmberkeley.github.io/cs61b/). `gh-pages` should always be a mirror of `master`. Here's how to make your `git push` respect this policy:

```shell
git config --replace-all remote.origin.push  refs/heads/master:refs/heads/master
git config --add         remote.origin.push +refs/heads/master:refs/heads/gh-pages
```

You can also run these commands with: `common/configure-git`


## About CSM

We aim to develop a community of mentors in Computer Science and provide additional academic support to students at UC Berkeley.
