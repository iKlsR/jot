import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;

public class JotComponents {
    public static JMenuBar menuBar = new JMenuBar();

    public static JMenu fileMenu = new JMenu("File");
        public static JMenuItem newTab = new JMenuItem("New File", KeyEvent.VK_N);
        public static JMenuItem open = new JMenuItem("Open", KeyEvent.VK_O);
        public static JMenuItem save = new JMenuItem("Save", KeyEvent.VK_S);
        public static JMenuItem saveAs = new JMenuItem("Save As", KeyEvent.VK_A);
        public static JMenuItem close = new JMenuItem("Close File", KeyEvent.VK_C);
        public static JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_E);

    public static JMenu editMenu = new JMenu("Edit");
        public static JMenuItem undo = new JMenuItem("Undo", KeyEvent.VK_Z);
        public static JMenuItem redo = new JMenuItem("Redo", KeyEvent.VK_Y);
        public static JMenuItem copy = new JMenuItem("Copy", KeyEvent.VK_C);
        public static JMenuItem cut = new JMenuItem("Cut", KeyEvent.VK_X);
        public static JMenuItem paste = new JMenuItem("Paste", KeyEvent.VK_P);

    public static JMenu viewMenu = new JMenu("View");
        public static JMenuItem fullScreen = new JRadioButtonMenuItem("Toggle Full Screen");

    public static JMenu optionsMenu = new JMenu("Options");
        public static JMenuItem toggleLineWrap = new JRadioButtonMenuItem("Toggle Linewrap");
        public static JMenuItem hideMenuBar = new JMenuItem("Hide Menu", KeyEvent.VK_K);
        public static JMenuItem hideConsole = new JMenuItem("Hide Console", KeyEvent.VK_PERIOD);
        public static JMenuItem indentation = new JMenu("Indentation");
        public static JMenuItem indent2 = new JRadioButtonMenuItem("Tab Width 2");
        public static JMenuItem indent4 = new JRadioButtonMenuItem("Tab Width 4");
        public static JMenuItem indent8 = new JRadioButtonMenuItem("Tab Width 8");
        public static JMenuItem hexToRGB = new JMenuItem("HEX to RGB");

    public static JPopupMenu tabPopupMenu = new JPopupMenu();
        public static JMenuItem pClose = new JMenuItem("Close");
        public static JMenuItem pCloseAll = new JMenuItem("Close All Except This");
        public static JMenuItem pCloseAllToRight = new JMenuItem("Close Tabs To The Right");
        public static JMenuItem pNew = new JMenuItem("New File");
        public static JMenuItem pOpen = new JMenuItem("Open File");

        JMenuItem [] fileMenuItems = { newTab, open, save, saveAs, close, exit };
        JMenuItem [] editMenuItems = { undo, redo, copy, cut, paste };
        JMenuItem [] viewMenuItems = { fullScreen };
        JMenuItem [] optionMenuItems = {
            toggleLineWrap, hideMenuBar, hideConsole,
            indent2, indent4, indent8, hexToRGB
        };
        JMenuItem [] tabPopupMenuItems = { pClose, pClose, pNew, pOpen, pCloseAllToRight };

    public JotComponents() {
        ///////////////////////////////////////// Initialize ////////////////////////////////////////
        toggleLineWrap.setSelected(true);
        indent4.setSelected(true);
        ///////////////////////////////////////// File Menu ////////////////////////////////////////
        fileMenu.setMnemonic('F');
        newTab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
        fileMenu.add(newTab);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
        fileMenu.add(open);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        fileMenu.add(save);
        saveAs.setDisplayedMnemonicIndex(5);
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK | Event.SHIFT_MASK));
        fileMenu.add(saveAs);
        fileMenu.addSeparator();
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
        fileMenu.add(close);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
        fileMenu.add(exit);
        menuBar.add(fileMenu);

        ///////////////////////////////////////// Edit Menu ////////////////////////////////////////
        editMenu.setMnemonic('E');
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));
        editMenu.add(undo);
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.CTRL_MASK));
        editMenu.add(redo);
        editMenu.addSeparator();
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        editMenu.add(copy);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        editMenu.add(cut);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
        editMenu.add(paste);
        menuBar.add(editMenu);

        ///////////////////////////////////////// View Menu ////////////////////////////////////////
        viewMenu.setMnemonic('V');
        // fullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12));
        viewMenu.add(fullScreen);
        menuBar.add(viewMenu);

        ///////////////////////////////////////// Options Menu ////////////////////////////////////////
        optionsMenu.setMnemonic('O');
        toggleLineWrap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));
        optionsMenu.add(toggleLineWrap);
        hideMenuBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, Event.CTRL_MASK));
        optionsMenu.add(hideMenuBar);
        hideConsole.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, Event.CTRL_MASK));
        optionsMenu.add(hideConsole);
        optionsMenu.add(indentation);
            indentation.add(indent2);
            indentation.add(indent4);
            indentation.add(indent8);
        optionsMenu.add(hexToRGB);
        menuBar.add(optionsMenu);

        ///////////////////////////////////////// Tab Popup Menu ////////////////////////////////////////
        tabPopupMenu.add(pClose);
        tabPopupMenu.add(pCloseAll);
        tabPopupMenu.add(pCloseAllToRight);
        tabPopupMenu.addSeparator();
        tabPopupMenu.add(pNew);
        tabPopupMenu.add(pOpen);
    }
}
