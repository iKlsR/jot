import java.awt.Dimension;
import java.awt.Event;

import java.awt.event.KeyEvent;

import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JMenuBar;
import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;
import javax.swing.JRadioButtonMenuItem;

public class JotComponents {
    private static class JotMenuItem extends JMenuItem {
        JotMenuItem(String title) {
            super(title);
        }

        JotMenuItem(String title, int nmemonic) {
            super(title, nmemonic);
        }

        JotMenuItem(String title, int nmemonic, int pWidth) {
            super(title, nmemonic);
            this.preferredWidth = pWidth;
        }

        private static int preferredWidth = 230;

        @Override
        public Dimension getPreferredSize() {
            Dimension preferred = super.getPreferredSize();
            Dimension minimum = getMinimumSize();
            Dimension maximum = getMaximumSize();
            // preferred.width = Math.min(Math.max(preferred.width, minimum.width), maximum.width);
            preferred.width = preferredWidth;
            preferred.height = Math.min(Math.max(preferred.height, minimum.height), maximum.height);
            return preferred;
        }
    }

    public static JMenuBar menuBar = new JMenuBar();

    public static JMenu fileMenu = new JMenu("File");
        public static JMenuItem newTab = new JotMenuItem("New File", KeyEvent.VK_N);
        public static JMenuItem open = new JotMenuItem("Open File", KeyEvent.VK_O);
        public static JMenuItem save = new JotMenuItem("Save", KeyEvent.VK_S);
        public static JMenuItem saveAs = new JotMenuItem("Save As", KeyEvent.VK_A);
        public static JMenuItem close = new JotMenuItem("Close File", KeyEvent.VK_C);
        public static JMenuItem exit = new JotMenuItem("Exit", KeyEvent.VK_E);

    public static JMenu editMenu = new JMenu("Edit");
        public static JMenuItem undo = new JotMenuItem("Undo", KeyEvent.VK_Z);
        public static JMenuItem redo = new JotMenuItem("Redo", KeyEvent.VK_Y);
        public static JMenuItem copy = new JotMenuItem("Copy", KeyEvent.VK_C);
        public static JMenuItem cut = new JotMenuItem("Cut", KeyEvent.VK_X);
        public static JMenuItem paste = new JotMenuItem("Paste", KeyEvent.VK_P);
        public static JMenuItem selectAll = new JotMenuItem("Select All", KeyEvent.VK_A);

    public static JMenu viewMenu = new JMenu("View");
        public static JMenuItem fullScreen = new JRadioButtonMenuItem("Toggle Full Screen");
        public static JMenuItem hideMenuBar = new JotMenuItem("Hide Menu", KeyEvent.VK_K);
        public static JMenuItem hideConsole = new JotMenuItem("Hide Console", KeyEvent.VK_PERIOD);

    public static JMenu optionsMenu = new JMenu("Options");
        public static JMenuItem toggleLineWrap = new JRadioButtonMenuItem("Toggle Linewrap");
        public static JMenu indentation = new JMenu("Indentation");
        ButtonGroup indentationGroup = new ButtonGroup();
        public static JMenuItem indent2 = new JRadioButtonMenuItem("Tab Width 2");
        public static JMenuItem indent4 = new JRadioButtonMenuItem("Tab Width 4");
        public static JMenuItem indent8 = new JRadioButtonMenuItem("Tab Width 8");
        public static JMenuItem spacesToTabs = new JMenuItem("Convert Indentation to Tabs");
        public static JMenuItem tabsToSpaces = new JMenuItem("Convert Indentation to Spaces");
        public static JMenuItem hexToRGB = new JotMenuItem("HEX to RGB");

    public static JMenu helpMenu = new JMenu("Help");
        public static JMenuItem source = new JotMenuItem("Get the source");
        public static JMenuItem about = new JotMenuItem("About Jot");

