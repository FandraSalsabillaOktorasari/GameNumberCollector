import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameGUI extends JFrame {
    /**
     * The strategy object defining the current game rules (Range, Lives, Logic).
     */
    private GameMode gameMode;

    /**
     * The object managing the player's state (current lives).
     */
    private Player player;

    // components
    private JLabel lblTitle, lblKesempatan, lblFeedback;
    // private JComboBox<String> cmbMode;
    private JTextField txtInput;
    private JButton btnSubmit, btnReset;
    private JTextArea txtLog;

    /**
     * Constructor: Initializes the game window with a specific difficulty mode.
     * * @param mode The concrete {@link GameMode} implementation (e.g., EasyMode, HardMode)
     * that dictates the rules for this session.
     */
    public GameGUI(GameMode mode) {
        this.gameMode = mode;
        player = new Player(mode.getInitialLives());

        initUI();
        startNewSession();
    }

    /**
     * Configures the JFrame properties, layout managers, and UI components.
     * This method handles the visual styling and registers event listeners.
     */
    private void initUI() {
        setTitle("Project PBO: Binary Search Game");
        setSize(400, 580);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(new Color(245, 245, 245));

        // header
        lblTitle = new JLabel("Guess the Number!");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblKesempatan = new JLabel("Lives: 7");
        lblKesempatan.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblKesempatan.setForeground(new Color(220, 50, 50));
        lblKesempatan.setAlignmentX(Component.CENTER_ALIGNMENT);

        // input 
        String[] options = {
                "1. Apakah \u2265 X ? (Lebih Besar / Sama)",
                "2. Apakah \u2264 X ? (Lebih Kecil / Sama)"
        };
        // cmbMode = new JComboBox<>(options);
        // cmbMode.setMaximumSize(new Dimension(350, 35));

        txtInput = new JTextField();
        txtInput.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtInput.setMaximumSize(new Dimension(350, 40));
        txtInput.setHorizontalAlignment(JTextField.CENTER);

        // take a guess button 
        btnSubmit = new JButton("Take a Guess");
        btnSubmit.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSubmit.setBackground(new Color(50, 120, 220));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSubmit.setMaximumSize(new Dimension(350, 40));

        // feedback
        lblFeedback = new JLabel("starting...");
        lblFeedback.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblFeedback.setForeground(Color.DARK_GRAY);
        lblFeedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFeedback.setBorder(new EmptyBorder(10, 0, 10, 0));

        // guess log
        txtLog = new JTextArea(8, 20);
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtLog);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Guess History"));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // reset button
        btnReset = new JButton("Restart");
        btnReset.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReset.setEnabled(false);

        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(lblKesempatan);
        mainPanel.add(Box.createVerticalStrut(20));
        // mainPanel.add(cmbMode);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(txtInput);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(btnSubmit);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(lblFeedback);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnReset);

        add(mainPanel);

        // Event Listeners
        btnSubmit.addActionListener(e -> prosesTebakan());
        btnReset.addActionListener(e -> startNewSession());
        txtInput.addActionListener(e -> prosesTebakan());
    }

    /**
     * Resets the game session state.
     * <p>
     * Triggers the GameMode to generate a new random number, resets player lives,
     * clears the log, and re-enables controls.
     * </p>
     */
    private void startNewSession() {
        gameMode.generateNumber();
        player.reset(gameMode.getInitialLives());
        updateUIStatus();
        lblFeedback.setText("New game started!");
        lblFeedback.setForeground(Color.BLACK);
        txtLog.setText("");
        txtInput.setText("");
        enableControls(true);
        txtInput.requestFocus();
    }

    /**
     * Updates the UI label to reflect the current number of lives remaining.
     */
    private void updateUIStatus() {
        lblKesempatan.setText("Lives: " + player.getLives());
    }

    /**
     * The main game loop logic triggered by user submission.
     * <p>
     * 1. Validates input (must be numeric).
     * 2. Delegates comparison to {@code gameMode.checkGuess()}.
     * 3. Provides feedback ("Too Low", "Too Big", or "Correct").
     * 4. Updates logs and checks for Game Over / Win conditions.
     * </p>
     */
    private void prosesTebakan() {
        String inputStr = txtInput.getText();
        
        if (inputStr.isEmpty() || !inputStr.matches("\\d+")) {
            lblFeedback.setText("⚠️ Input valid number!");
            return;
        }

        int guess = Integer.parseInt(inputStr);

        GuessResult result = gameMode.checkGuess(guess);

        if (result == GuessResult.CORRECT) {
            lblFeedback.setText("Bingo! You nailed it! The number was " + guess);
            lblFeedback.setForeground(new Color(0, 150, 0));
            txtLog.append("✓ " + guess + " -> Correct! (WIN)\n");
            endGame(true);
        } else {
            player.decreaseLife();
            updateUIStatus();

            String hint = "";
            if (result == GuessResult.TOO_LOW) {
                hint = "Too Low";
                lblFeedback.setForeground(new Color(200, 100, 0));
            } else {
                hint = "Too Big";
                lblFeedback.setForeground(new Color(200, 50, 50));
            }
            
            lblFeedback.setText("❌ " + hint + "! Try again.");
            txtLog.append("• " + guess + " -> " + hint + "\n");

            // game over check
            if (!player.isAlive()) {
                lblFeedback.setText("💀 GAME OVER! Number: " + gameMode.getTargetNumber());
                lblFeedback.setForeground(Color.RED);
                endGame(false);
            } else {
                txtInput.setText("");
                txtInput.requestFocus();
            }
        }
    }

    /**
     * Toggles the interactive state of the game controls.
     *
     * @param enable {@code true} to enable inputs (during game),
     * {@code false} to disable (game over/win).
     */
    private void enableControls(boolean enable) {
        txtInput.setEnabled(enable);
        btnSubmit.setEnabled(enable);
        // cmbMode.setEnabled(enable);
        btnReset.setEnabled(!enable);
    }

    /**
     * Finalizes the game session.
     *
     * @param win {@code true} if the player won, {@code false} if lost.
     */
    private void endGame(boolean win) {
        enableControls(false);
        if(win) JOptionPane.showMessageDialog(this, "Bingo! You nailed it!");
    }
}
