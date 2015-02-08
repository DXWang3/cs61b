META := Makefile readme.make.py
ALL := ../readme.md ../readme.pdf

default: ../readme.md
	@echo 'Note: To make the PDF, use `make pdf` or `make all`.'

all: $(ALL)

pdf: ../readme.pdf

clean:
	rm -fv -- $(ALL)

../readme.md: readme.src.md $(META)
	python readme.make.py < $< > $@

# Pandoc documentation here: http://johnmacfarlane.net/pandoc/faqs.html
../readme.pdf: readme.src.md $(META)
	python readme.make.py soln,tex < $< > readme.for-pdf.md
	pandoc readme.for-pdf.md --out $@ \
		--latex-engine=xelatex \
		-V mainfont='Helvetica' \
		-V geometry:"top=1in, bottom=1in, left=1in, right=1in"
	rm -- readme.for-pdf.md

.PHONY: all default pdf clean
