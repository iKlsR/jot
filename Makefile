JAVAC=javac

jot:
	$(JAVAC) -classpath lib/rsyntaxtextarea.jar -d bin  \
	src/Jot.java src/JotEngine.java src/JotComponents.java \
	src/JotEventEngine.java src/JotFile.java \
	src/JotDocument.java src/JotTabbedPaneUI.java \
	src/JotUtilities.java src/JotStatusStrip.java \
	src/JotConsole.java src/JotSplitPane.java src/JotSidebar.java
.PHONY clean:
clean:
	rm -f bin/*.class
