SRC= ./netbeans/src
LIB= ./netbeans/lib/
JAVA_FILES:= $(shell find $(SRC) -name *.java -exec ls {} +)

all:
	javac -cp $(LIB)java-cup-11b.jar:$(LIB)java-cup-11b-runtime.jar:. $(JAVA_FILES)

clean:
	find $(SRC) -name *.class -exec rm -rf {} +
