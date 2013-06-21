import java.awt.FileDialog;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

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

            if (JotEngine.tabbedPane.isVisible() == false) {
                JotEngine.tabbedPane.setVisible(true);
            }

            try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                String line;

                while ((line = br.readLine()) != null) {
                    doc.getText().append(line + "\n");
                } br.close();

                doc.getText().setCaretPosition(0);

                String fullPath = fd.getDirectory() + doc.getName();
                JotEventEngine.updateNameJEE(fullPath + " - " + JotEngine.windowCaption);
                JotEngine.statusStrip.setText(doc.getName());
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
            JotEngine.statusStrip.setText(doc.getName());
            doc.setPath(fd.getDirectory());
            // JotEngine.console.setText("WROTE: " + fullPath);
        }
    }

    public static void newFile() {
        if (JotEngine.tabbedPane.isVisible() == false) {
            JotEngine.tabbedPane.setVisible(true);
        }

        JotDocument wdoc = new JotDocument();
        wdoc.getText().addKeyListener(JotEngine.thisThis);

        if (JotEngine.tabbedPane.getSelectedIndex() <= 1) {
            JotEngine.tabbedPane.insertTab(wdoc.getName(), null, wdoc, wdoc.getName(), 0);
        } else if (JotEngine.tabbedPane.getSelectedIndex() > 1) {
            JotEngine.tabbedPane.insertTab(
                wdoc.getName(), null, wdoc, wdoc.getName(), JotEngine.tabbedPane.getSelectedIndex() + 1
            );
            JotEngine.tabbedPane.setSelectedIndex(JotEngine.tabbedPane.getSelectedIndex() + 1);
        } else {
            return;
        }
    }

    public static void closeFile(int MODE) {
        if (MODE == 1) {
            try {
                JotEngine.tabbedPane.remove(JotEngine.tabbedPane.getSelectedIndex());
                if ((JotEngine.tabbedPane.getTabCount()) == 0x00) {
                    JotEngine.tabbedPane.setVisible(false);
                }
            } catch (IndexOutOfBoundsException ex) {
                // JotEngine.console.setText("INFO: There are no more tabs to close!");
            }
        } else if (MODE == 2) {
            // ...
        } else if (MODE == 3) {
            // ...
        }
    }
}
