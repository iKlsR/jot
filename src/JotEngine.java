import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.util.Timer;
import java.util.TimerTask;

public class JotEngine implements ActionListener, MouseListener, KeyListener {
    private static String appName = "Jot";
    private static String buildVersion = "0.0.1";
    private static JPanel jp;
    public static String windowCaption = appName + " " + buildVersion;

    Dimension defaultSize = new Dimension(1280, 640);

    public static Timer timer;
    public static JFrame frame;
    public static JTabbedPane tabbedPane;

    public static JotComponents wc;
    public static JotEngine thisThis;
    public static JotConsole console;
    public static JotStatusStrip statusStrip;

    public JotEngine() {
        frame = new JFrame(windowCaption);
        frame.setSize(defaultSize);
        frame.setResizable(true);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(0, 0));
        frame.setIconImage(new ImageIcon("res/icon.png").getImage());

        thisThis = this;
        init();
    }

    public void init() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addMouseListener(this);
        tabbedPane.addKeyListener(this);
        tabbedPane.setFocusable(false);
        tabbedPane.setUI(new JotTabbedPaneUI());

        wc = new JotComponents();
        jp = new JPanel();
        console = new JotConsole("> ");

        // the default tab, don't add any settings here, default settings are in JotDocument
        JotDocument doc = new JotDocument();
        doc.getText().addKeyListener(this);
        tabbedPane.addTab(doc.getName(), doc);

        updateNameJE(doc.getName() + " - " + windowCaption);
        statusStrip = new JotStatusStrip(doc.getName());

        for (JMenuItem jm : wc.fileMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.editMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.viewMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.optionMenuItems)     jm.addActionListener(this);
        for (JMenuItem jm : wc.tabPopupMenuItems)   jm.addActionListener(this);

        // the foundation..
        frame.add(tabbedPane);
        frame.setJMenuBar(JotComponents.menuBar);
        jp.setLayout(new BorderLayout());
        jp.add(statusStrip, BorderLayout.NORTH);
        jp.add(console, BorderLayout.SOUTH);
        frame.add(jp, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JotEventEngine.globalEvents(e);
    }

    public void updateNameJE(String n) {
        this.frame.setTitle(n);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JotDocument doc = (JotDocument) tabbedPane.getSelectedComponent();
        if (doc.getPath() == null) {
            updateNameJE(doc.getName() + " - " + windowCaption);
        } else {
            updateNameJE(doc.getPath() + doc.getName() + " - " + windowCaption);
        }
        statusStrip.setText(doc.getName());
    }

    public void mousePressed(MouseEvent e) {
    }

    // private int oldIx = -1;
    public void mouseReleased(MouseEvent e) {
        JTabbedPane tp = (JTabbedPane) e.getSource();
        int ix = tp.indexAtLocation( e.getX(), e.getY() );
        // if (ix == oldIx) return;
        // oldIx = ix;
        // if (ix == -1) return;

        if (e.getButton() == MouseEvent.BUTTON3) {
            try {
                String pTitle = tp.getTitleAt(ix);
                JotComponents.tabPopupMenu.show( (JTabbedPane) e.getComponent(), e.getX(), e.getY());
            } catch (ArrayIndexOutOfBoundsException ae) {
                return;
            }
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            JotFile.closeFile(1);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_K && e.isControlDown() == true) {
            if (JotEventEngine.menuBarVisible == true) {
                JotComponents.menuBar.setVisible(false);
                JotEventEngine.menuBarVisible = false;
            } else if (JotEventEngine.menuBarVisible == false) {
                JotComponents.menuBar.setVisible(true);
                JotEventEngine.menuBarVisible = true;
            }
        }
    }

    public static void clearConsole(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    static class RemindTask extends TimerTask {
        @Override
        public void run() {
            // System.out.println("hello");
            // console.setText("> ");
            // timer.cancel();
        }
    }
}
