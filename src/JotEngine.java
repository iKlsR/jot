import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Timer;
import java.util.TimerTask;

public class JotEngine implements ActionListener, MouseListener, KeyListener {
    public static String appName = "Jot";
    public static String buildVersion = "0.0.1";
    public static String windowCaption = appName + " " + buildVersion;

    // public static JTextField console;

    Dimension defaultSize = new Dimension(1280, 640);

    public JotComponents wc;
    public static Timer timer;

    public static JFrame frame;
    public static JotEngine thisThis;

    public static JTabbedPane tabbedPane;

    public JotEngine() {
        frame = new JFrame(windowCaption);
        frame.setSize(defaultSize);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon("res/icon.png").getImage());

        thisThis = this;
        init();
    }

    public void init() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addMouseListener(this);
        tabbedPane.addKeyListener(this);
        tabbedPane.setFocusable(false);
        wc = new JotComponents();

        // the default tab
        // ! don't add any settings here, default settings are in JotDocument
        // JotDocument doc = new JotDocument();
        // doc.getText().addKeyListener(this);
        // tabbedPane.addTab(doc.getName(), doc);
        JotFile.newFile();

        // updateNameJE(doc.getName() + " - " + windowCaption);

        for (JMenuItem jm : wc.fileMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.editMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.viewMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.optionMenuItems)     jm.addActionListener(this);
        for (JMenuItem jm : wc.tabPopupMenuItems)   jm.addActionListener(this);

        // console = new JTextField("> ");
        // console.setBackground(new Color(41, 49, 52));
        // console.setForeground(new Color(232, 232, 211));
        // console.setFont(new Font("Consolas", Font.PLAIN, 20));

        frame.add(tabbedPane);
        // add(console, BorderLayout.SOUTH);
        frame.setJMenuBar(JotComponents.menuBar);
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
    }

    public void mousePressed(MouseEvent e) {
    }

    private int oldIx = -1;
    public void mouseReleased(MouseEvent e) {
        JTabbedPane tp = (JTabbedPane) e.getSource();
        int ix = tp.indexAtLocation( e.getX(), e.getY() );
        // if (ix == oldIx) return;
        // oldIx = ix;
        // if (ix == -1) return;

        if (e.getButton() == MouseEvent.BUTTON3) {
            try {
                String pTitle = tp.getTitleAt(ix);
                System.out.println(tp.getTitleAt(ix));
                JotComponents.tabPopupMenu.show( (JTabbedPane) e.getComponent(), e.getX(), e.getY());
            } catch (ArrayIndexOutOfBoundsException ae) {
                return;
            }
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

    // this will get its own class? soon..
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
