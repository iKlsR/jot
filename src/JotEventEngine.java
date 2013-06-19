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
    public static boolean menuBarVisible = true;

    // what?
    public class JU extends JotUtilities { }

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
        } else if (e.getActionCommand().equalsIgnoreCase ("hide menu")) {
            // need to add KeyListener to toggle it back..
            JotComponents.menuBar.setVisible(false);
        }
        // Tab Menu
        else if (e.getActionCommand().equalsIgnoreCase("tab width 2")) {
            wdoc.getText().setTabSize(2);
            JotComponents.indent4.setSelected(false);
            JotComponents.indent8.setSelected(false);
            System.out.println("asas");
        } else if (e.getActionCommand().equalsIgnoreCase("tab width 4")) {
            wdoc.getText().setTabSize(4);
            JotComponents.indent2.setSelected(false);
            JotComponents.indent8.setSelected(false);
        } else if (e.getActionCommand().equalsIgnoreCase("tab width 8")) {
            wdoc.getText().setTabSize(8);
            JotComponents.indent2.setSelected(false);
            JotComponents.indent4.setSelected(false);
        } else if (e.getActionCommand().equalsIgnoreCase("hex to rgb")) {
            String str = wdoc.getText().getSelectedText();
            if (str != null) {
                System.out.println("RGB: " + JU.hexToR(str) + ", " + JU.hexToG(str) + ", " + JU.hexToB(str));
            }
        }
    }

    public static void updateNameJEE(String n) {
        JotEngine.frame.setTitle(n);
    }
}
