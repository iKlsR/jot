import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.DefaultListModel;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.util.Timer;
import java.util.TimerTask;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

class Lbl extends JLabel {
    Lbl(String msg) {
        super(msg);
        setPreferredSize(new Dimension(40, 0x00));
    }

    Lbl() {
        setPreferredSize(new Dimension(40, 0x00));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setColor(new Color(52, 152, 219));
        g2d.fillRect(0x00, 0, 25, 26);
        int xpoints[] = {25, 25, 33};
        int ypoints[] = {0, 26, 13};
        int npoints = 3;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.fillPolygon(new Polygon(xpoints, ypoints, npoints));
    }
}

public class JotEngine implements ActionListener, MouseListener, KeyListener, DocumentListener, WindowListener, ListSelectionListener {
    private static String appName = "Jot";
    private static String buildVersion = "0.0.1";
    private static JPanel jp;
    public static String windowCaption = appName + " " + buildVersion;

    Dimension defaultSize = new Dimension(1280, 650);
    // Dimension defaultSize = new Dimension(1366, 727);

    public static Timer timer;
    public static JFrame frame;
    public static JTabbedPane tabbedPane;

    public static JotComponents wc;
    public static JotEngine thisThis;
    public static JotConsole console;
    public static Lbl lbl;
    public static JotStatusStrip statusStrip;
    public static JotStatusStrip docInfoStrip;
    public static JotStatusStrip tempStrip;
    public static JotStatusStrip currentLN;
    public static JotStatusStrip langStrip;

    public static DefaultListModel<String> listModel;
    public static JotSideBar<String> sidebar;

    public JotEngine() {
        frame = new JFrame(windowCaption);
        frame.setSize(defaultSize);
        frame.setResizable(true);
        // frame.setLocationByPlatform(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(0, 0));
        frame.setIconImage(new ImageIcon("res/icon.png").getImage());

        thisThis = this;
        frame.addWindowListener(this);
        init();
    }

    public void init() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addMouseListener(this);
        tabbedPane.addKeyListener(this);
        tabbedPane.setFocusable(false);
        tabbedPane.setUI(new JotTabbedPaneUI());
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        wc = new JotComponents();
        jp = new JPanel();
        console = new JotConsole("");
        console.setVisible(false);
        console.addKeyListener(this);
        lbl = new Lbl("Jot");

        // this needs its own file soon..
        listModel = new DefaultListModel<String>();
        sidebar = new JotSideBar<String>(listModel);
        sidebar.addListSelectionListener(this);

        // the default tab, don't add any settings here, default settings are in JotDocument
        // really want to get rid of this, perhaps call all update functions from JotFile

        JotDocument doc = new JotDocument();
        doc.getText().addKeyListener(this);
        doc.getText().getDocument().addDocumentListener(this);
        tabbedPane.addTab(doc.getName(), doc);

        listModel.insertElementAt("  " + doc.getName(), 0x00);
        sidebar.setSelectedIndex(0x00);

        console.addActionListener(this);

        updateNameJE(doc.getName() + " - " + windowCaption);
        statusStrip = new JotStatusStrip(" " + doc.caption(), new Color(243, 156, 18));
        currentLN = new JotStatusStrip("---/ ", new Color(52, 152, 219));

        docInfoStrip = new JotStatusStrip(" ", new Color(149, 165, 166));
        docInfoStrip.updateStrip(JotEventEngine.currentSpaces);
        tempStrip = new JotStatusStrip("  ---", new Color(149, 165, 166)); // temp

        langStrip = new JotStatusStrip("None" + " ", new Color(189, 195, 199));

        for (JMenuItem jm : wc.fileMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.editMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.viewMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.optionMenuItems)     jm.addActionListener(this);
        for (JMenuItem jm : wc.tabPopupMenuItems)   jm.addActionListener(this);
        for (JMenuItem jm : wc.docPopupMenuItems)   jm.addActionListener(this);
        for (JMenuItem jm : wc.helpMenuItems)       jm.addActionListener(this);

        foundation();
    }

    public void foundation() {
        // the foundation..
        // frame.add(tabbedPane);
        frame.setJMenuBar(JotComponents.menuBar);
        jp.setLayout(new BorderLayout()); //

        jp.add(statusStrip, BorderLayout.WEST);

        JPanel middle = new JPanel();
        middle.setLayout(new BorderLayout());
            middle.add(docInfoStrip, BorderLayout.WEST);
            middle.add(tempStrip, BorderLayout.CENTER);
            middle.add(currentLN, BorderLayout.EAST);
        jp.add(middle, BorderLayout.CENTER);

        jp.add(langStrip, BorderLayout.EAST);

        //
        // jp.add(console, BorderLayout.SOUTH);
        JPanel cons = new JPanel();
        cons.setBackground(new Color(31, 39, 42));
        cons.setLayout(new BorderLayout());
        cons.add(lbl, BorderLayout.WEST);
        cons.add(console);
        jp.add(cons, BorderLayout.SOUTH);
        //

        JPanel cont = new JPanel();
        cont.setLayout(new BorderLayout());
        cont.add(tabbedPane);
        cont.add(jp, BorderLayout.SOUTH);

        frame.add(new JotSplitPane(sidebar, cont, 200, 2, false));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JotEventEngine.actionEvents(e);
    }

    public void updateNameJE(String n) {
        this.frame.setTitle(n);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JotEventEngine.mouseClickEvents(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JotEventEngine.mouseReleasedEvents(e);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) {
        JotEventEngine.keyTypedEvents(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JotEventEngine.keyPressedEvents(e);
    }

    public void changedUpdate(DocumentEvent e) { }

    public void insertUpdate(DocumentEvent e) {
        JotDocument doc = (JotDocument) JotEngine.tabbedPane.getSelectedComponent();
        if (e.getType() == DocumentEvent.EventType.INSERT || e.getType() == DocumentEvent.EventType.REMOVE) {
            // will use isDirty() eventually, for now this works..
            statusStrip.setText(JotUtilities.DIRTY + doc.getName());
            doc.setDirty(true);
        }
    }

    public void removeUpdate(DocumentEvent e) { }


    public static void clearConsole(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    static class RemindTask extends TimerTask {
        @Override
        public void run() {
            console.setText("");
            timer.cancel();
        }
    }

    public void windowActivated(WindowEvent e) { }
    public void windowClosed(WindowEvent e) { }
    public void windowDeactivated(WindowEvent e) { }
    public void windowDeiconified(WindowEvent e) { }
    public void windowIconified(WindowEvent e) { }
    public void windowOpened(WindowEvent e) { }

    public void windowClosing(WindowEvent e) {
        JotDocument doc = (JotDocument) JotEngine.tabbedPane.getSelectedComponent();
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (JotEngine.tabbedPane.getTabCount() == 0x00) {
                // whut?
            } else {
                JotFile.unsavedChanges();
            }
        }
    }

    public void valueChanged(ListSelectionEvent le) {
        if (le.getValueIsAdjusting() == false) {
            JotEngine.tabbedPane.setSelectedIndex(sidebar.getSelectedIndex());
        }
    }
}
