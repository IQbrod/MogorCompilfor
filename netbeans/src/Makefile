PARSER_FILES= $(wildcard Parser/*.java)
MAIN= Main.java

all:
	javac -cp ../lib/java-cup-11b.jar:../lib/java-cup-11b-runtime.jar:. $(PARSER_FILES) $(MAIN)

clean:
	rm -Rf *.class Parser/*.class Entrainement/*.class BackEnd/*.class FrontEnt/TypeCheck/*.class
