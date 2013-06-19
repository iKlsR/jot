import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Jot {
    public static void main(String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("There was an error.");
                }
                new JotEngine().setVisible(true);
            }
        });
    }
}
