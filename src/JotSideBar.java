import java.awt.Color;

import javax.swing.JList;
import javax.swing.DefaultListModel;

public class JotSideBar<E> extends JList<E> {
    public JotSideBar(DefaultListModel<E> listModel) {
        super(listModel);

        setBackground(new Color(30, 30, 30));
        setForeground(new Color(120, 120, 120));
        setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 15));
        setFocusable(false);
        setSelectionForeground(new Color(210, 210, 210));
        setSelectionBackground(new Color(210, 210, 210, 110));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
    }
}
