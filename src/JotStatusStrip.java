import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class JotStatusStrip extends JLabel {
    private static String strip;

    // public JotStatusStrip() {
    //     this(strip);
    // }

    public JotStatusStrip(String s, Color c) {
        super(" " + s);
        this.strip = s;

        setOpaque(true);
        setBackground(new Color(29, 29, 30));
        setForeground(c);
        setFont(new Font("Consolas", Font.PLAIN, 20));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }

    // @Override
    // public void setText(String s) {
    //     this.strip = s;
    // }
}
