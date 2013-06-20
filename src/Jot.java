import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Insets;
import java.awt.Color;

public class Jot {
    public static void main(String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("err..");
                }
                new JotEngine().frame.setVisible(true);
            }
        });
    }
}
