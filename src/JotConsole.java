import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.BorderFactory;

public class JotConsole extends JTextField {
    public JotConsole(String msg) {
        super(msg);

        setBackground(new Color(31, 39, 42));
        setForeground(new Color(232, 232, 211));
        setFont(new Font("Consolas", Font.PLAIN, 20));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }
}