    public static JPopupMenu tabPopupMenu = new JPopupMenu();
        public static JotMenuItem pClose = new JotMenuItem("Close");
        public static JotMenuItem pCloseAll = new JotMenuItem("Close All Except This");
        public static JotMenuItem pCloseAllToRight = new JotMenuItem("Close Tabs To The Right");
        public static JotMenuItem pNew = new JotMenuItem("New", KeyEvent.VK_N, 230);
        public static JotMenuItem pOpen = new JotMenuItem("Open", KeyEvent.VK_O, 230);

    public static JPopupMenu docPopupMenu = new JPopupMenu();
        public static JMenuItem dCopy = new JotMenuItem("Copy", KeyEvent.VK_C, 230);
        public static JMenuItem dCut = new JotMenuItem("Cut", KeyEvent.VK_X);
        public static JMenuItem dPaste = new JotMenuItem("Paste", KeyEvent.VK_P);
        public static JMenuItem dSelectAll = new JotMenuItem("Select All", KeyEvent.VK_A);
        public static JMenuItem dHexToRGB = new JotMenuItem("HEX to RGB");

        JMenuItem [] fileMenuItems = { newTab, open, save, saveAs, close, exit };
        JMenuItem [] editMenuItems = { undo, redo, copy, cut, paste, selectAll };
        JMenuItem [] viewMenuItems = { fullScreen, hideMenuBar, hideConsole };
        JMenuItem [] optionMenuItems = { toggleLineWrap,
            indent2, indent4, indent8, hexToRGB,
            spacesToTabs, tabsToSpaces
        };
        JMenuItem [] tabPopupMenuItems = { pClose, pCloseAll, pCloseAllToRight, pNew, pOpen };
        JMenuItem [] docPopupMenuItems = { dCopy, dCut, dPaste, dSelectAll, dHexToRGB };
        JMenuItem [] helpMenuItems = { source, about };

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
        editMenu.addSeparator();
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
        editMenu.add(selectAll);
        menuBar.add(editMenu);

        ///////////////////////////////////////// View Menu ////////////////////////////////////////
        viewMenu.setMnemonic('V');
        // fullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12));
        viewMenu.add(fullScreen);
        hideMenuBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, Event.CTRL_MASK));
        viewMenu.add(hideMenuBar);
        hideConsole.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, Event.CTRL_MASK));
        viewMenu.add(hideConsole);
        menuBar.add(viewMenu);

        ///////////////////////////////////////// Options Menu ////////////////////////////////////////
        optionsMenu.setMnemonic('O');
        toggleLineWrap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));
        optionsMenu.add(toggleLineWrap);
        indentationGroup.add(indent2);
        indentationGroup.add(indent4);
        indentationGroup.add(indent8);
        indentation.add(indent2);
        indentation.add(indent4);
        indentation.add(indent8);
        indentation.addSeparator();
        indentation.add(spacesToTabs);
        indentation.add(tabsToSpaces);
        optionsMenu.add(indentation);
        optionsMenu.add(hexToRGB);
        menuBar.add(optionsMenu);

        ///////////////////////////////////////// Help Menu ////////////////////////////////////////
        helpMenu.setMnemonic('H');
        helpMenu.add(source);
        helpMenu.addSeparator();
        helpMenu.add(about);
        menuBar.add(helpMenu);

        ///////////////////////////////////////// Tab Popup Menu ////////////////////////////////////////
        tabPopupMenu.add(pClose);
        tabPopupMenu.add(pCloseAll);
        tabPopupMenu.add(pCloseAllToRight);
        tabPopupMenu.addSeparator();
        tabPopupMenu.add(pNew);
        pNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
        tabPopupMenu.add(pOpen);
        pOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));

        ///////////////////////////////////////// Doc Popup Menu ////////////////////////////////////////
        docPopupMenu.add(dCopy);
        dCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        docPopupMenu.add(dCut);
        dCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        docPopupMenu.add(dPaste);
        dPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK));
        docPopupMenu.addSeparator();
        docPopupMenu.add(dSelectAll);
        dSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
        docPopupMenu.add(dHexToRGB);
    }
}
