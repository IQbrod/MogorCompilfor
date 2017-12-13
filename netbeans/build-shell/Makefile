SRC= ../src

JAVA_FILES:= $(shell find $(SRC) -name *.java -exec ls {} +)

all:
	javac -cp ../lib/java-cup-11b.jar:../lib/java-cup-11b-runtime.jar:. $(JAVA_FILES)

clean:
	find $(SRC) -name *.class -exec rm -rf {} +
