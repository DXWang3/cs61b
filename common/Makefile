WEEK := $(shell basename $(shell pwd))
PDF_PROB := csm61b-$(WEEK).pdf
PDF_SOLN := csm61b-$(WEEK)-soln.pdf
PDF_PROB_BW := csm61b-$(WEEK).bw.pdf
PDF_SOLN_BW := csm61b-$(WEEK)-soln.bw.pdf
MD_PROB := readme.md
MD_SOLN = readme-soln.md
META := code/*.java code-soln/*.java Makefile ../common/docmaker.py ../common/docmaker_header.tex
PRODUCTS := $(PDF_PROB) $(PDF_SOLN) $(PDF_PROB_BW) $(PDF_SOLN_BW) $(MD_PROB) $(MD_SOLN)
INTERMEDIATES := $(PDF_PROB).md $(PDF_SOLN).md

default:
	@echo 'Students: To compile the .java files, cd into the code/ directory first.'
	@echo 'Mentors: To compile the docs, use one of the following:'
	@echo '  make md'
	@echo '  make pdf'
	@echo '  make doc  # to make both pdf and md'

$(MD_PROB): readme.src.md $(META)
	python ../common/docmaker.py md,prob < $< > $@

$(MD_SOLN): readme.src.md $(META)
	python ../common/docmaker.py md,soln < $< > $@

$(PDF_PROB).md: readme.src.md $(META)
	python ../common/docmaker.py tex,prob < $< > $@

$(PDF_SOLN).md: readme.src.md $(META)
	python ../common/docmaker.py tex,soln < $< > $@

%.pdf: %.pdf.md
	pandoc $< --out $@ \
		--include-in-header ../common/docmaker_header.tex \
		--latex-engine=xelatex \
		-V mainfont='Helvetica' \
		-V geometry:"top=1in, bottom=0.7in, left=1in, right=1in"

%.bw.pdf: %.pdf.md
	pandoc $< --out $@ \
		--include-in-header ../common/docmaker_header.tex \
		--no-highlight \
		--latex-engine=xelatex \
		-V mainfont='Helvetica' \
		-V geometry:"top=1in, bottom=0.7in, left=1in, right=1in"

doc: md pdf

md: $(MD_PROB)

pdf: $(PDF_SOLN) $(PDF_PROB) $(PDF_PROB_BW)
	rm -f -- $(PDF_PROB).md $(PDF_SOLN).md

echo:
	@echo $(PDF_PROB)
	@echo $(PDF_SOLN)
	@echo $(MD_PROB)
	@echo $(MD_SOLN)

clean:
	rm -f -- $(PRODUCTS) $(INTERMEDIATES)

.PHONY: default doc md pdf clean
