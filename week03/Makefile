WEEK := $(shell basename $(shell pwd))
PDF_PROB := csm61b-$(WEEK).pdf
PDF_SOLN := csm61b-$(WEEK)-soln.pdf
MD_PROB := readme.md
MD_SOLN = readme-soln.md
ALL := $(PDF_PROB) $(PDF_SOLN) $(MD_PROB) $(MD_SOLN)

default:
	@echo 'Students: To compile the .java files, cd into the code/ directory first.'
	@echo 'Mentors: To compile the docs, use one of the following:'
	@echo '  make md'
	@echo '  make pdf'
	@echo '  make doc  # to make both'

$(MD_PROB): readme.src.md
	python ../common/docmaker.py md,prob < $< > $@

$(MD_SOLN): readme.src.md
	python ../common/docmaker.py md,soln < $< > $@

$(PDF_PROB).md: readme.src.md
	python ../common/docmaker.py tex,prob < $< > $@

$(PDF_SOLN).md: readme.src.md
	python ../common/docmaker.py tex,soln < $< > $@

%.pdf: %.pdf.md
	pandoc $< --out $@ \
		--latex-engine=xelatex \
		-V mainfont='Helvetica' \
		-V geometry:"top=1in, bottom=1in, left=1in, right=1in"
	rm -- $<

doc: md pdf

md: $(MD_PROB)

pdf: $(PDF_PROB) $(PDF_SOLN)

clean:
	rm -fv -- $(ALL)

.PHONY: default doc md pdf clean
