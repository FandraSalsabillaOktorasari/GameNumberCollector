import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameGUI extends JFrame {
    private BinaryGame game;
    private Player player;

    // Komponen UI
    private JLabel lblTitle, lblKesempatan, lblFeedback;
    private JComboBox<String> cmbMode;
    private JTextField txtInput;
    private JButton btnSubmit, btnReset;
    private JTextArea txtLog;

    public GameGUI() {
        game = new BinaryGame();
        player = new Player(7);

        initUI();
        startNewSession();
    }

    private void initUI() {
        setTitle("Project PBO: Binary Search Game");
        setSize(400, 580);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(new Color(245, 245, 245));

        // 1. HEADER
        lblTitle = new JLabel("TEBAK ANGKA OOP");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblKesempatan = new JLabel("Nyawa: 7");
        lblKesempatan.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblKesempatan.setForeground(new Color(220, 50, 50));
        lblKesempatan.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 2. INPUT (Hanya 2 Opsi Sekarang)
        // \u2265 adalah kode unicode untuk simbol >=
        // \u2264 adalah kode unicode untuk simbol <=
        String[] options = {
                "1. Apakah \u2265 X ? (Lebih Besar / Sama)",
                "2. Apakah \u2264 X ? (Lebih Kecil / Sama)"
        };
        cmbMode = new JComboBox<>(options);
        cmbMode.setMaximumSize(new Dimension(350, 35));

        txtInput = new JTextField();
        txtInput.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtInput.setMaximumSize(new Dimension(350, 40));
        txtInput.setHorizontalAlignment(JTextField.CENTER);

        // 3. BUTTON
        btnSubmit = new JButton("CEK PERNYATAAN");
        btnSubmit.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSubmit.setBackground(new Color(50, 120, 220));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSubmit.setMaximumSize(new Dimension(350, 40));

        // 4. FEEDBACK
        lblFeedback = new JLabel("Mulai permainan...");
        lblFeedback.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblFeedback.setForeground(Color.DARK_GRAY);
        lblFeedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFeedback.setBorder(new EmptyBorder(10, 0, 10, 0));

        // 5. LOG
        txtLog = new JTextArea(8, 20);
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtLog);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Log Aktivitas"));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 6. RESET
        btnReset = new JButton("ULANGI GAME");
        btnReset.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReset.setEnabled(false);

        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(lblKesempatan);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(cmbMode);
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

    private void startNewSession() {
        game.generateNumber();
        player.reset(7);
        updateUIStatus();
        lblFeedback.setText("Game baru dimulai!");
        lblFeedback.setForeground(Color.BLACK);
        txtLog.setText("");
        txtInput.setText("");
        enableControls(true);
        txtInput.requestFocus();
    }

    private void updateUIStatus() {
        lblKesempatan.setText("Nyawa: " + player.getLives());
    }

    private void prosesTebakan() {
        String inputStr = txtInput.getText();
        if (inputStr.isEmpty() || !inputStr.matches("\\d+")) {
            lblFeedback.setText("⚠️ Masukkan angka valid!");
            return;
        }

        int nilaiX = Integer.parseInt(inputStr);
        int mode = cmbMode.getSelectedIndex(); // 0 = (>=), 1 = (<=)

        // 1. AUTO-WIN CHECK (Cek Equality Dulu)
        // Walaupun user memilih opsi "Lebih Besar/Kecil", jika angkanya TEPAT,
        // kita anggap user MENANG. Ini mekanisme pengganti tombol "Tebak".
        GuessResult directCheck = game.checkGuess(nilaiX);

        if (directCheck == GuessResult.CORRECT) {
            lblFeedback.setText("🎉 BENAR! Jawabannya " + nilaiX);
            lblFeedback.setForeground(new Color(0, 150, 0));
            txtLog.append("✓ Input " + nilaiX + " -> TEPAT SEKALI! (WIN)\n");
            endGame(true);
            return;
        }

        // 2. Logic Pernyataan (Hinting)
        String aksi = "";
        boolean statementBenar = false;

        switch (mode) {
            case 0: // Apakah >= X ?
                statementBenar = game.isGreaterThanOrEqual(nilaiX);
                aksi = "Rahasia \u2265 " + nilaiX + "?"; // Simbol >=
                tampilkanHasil(aksi, statementBenar);
                break;

            case 1: // Apakah <= X ?
                statementBenar = game.isLessThanOrEqual(nilaiX);
                aksi = "Rahasia \u2264 " + nilaiX + "?"; // Simbol <=
                tampilkanHasil(aksi, statementBenar);
                break;
        }

        // 3. Kurangi Nyawa & Cek Kalah
        player.decreaseLife();
        updateUIStatus();

        if (!player.isAlive()) {
            lblFeedback.setText("💀 GAME OVER! Angka: " + game.getTargetNumber());
            lblFeedback.setForeground(Color.RED);
            endGame(false);
        }

        txtInput.setText("");
        txtInput.requestFocus();
    }

    private void tampilkanHasil(String aksi, boolean isTrue) {
        if (isTrue) {
            lblFeedback.setText("✅ YA, BENAR.");
            lblFeedback.setForeground(new Color(0, 100, 0));
            txtLog.append("✓ " + aksi + " -> YA\n");
        } else {
            lblFeedback.setText("❌ TIDAK / SALAH.");
            lblFeedback.setForeground(Color.RED);
            txtLog.append("✕ " + aksi + " -> TIDAK\n");
        }
    }

    private void enableControls(boolean enable) {
        txtInput.setEnabled(enable);
        btnSubmit.setEnabled(enable);
        cmbMode.setEnabled(enable);
        btnReset.setEnabled(!enable);
    }

    private void endGame(boolean win) {
        enableControls(false);
        if(win) JOptionPane.showMessageDialog(this, "Selamat! Anda menemukan angkanya.");
    }
}