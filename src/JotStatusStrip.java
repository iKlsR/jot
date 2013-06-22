import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.BorderFactory;

// format
// Spaces (4)

public class JotStatusStrip extends JLabel {
    public JotStatusStrip() {
        this("", new Color(255, 255, 255, 255));
    }

    public JotStatusStrip(String s, Color c) {
        super(" " + s);

        setOpaque(true);
        setBackground(new Color(29, 29, 30));
        setForeground(c);
        setFont(new Font("Consolas", Font.PLAIN, 20));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }

    public void updateStrip(int spaces) {
        setText("  " + "Spaces (" + spaces + ")");
    }
}
