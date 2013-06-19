import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import javax.swing.event.*;
import javax.swing.text.Element;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class JotEventEngine {
    public static boolean lineWrap = true;

    public static void globalEvents(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("new file")) {

        } else if (e.getActionCommand().equalsIgnoreCase("open")) {
           JotFile.openFile();
        } else if (e.getActionCommand().equalsIgnoreCase("save")) {
            JotFile.saveFile();
        } else if (e.getActionCommand().equalsIgnoreCase("save as")) {
            JotFile.saveFileAs();
        } else if (e.getActionCommand().equalsIgnoreCase("close file")) {
            try {
                JotEngine.tabbedPane.remove(JotEngine.tabbedPane.getSelectedIndex());
                if ((JotEngine.tabbedPane.getTabCount()) == 0) {
                    JotEngine.tabbedPane.setVisible(false);
                }
            } catch (IndexOutOfBoundsException ex) {
                // JotEngine.console.setText("INFO: There are no more tabs to close!");
            }
        } else if (e.getActionCommand().equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }

    public static void updateNameJEE(String n) {
        JotEngine.home.setTitle(n);
    }
}
