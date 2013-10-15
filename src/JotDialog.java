import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class JotDialog extends JDialog {
    public JotDialog(JFrame frame, String str, JFrame parent) {
        super(frame, str);
        setResizable(false);
        setSize(320, 132);
        setLocationRelativeTo(parent);

        java.awt.Color fg = new java.awt.Color(240, 244, 245);

        JLabel appName = new JLabel("Jot 0.0.2");
        JLabel authorName = new JLabel("by Ricardo Lovelace");
        JLabel site = new JLabel("http://github.com/iKlsR/jot");
        JPanel panel = new JPanel();

        appName.setForeground(fg);
        authorName.setForeground(fg);
        site.setForeground(fg);

        appName.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 40));
        authorName.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 18));
        site.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 12));

        panel.setBackground(new java.awt.Color(62, 62, 62));
        panel.setLayout(new MigLayout());
        panel.add(appName, "push, align center, wrap");
        panel.add(authorName, "push, align center, wrap");
        panel.add(site, "push, align center");

        add(panel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                // System.exit(0);
                dispose();
            }
        });
    }
}
