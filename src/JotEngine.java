import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.util.Timer;
import java.util.TimerTask;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

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
    public static JotStatusStrip docInfoStrip;
    public static JotStatusStrip langStrip;

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
        console = new JotConsole("");

        // the default tab, don't add any settings here, default settings are in JotDocument
        // really want to get rid of this, perhaps call all update functions from JotFile
        JotDocument doc = new JotDocument();
        doc.getText().addKeyListener(this);
        console.addActionListener(this);
        tabbedPane.addTab(doc.getName(), doc);

        updateNameJE(doc.getName() + " - " + windowCaption);
        statusStrip = new JotStatusStrip(" " + doc.getName(), new Color(243, 156, 18));

        docInfoStrip = new JotStatusStrip(" ", new Color(39, 174, 96));
        docInfoStrip.updateStrip(JotEventEngine.currentSpaces);

        langStrip = new JotStatusStrip("Java" + " ", new Color(189, 195, 199));

        for (JMenuItem jm : wc.fileMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.editMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.viewMenuItems)       jm.addActionListener(this);
        for (JMenuItem jm : wc.optionMenuItems)     jm.addActionListener(this);
        for (JMenuItem jm : wc.tabPopupMenuItems)   jm.addActionListener(this);
        for (JMenuItem jm : wc.docPopupMenuItems)   jm.addActionListener(this);

        // the foundation..
        frame.add(tabbedPane);
        frame.setJMenuBar(JotComponents.menuBar);
        jp.setLayout(new BorderLayout()); //

        jp.add(statusStrip, BorderLayout.WEST);
        jp.add(docInfoStrip, BorderLayout.CENTER);
        jp.add(langStrip, BorderLayout.EAST);

        jp.add(console, BorderLayout.SOUTH);
        frame.add(jp, BorderLayout.SOUTH);
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
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        JotEventEngine.keyPressedEvents(e);
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
