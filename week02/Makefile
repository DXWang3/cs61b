SOURCE_DIR := .
OBJECT_DIR := .

SOURCES := $(wildcard $(SOURCE_DIR)/*.java)
OBJECTS := $(patsubst $(SOURCE_DIR)/%.java,$(OBJECT_DIR)/%.class,$(SOURCES))
OBJECTS_WILDCARD := $(OBJECT_DIR)/*.class


default: $(OBJECTS)
	@echo -n

%.class: %.java
	javac $(SOURCES)


.PHONY: default
