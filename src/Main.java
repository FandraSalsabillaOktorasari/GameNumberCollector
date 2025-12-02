import javax.swing.SwingUtilities;

public class Main {
    /**
     * The main method is the starting point of the Java application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Menjalankan GUI di Thread yang aman
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            gui.setVisible(true);
        });
    }
}