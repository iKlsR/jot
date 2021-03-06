import java.awt.FileDialog;
import java.awt.Color;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class JotFile {
    public static void openFile() {
        FileDialog fd = new FileDialog(JotEngine.frame, "Open", FileDialog.LOAD);
        String homefolder = System.getProperty("user.home");
        fd.setDirectory(homefolder);
        fd.setVisible(true);

        if (fd.getFile() == null) {
            // JotEngine.clearConsole(2);
        } else {
            File file = new File(fd.getFile());

            String extension = JotUtilities.getExtension(file.getName());

            JotDocument doc =
            new JotDocument(file.getName(), new RSyntaxTextArea(), JotUtilities.applySyntax(extension));

            doc.getText().getDocument().addDocumentListener(JotEngine.thisThis);
            JotEngine.tabbedPane.addTab(doc.caption(), doc);
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
                JotEngine.statusStrip.setText(JotUtilities.CLEAN + doc.getName());
                doc.setDirty(false);

                JotEngine.listModel.insertElementAt(
                    "  " + doc.getName(), JotEngine.tabbedPane.getTabCount() - 1);
                JotEngine.sidebar.setSelectedIndex(JotEngine.tabbedPane.getTabCount() - 1);

                // JotEngine.listModel.addElement("  " + doc.getName());

                doc.setPath(fd.getDirectory());
                // System.out.println(fullPath);

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        }
    }

    public static void saveFile() {
        JotDocument doc = (JotDocument) JotEngine.tabbedPane.getSelectedComponent();

        // if the file already exists.. TODO -- check if its currently open
        String filePath = doc.getPath() + doc.getName();
        if (new File(filePath).isFile()) {
            doc.save2(doc.getPath());
            JotEngine.statusStrip.setText(JotUtilities.CLEAN + doc.getName());
            doc.setDirty(false);
        } else {
            saveFileAs();
            JotEngine.statusStrip.setText(JotUtilities.CLEAN + doc.getName());
            doc.setDirty(false);
        }
    }

    public static void saveFileAs() {
        FileDialog fd = new FileDialog(JotEngine.frame, "Save File", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() == null) {

        } else {
            File file = new File(fd.getDirectory() + fd.getFile());
            JotDocument doc = (JotDocument) JotEngine.tabbedPane.getSelectedComponent();
            doc.saveAs(file.getAbsolutePath());

            doc.setName(fd.getFile());
            JotEngine.tabbedPane.setTitleAt(JotEngine.tabbedPane.getSelectedIndex(), doc.caption());

            String extension = JotUtilities.getExtension(file.getName());
            doc.getText().setSyntaxEditingStyle(JotUtilities.applySyntax(extension));
            JotUtilities.applySyntax(extension);

            JotEngine.listModel.setElementAt(
                "  " + doc.getName(), JotEngine.tabbedPane.getSelectedIndex());

            String fullPath = fd.getDirectory() + doc.getName();
            JotEventEngine.updateNameJEE(fullPath + " - " + JotEngine.windowCaption);
            JotEngine.statusStrip.setText(JotUtilities.CLEAN + doc.getName());
            doc.setDirty(false);
            doc.setPath(fd.getDirectory());
        }
    }

    public static void newFile() {
        if (JotEngine.tabbedPane.isVisible() == false) {
            JotEngine.tabbedPane.setVisible(true);
        }

        JotDocument wdoc = new JotDocument();
        JotEngine.statusStrip.setText(JotUtilities.CLEAN + wdoc.getName());
        wdoc.setDirty(false);
        wdoc.getText().addKeyListener(JotEngine.thisThis);
        wdoc.getText().getDocument().addDocumentListener(JotEngine.thisThis);

        if (JotEngine.tabbedPane.getSelectedIndex() == 0x00) {
            JotEngine.tabbedPane.insertTab(wdoc.getName(), null, wdoc, wdoc.getName(), 1);
            JotEngine.tabbedPane.setSelectedIndex(JotEngine.tabbedPane.getSelectedIndex() + 1);

            JotEngine.listModel.insertElementAt("  " + wdoc.getName(), 1);
            JotEngine.sidebar.setSelectedIndex(JotEngine.tabbedPane.getTabCount() - 1);

        } else if (JotEngine.tabbedPane.getSelectedIndex() > 0) {
            JotEngine.tabbedPane.insertTab(
                wdoc.getName(), null, wdoc, wdoc.getName(), JotEngine.tabbedPane.getSelectedIndex() + 1);
            JotEngine.tabbedPane.setSelectedIndex(JotEngine.tabbedPane.getSelectedIndex() + 1);

            JotEngine.listModel.addElement(
                "  " + wdoc.getName());
            JotEngine.sidebar.setSelectedIndex(JotEngine.tabbedPane.getTabCount() - 1);
        } else {
            JotEngine.tabbedPane.insertTab(wdoc.getName(), null, wdoc, wdoc.getName(), 0x00);
            JotEngine.listModel.insertElementAt("  " + wdoc.getName(), 0x00);
            JotEngine.sidebar.setSelectedIndex(0x00);
        }
    }

    public static void closeFile(int MODE) {
        JotDocument doc = (JotDocument) JotEngine.tabbedPane.getSelectedComponent();
        if (MODE == 1) {
            try {
                JotEngine.tabbedPane.remove(JotEngine.tabbedPane.getSelectedIndex());
                if ((JotEngine.tabbedPane.getTabCount()) == 0x00) {
                    JotEngine.tabbedPane.setVisible(false);
                }

                if (JotEngine.tabbedPane.getTabCount() == 0x00) {
                    JotEngine.listModel.remove(JotEngine.tabbedPane.getSelectedIndex() + 1);
                    JotEngine.tabbedPane.setSelectedIndex(JotEngine.tabbedPane.getSelectedIndex() + 1);
                } else {
                    JotEngine.listModel.remove(JotEngine.tabbedPane.getSelectedIndex());
                }

                if (JotEngine.tabbedPane.getSelectedIndex() < JotEngine.tabbedPane.getTabCount()) {
                    JotEngine.tabbedPane.setSelectedIndex(JotEngine.tabbedPane.getSelectedIndex());
                } else if (JotEngine.tabbedPane.getSelectedIndex() == JotEngine.tabbedPane.getTabCount()) {
                    System.out.println("bingo");
                }

            } catch (IndexOutOfBoundsException ex) {
                System.out.println(":)");
            }
        } else if (MODE == 2) {
            // ...
        } else if (MODE == 3) {
            // ...
        }
    }

    public static void unsavedChanges() {
        JotDocument doc = (JotDocument) JotEngine.tabbedPane.getSelectedComponent();
        if (doc.isDirty()) {
        int n =
        JOptionPane.showConfirmDialog(JotEngine.frame, "Unsaved Changes.. Save?", "Exit?", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                saveFile();
                System.exit(0);
            } else if (n == JOptionPane.NO_OPTION){
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
}
