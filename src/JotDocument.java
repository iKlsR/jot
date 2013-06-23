import java.awt.Font;
import java.awt.Color;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import java.awt.GraphicsEnvironment;

import javax.swing.text.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class JotDocument extends RTextScrollPane {
    public String docName;
    public RSyntaxTextArea textArea;
    private String path;
    private boolean dirty;

    public JotDocument() {
        this("untitled", new RSyntaxTextArea(), SyntaxConstants.SYNTAX_STYLE_NONE);
    }

    public JotDocument(String name, RSyntaxTextArea txt, String syntax) {
        super(txt);
        this.docName = name;
        this.textArea = txt;

        this.dirty = false;
        this.textArea.setSyntaxEditingStyle(syntax);
        this.textArea.setCodeFoldingEnabled(true);
        this.textArea.setAntiAliasingEnabled(true);
        this.textArea.setLineWrap(true);
        this.textArea.setTabSize(4);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // these are overwritten by the theme
        // this.textArea.setBackground(new Color(41, 49, 52));
        // this.textArea.setCurrentLineHighlightColor(new Color(47, 57, 60, 100));

        // this.textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 0x00));
        // this.textArea.setFont(new Font(defaultFont));

        // this needs to be configured some more..
        this.textArea.setCaretStyle(RTextArea.INSERT_MODE, ConfigurableCaret.VERTICAL_LINE_STYLE);

        this.textArea.setPopupMenu(JotComponents.docPopupMenu);

        changeStyleViaThemeXml(this.textArea);
        setFoldIndicatorEnabled(true);

        try {
            Font f =
            Font.createFont(Font.TRUETYPE_FONT, new File("res/font/UbuntuMono-R.ttf")).deriveFont(Font.TRUETYPE_FONT, 20);
            this.textArea.setFont(f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.textArea.setAntiAliasingEnabled(true);
    }

    private void changeStyleViaThemeXml(RSyntaxTextArea txt) {
        try {
            Theme theme = Theme.load(getClass().getResourceAsStream("themes/dark.xml"));
            theme.apply(txt);
        } catch (IOException ioe) { // ...
            ioe.printStackTrace();
        }
    }

    // private void loadResources() throws FontFormatException, IOException  {
    //     Font fontRaw = Font.createFont(Font.TRUETYPE_FONT, new File("UbuntuMono-R.ttf"));
    //     // Font fontBase = fontRaw.deriveFont(28f);
    //     this.defaultFont = fontRaw;
    // }

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

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean b) {
        dirty = b;
    }

    public String caption() {
        if (docName.length() > 12) {
            return docName.substring(0, 10) + "..";
        } else {
            return docName;
        }
    }

    public RSyntaxTextArea getText() {
        return textArea;
    }

    @Override
    public void setName(String n) {
        docName = n;
    }

    @Override
    public String getName() {
        return docName;
    }

    @Override public void setBorder(javax.swing.border.Border border) {
        // ...
    }
}
