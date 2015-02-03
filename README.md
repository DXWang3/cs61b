# CSM Berkeley 61B


## Related links

- [CS 61B Website](http://berkeley-cs61b.github.io/public_html/)
- [CSM Facebook](https://www.facebook.com/BerkeleyCSM)


## Technical information for contributors

### About readmes

Each subdirectory will contain a `readme.md` and `readme.pdf` that are automatically generated from the `readme.src/readme.src.md` file. Generate the files with:

```shell
cd readme.src
make      # to make just readme.md
# or
make all  # to make readme.md and readme.pdf
```

`readme.pdf` is not included in the default target because generating a PDF from the same source twice will result in nonidentical PDFs, and Git will think changes were made.

`readme.md` is lowercase to make it easy to find among a sea of uppercamelcased `.java` files.

If you've modified only references files and not `readme.src.md`, you can force a remake by updating the modified time of `readme.src.md`:

```shell
touch readme.src.md
make all
```


### About the `gh-pages` branch

This repo has two branches, `master` and `gh-pages`. Publishing to `gh-pages` makes all the files available at [csmberkeley.github.io/cs61b](https://csmberkeley.github.io/cs61b/). `gh-pages` should always be a mirror of `master`. Here's how to make your `git push` respect this policy:

```shell
git config --replace-all remote.origin.push  refs/heads/master:refs/heads/master
git config --add         remote.origin.push +refs/heads/master:refs/heads/gh-pages
```

You can also run these commands with: `common/configure-git`


## About CSM

We aim to develop a community of mentors in Computer Science and provide additional academic support to students at UC Berkeley.
