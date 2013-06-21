import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class JotStatusStrip extends JLabel {
    private static String strip;

    // public JotStatusStrip() {
    //     this(strip);
    // }

    public JotStatusStrip(String s) {
        super(s);
        this.strip = s;

        setOpaque(true);
        setBackground(new Color(29, 29, 30));
        setForeground(new Color(243, 156, 18));
        setFont(new Font("Consolas", Font.PLAIN, 20));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }

    // @Override
    // public void setText(String s) {
    //     this.strip = s;
    // }
}
