import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Timer;
import java.util.TimerTask;

public class JotEngine implements ActionListener, MouseListener {
    public static String appName = "Jot";
    public static String buildVersion = "0.0.1";
    public static String windowCaption = appName + " " + buildVersion;

    // public static JTextField console;

    Dimension defaultSize = new Dimension(1280, 640);

    public JotComponents wc;
    public static Timer timer;

    public static JotEngine home;
    public static JFrame frame;

    public static JTabbedPane tabbedPane;

    public JotEngine() {
        frame = new JFrame(windowCaption);
        frame.setSize(defaultSize);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon("res/icon.png").getImage());

        home = this;
        init();
    }

    public void init() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addMouseListener(this);
        tabbedPane.setFocusable(false);
        wc = new JotComponents();

        // the default tab
        // ! don't add any settings here, default docs are in JotDocument
        JotDocument doc = new JotDocument();
        tabbedPane.addTab(doc.getName(), doc);

        updateNameJE(doc.getName() + " - " + windowCaption);

        for (JMenuItem jm : wc.fileMenuItems)   jm.addActionListener(this);
        for (JMenuItem jm : wc.editMenuItems)   jm.addActionListener(this);
        for (JMenuItem jm : wc.viewMenuItems)   jm.addActionListener(this);
        for (JMenuItem jm : wc.optionMenuItems) jm.addActionListener(this);

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
        // this.setTitle(n);
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

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
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
