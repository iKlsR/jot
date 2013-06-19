import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Element;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class JotFile {
    public static void openFile() {
        FileDialog fd = new FileDialog(JotEngine.frame, "Open", FileDialog.LOAD);
        String homefolder = System.getProperty("user.home");
        fd.setDirectory(homefolder);
        fd.setVisible(true);

        if (fd.getFile() == null) {
            // JotEngine.console.setText("ALERT: You didn't open anything..");
            JotEngine.clearConsole(2);
        } else {
            File file = new File(fd.getFile());

            JotDocument doc = new JotDocument(file.getName(), new RSyntaxTextArea());
            // doc.getText().addKeyListener(JotEngine.frame);
            JotEngine.tabbedPane.addTab(doc.getName(), doc);
            JotEngine.tabbedPane.setSelectedIndex(JotEngine.tabbedPane.getTabCount() - 1);

            String path = fd.getDirectory() + file.getName();

            try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                String line;

                while ((line = br.readLine()) != null) {
                    doc.getText().append(line + "\n");
                } br.close();

                doc.getText().setCaretPosition(0);

                String fullPath = fd.getDirectory() + doc.getName();
                JotEventEngine.updateNameJEE(fullPath + " - " + JotEngine.windowCaption);
                // doc.setName(doc.getName());
                // System.out.println(doc.getName());
                doc.setPath(fd.getDirectory());

                // JotEngine.console.setText("OPENED: " + fullPath);
                // JotEngine.clearConsole(5);
            } catch (FileNotFoundException e) {
                // JotEngine.console.setText("INFO: The file was not found!");
            } catch (IOException e) {
                // JotEngine.console.setText("ERROR: There was an error opening the file!");
            }
        }
    }

    public static void saveFile() {
        JotDocument doc = (JotDocument) JotEngine.tabbedPane.getSelectedComponent();

        // if the file already exists.. TODO -- check if its currently open
        String filePath = doc.getPath() + doc.getName();
        if (new File(filePath).isFile()) {
            doc.save2(doc.getPath());
            // JotEngine.console.setText("UPDATED: " + filePath);
        } else {
            saveFileAs();
        }
    }

    public static void saveFileAs() {
        FileDialog fd = new FileDialog(JotEngine.frame, "Save File", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() == null) {
            // JotEngine.console.setText("ALERT: You didn't save anything..");
            JotEngine.clearConsole(2);
        } else {
            File file = new File(fd.getDirectory() + fd.getFile());
            JotDocument doc = (JotDocument) JotEngine.tabbedPane.getSelectedComponent();
            // doc.textArea.addKeyListener(this);
            doc.saveAs(file.getAbsolutePath());

            doc.setName(fd.getFile());
            JotEngine.tabbedPane.setTitleAt(JotEngine.tabbedPane.getSelectedIndex(), doc.getName());

            String fullPath = fd.getDirectory() + doc.getName();
            JotEventEngine.updateNameJEE(fullPath + " - " + JotEngine.windowCaption);
            doc.setPath(fd.getDirectory());
            // JotEngine.console.setText("WROTE: " + fullPath);
        }
    }
}
