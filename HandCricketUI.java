import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HandCricketUI class provides the graphical user interface for the Hand Cricket game.
 * Player bats against computer's bowling.
 */
public class HandCricketUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int MAX_NAME_LENGTH = 20;
    private static final Color PRIMARY_COLOR = new Color(240, 248, 255);
    private static final Color SUCCESS_COLOR = new Color(34, 139, 34);
    private static final Color DANGER_COLOR = new Color(220, 20, 60);
    private static final Color PLAYER_COLOR = new Color(70, 130, 180);
    
    // Main panels
    private final JPanel mainPanel;
    private final JPanel introPanel;
    private final JPanel gamePanel;
    private final JPanel endPanel;
    
    // Game state and UI Components
    private Game game;
    private int playerChoice;
    private JLabel statusLabel;
    private JLabel scoreLabel;
    private JButton[] numberButtons;
    private JButton playButton;
    private JTextField playerNameField;

    /**
     * Constructor initializes the UI components and sets up the game window.
     */
    public HandCricketUI() {
        setTitle("Hand Cricket Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        
        mainPanel = new JPanel(new CardLayout());
        introPanel = new JPanel();
        gamePanel = new JPanel();
        endPanel = new JPanel();
        
        this.playerChoice = -1;
        
        setupIntroPanel();
        setupGamePanel();
        setupEndPanel();
        
        mainPanel.add(introPanel, "intro");
        mainPanel.add(gamePanel, "game");
        mainPanel.add(endPanel, "end");
        add(mainPanel);
        
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "intro");
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void setupIntroPanel() {
        introPanel.setLayout(new GridBagLayout());
        introPanel.setBackground(PRIMARY_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel titleLabel = new JLabel("Welcome to Hand Cricket!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        introPanel.add(titleLabel, gbc);
        
        JLabel nameLabel = new JLabel("Enter your name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        introPanel.add(nameLabel, gbc);
        
        playerNameField = new JTextField(20);
        gbc.gridx = 1;
        introPanel.add(playerNameField, gbc);
        
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> startGame());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        introPanel.add(startButton, gbc);
    }
    
    private void setupGamePanel() {
        gamePanel.setLayout(new BorderLayout(10, 10));
        gamePanel.setBackground(PRIMARY_COLOR);
        
        // Status panel at the top
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusPanel.setBackground(PRIMARY_COLOR);
        statusLabel = new JLabel("Your turn to bat!");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusPanel.add(statusLabel);
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(scoreLabel);
        gamePanel.add(statusPanel, BorderLayout.NORTH);
        
        // Number buttons panel (0-6)
        JPanel numbersPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        numbersPanel.setBackground(PRIMARY_COLOR);
        numberButtons = new JButton[7];
        
        for (int i = 0; i < 7; i++) {
            final int num = i;
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberButtons[i].setBackground(PLAYER_COLOR);
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].addActionListener(e -> handlePlayerChoice(num));
            numbersPanel.add(numberButtons[i]);
        }
        
        gamePanel.add(numbersPanel, BorderLayout.CENTER);
        
        // Play button panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.setBackground(PRIMARY_COLOR);
        playButton = new JButton("Play!");
        playButton.setFont(new Font("Arial", Font.BOLD, 20));
        playButton.addActionListener(e -> handlePlay());
        playButton.setEnabled(false);
        controlPanel.add(playButton);
        gamePanel.add(controlPanel, BorderLayout.SOUTH);
    }
    
    private void setupEndPanel() {
        endPanel.setLayout(new GridBagLayout());
        endPanel.setBackground(PRIMARY_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        endPanel.add(gameOverLabel, gbc);
        
        JButton restartButton = new JButton("Play Again");
        restartButton.addActionListener(e -> restartGame());
        gbc.gridy = 1;
        endPanel.add(restartButton, gbc);
    }
    
    private void startGame() {
        String playerName = playerNameField.getText().trim();
        if (playerName.isEmpty() || playerName.length() > MAX_NAME_LENGTH) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid name (1-20 characters)",
                "Invalid Name",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        game = new Game(playerName);
        resetGameState();
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "game");
        updateUI();
    }
    
    private void handlePlayerChoice(int num) {
        playerChoice = num;
        for (JButton button : numberButtons) {
            button.setEnabled(false);
        }
        playButton.setEnabled(true);
        statusLabel.setText("You chose " + num + ". Click Play!");
    }
    
    private void handlePlay() {
        if (playerChoice == -1) {
            statusLabel.setText("Please choose a number first!");
            return;
        }
        
        boolean isOut = game.playTurn(playerChoice);
        
        String resultText = game.getStatusMessage();
        if (isOut) {
            showGameOver();
        } else {
            resetTurn();
        }
        
        statusLabel.setText(resultText);
        scoreLabel.setText("Score: " + game.getPlayerScore());
    }
    
    private void resetTurn() {
        playerChoice = -1;
        for (JButton button : numberButtons) {
            button.setEnabled(true);
        }
        playButton.setEnabled(false);
    }
    
    private void resetGameState() {
        playerChoice = -1;
        if (numberButtons != null) {
            for (JButton button : numberButtons) {
                button.setEnabled(true);
            }
        }
        if (playButton != null) {
            playButton.setEnabled(false);
        }
    }
    
    private void showGameOver() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "end");
        
        String gameOverMessage = String.format("%s's final score: %d",
            game.getPlayer().getName(), game.getPlayerScore());
        JOptionPane.showMessageDialog(this, gameOverMessage, "Game Over",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void restartGame() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "intro");
        playerNameField.setText("");
        resetGameState();
    }
    
    private void updateUI() {
        scoreLabel.setText("Score: " + game.getPlayerScore());
        statusLabel.setText(game.getStatusMessage());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HandCricketUI());
    }
}