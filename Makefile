JAVAC=javac

all:
	$(JAVAC) -classpath lib\rsyntaxtextarea.jar -d bin  \
	src\Jot.java src\JotEngine.java src\JotDocument.java \
	src\JotComponents.java src\JotEventEngine.java \
	src\JotFile.java src\JotUtilities.java src\JotTabbedPaneUI.java
file:
	$(JAVAC) -classpath lib\rsyntaxtextarea.jar src\JotFile.java -d bin
engine:
	$(JAVAC) -classpath lib\rsyntaxtextarea.jar \
	src\JotEngine.java src\JotDocument.java -d bin
doc:
	$(JAVAC) -classpath lib\rsyntaxtextarea.jar -d bin \
	src\JotDocument.java
jot:
	$(JAVAC) src\Jot.java -d bin
exec:
	jar cmf MANIFEST jot.jar bin/*.class
.PHONY clean:
clean:
	rm -f bin/*.class
