import java.awt.*;
import java.io.*;
import javax.swing.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class JotDocument extends RTextScrollPane {
    public String docName;
    public RSyntaxTextArea textArea;
    private String path;

    public JotDocument() {
        this("untitled", new RSyntaxTextArea());
    }

    public JotDocument(String name, RSyntaxTextArea txt) {
        super(txt);
        this.docName = name;
        this.textArea = txt;

        this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
        this.textArea.setCodeFoldingEnabled(true);
        // this.textArea.setBackground(new Color(41, 49, 52));
        this.textArea.setAntiAliasingEnabled(true);
        // this.textArea.setCurrentLineHighlightColor(new Color(47, 57, 60, 100));
        this.textArea.setLineWrap(true);
        this.textArea.setFont(new Font("Consolas", Font.PLAIN, 20));

        changeStyleViaThemeXml(this.textArea);

        setFoldIndicatorEnabled(true);
    }

    private void changeStyleViaThemeXml(RSyntaxTextArea txt) {
        try {
            Theme theme = Theme.load(getClass().getResourceAsStream("themes/dark.xml"));
            theme.apply(txt);
        } catch (IOException ioe) { // Never happens
            ioe.printStackTrace();
        }
    }

    public void save() {
        saveAs(docName);
    }

    public void save2(String p) {
        saveAs(p + docName);
    }

    public void saveAs(String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(textArea.getText());
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found!");
        } catch (IOException e) {
            System.out.println("There was an error saving the file!");
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String p) {
        path = p;
    }

    public JTextArea getText() {
        return textArea;
    }

    @Override
    public String getName() {
        return docName;
    }

    @Override
    public void setName(String n) {
        docName = n;
    }
}
