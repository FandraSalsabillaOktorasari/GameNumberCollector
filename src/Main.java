import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {   
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("failed to load nimbus ui manager");
        }
        // Menjalankan GUI di Thread yang aman
        SwingUtilities.invokeLater(() -> {
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
            // GameGUI gui = new GameGUI();
            // gui.setVisible(true);
        });
    }
}
