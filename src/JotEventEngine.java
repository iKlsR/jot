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
    public static boolean fullScreen = false;

    public static void globalEvents(ActionEvent e) {
        // File Menu Items
        if (e.getActionCommand().equalsIgnoreCase("new file")) {

        } else if (e.getActionCommand().equalsIgnoreCase("open")) {
           JotFile.openFile();
        } else if (e.getActionCommand().equalsIgnoreCase("save")) {
            JotFile.saveFile();
        } else if (e.getActionCommand().equalsIgnoreCase("save as")) {
            JotFile.saveFileAs();
        } else if (e.getActionCommand().equalsIgnoreCase("close file")) {

        } else if (e.getActionCommand().equalsIgnoreCase("exit")) {
            System.exit(0);
        }

        // Edit Menu Items

        // View Menu Items
        if (e.getActionCommand().equalsIgnoreCase("toggle full screen")) {
            if (fullScreen == true) {
                JotEngine.frame.dispose();
                JotEngine.frame.setResizable(false);
                JotEngine.frame.setUndecorated(false);
                JotEngine.frame.setVisible(true);

                fullScreen = false;
                JotComponents.fullScreen.setSelected(false);
            } else if (fullScreen == false) {
                GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice dev = env.getDefaultScreenDevice();
                JotEngine.frame.dispose();
                JotEngine.frame.setResizable(false);
                JotEngine.frame.setUndecorated(true);
                dev.setFullScreenWindow(JotEngine.frame);

                fullScreen = true;
                JotComponents.fullScreen.setSelected(true);
            }
        }

        // Option Menu Items
        JotDocument wdoc = (JotDocument) JotEngine.tabbedPane.getSelectedComponent();
        if (e.getActionCommand().equalsIgnoreCase("toggle linewrap")) {
            if (lineWrap == true) {
                lineWrap = false;
                JotComponents.toggleLineWrap.setSelected(false);
                wdoc.getText().setLineWrap(false);
            } else if (lineWrap == false) {
                lineWrap = true;
                JotComponents.toggleLineWrap.setSelected(true);
                wdoc.getText().setLineWrap(true);
            }
        }
    }

    public static void updateNameJEE(String n) {
        // JotEngine.home.setTitle(n);
    }
}
