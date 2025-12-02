import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    /**
     * Constructor: Initializes the main menu window and its components.
     */
    public MainMenu() {
        initUI();
    }

    /**
     * Sets up the frame properties, layout, and adds UI elements.
     * <p>
     * Creates three buttons corresponding to the three difficulty levels
     * and assigns ActionListeners to trigger the game start.
     * </p>
     */
    private void initUI() {
        setTitle("Binary Search Game - Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        // title
        JLabel lblTitle = new JLabel("TEBAK ANGKA OOP", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(lblTitle);

        // difficulty option buttons
        JButton btnEasy = new JButton("Easy (1-10)");
        JButton btnMedium = new JButton("Medium (1-100)");
        JButton btnHard = new JButton("Hard (1-1000)");

        // button styling
        styleButton(btnEasy, new Color(100, 200, 100));
        styleButton(btnMedium, new Color(255, 200, 100));
        styleButton(btnHard, new Color(255, 100, 100));

        // action listeners
        btnEasy.addActionListener(e -> startGame(new EasyMode()));
        btnMedium.addActionListener(e -> startGame(new MediumMode()));
        btnHard.addActionListener(e -> startGame(new HardMode()));

        add(btnEasy);
        add(btnMedium);
        add(btnHard);
    }

    /**
     * Helper method to apply consistent visual styling to buttons.
     *
     * @param btn   The JButton object to be styled.
     * @param color The background color to apply.
     */
    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
    }

    /**
     * Transitions from the Main Menu to the actual Game Window.
     * <p>
     * This method closes (disposes) the menu window and opens a new
     * {@link GameGUI} injected with the selected game mode.
     * </p>
     *
     * @param selectedMode The concrete {@link GameMode} implementation
     * (Easy, Medium, or Hard) chosen by the user.
     */
    private void startGame(GameMode selectedMode) {
        // close menu
        this.dispose(); 
        
        // start game with the selected mode
        SwingUtilities.invokeLater(() -> {
            GameGUI game = new GameGUI(selectedMode); 
            game.setVisible(true);
        });
    }
}
