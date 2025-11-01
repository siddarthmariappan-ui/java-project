import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HandCricketUI class provides the graphical user interface for the Hand Cricket game.
 * It uses Java Swing to create an interactive game experience.
 */
public class HandCricketUI extends JFrame {
    private Game game;
    private JPanel mainPanel;
    private JPanel introPanel;
    private JPanel gamePanel;
    private JPanel endPanel;
    
    // Intro panel components
    private JTextField player1NameField;
    private JTextField player2NameField;
    private JButton startButton;
    
    // Game panel components
    private JLabel statusLabel;
    private JLabel scoreLabel;
    private JLabel targetLabel;
    private JLabel batsmanLabel;
    private JLabel bowlerLabel;
    private JButton[] batsmanButtons;
    private JLabel waitingLabel;
    private JLabel bowlerSelectionLabel;
    
    // End panel components
    private JLabel winnerLabel;
    private JLabel finalScoreLabel;
    private JButton playAgainButton;
    private JButton exitButton;
    
    private int batsmanChoice = -1;
    private int bowlerChoice = -1;
    
    /**
     * Constructor to initialize the UI.
     */
    public HandCricketUI() {
        setTitle("Hand Cricket Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Create card layout for different screens
        mainPanel = new JPanel(new CardLayout());
        
        createIntroPanel();
        createGamePanel();
        createEndPanel();
        
        mainPanel.add(introPanel, "INTRO");
        mainPanel.add(gamePanel, "GAME");
        mainPanel.add(endPanel, "END");
        
        add(mainPanel);
        
        // Show intro panel first
        showIntroPanel();
    }
    
    /**
     * Create the introduction/welcome panel.
     */
    private void createIntroPanel() {
        introPanel = new JPanel();
        introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
        introPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        introPanel.setBackground(new Color(240, 248, 255));
        
        // Title
        JLabel titleLabel = new JLabel("🏏 Hand Cricket Game 🏏");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        
        // Instructions
        JLabel instructionsLabel = new JLabel("<html><div style='text-align: center;'>" +
            "Welcome to Hand Cricket!<br><br>" +
            "Rules:<br>" +
            "• Two players take turns batting and bowling<br>" +
            "• Each player selects a number from 0 to 6<br>" +
            "• If numbers match, the batsman is OUT<br>" +
            "• Otherwise, the batsman scores that many runs<br>" +
            "• After first innings, players swap roles<br>" +
            "• The player with higher score wins!<br><br>" +
            "Enter player names to start:</div></html>");
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        // Player 1 input
        JPanel p1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1Panel.setBackground(new Color(240, 248, 255));
        JLabel p1Label = new JLabel("Player 1 Name:");
        p1Label.setFont(new Font("Arial", Font.BOLD, 14));
        player1NameField = new JTextField(15);
        player1NameField.setText("Player 1");
        p1Panel.add(p1Label);
        p1Panel.add(player1NameField);
        
        // Player 2 input
        JPanel p2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2Panel.setBackground(new Color(240, 248, 255));
        JLabel p2Label = new JLabel("Player 2 Name:");
        p2Label.setFont(new Font("Arial", Font.BOLD, 14));
        player2NameField = new JTextField(15);
        player2NameField.setText("Player 2");
        p2Panel.add(p2Label);
        p2Panel.add(player2NameField);
        
        // Start button
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setPreferredSize(new Dimension(150, 40));
        startButton.setBackground(new Color(34, 139, 34));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p1Name = player1NameField.getText().trim();
                String p2Name = player2NameField.getText().trim();
                
                if (p1Name.isEmpty()) p1Name = "Player 1";
                if (p2Name.isEmpty()) p2Name = "Player 2";
                
                game = new Game(p1Name, p2Name);
                showGamePanel();
            }
        });
        
        introPanel.add(titleLabel);
        introPanel.add(instructionsLabel);
        introPanel.add(p1Panel);
        introPanel.add(p2Panel);
        introPanel.add(Box.createVerticalStrut(20));
        introPanel.add(startButton);
    }
    
    /**
     * Create the main game panel.
     */
    private void createGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gamePanel.setBackground(new Color(255, 250, 240));
        
        // Status label
        statusLabel = new JLabel("Game Status");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Score label
        scoreLabel = new JLabel("Score");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Target label (for second innings)
        targetLabel = new JLabel("");
        targetLabel.setFont(new Font("Arial", Font.BOLD, 14));
        targetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        targetLabel.setForeground(new Color(220, 20, 60));
        
        // Batsman and bowler labels
        batsmanLabel = new JLabel("Batsman: ");
        batsmanLabel.setFont(new Font("Arial", Font.BOLD, 14));
        batsmanLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bowlerLabel = new JLabel("Bowler: ");
        bowlerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bowlerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Batsman selection section
        JLabel batsmanPrompt = new JLabel("Batsman - Select your number (0-6):");
        batsmanPrompt.setFont(new Font("Arial", Font.BOLD, 14));
        batsmanPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        batsmanPrompt.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        JPanel batsmanButtonPanel = new JPanel(new GridLayout(1, 7, 5, 5));
        batsmanButtonPanel.setBackground(new Color(255, 250, 240));
        batsmanButtons = new JButton[7];
        for (int i = 0; i < 7; i++) {
            final int num = i;
            batsmanButtons[i] = new JButton(String.valueOf(i));
            batsmanButtons[i].setFont(new Font("Arial", Font.BOLD, 16));
            batsmanButtons[i].setPreferredSize(new Dimension(60, 50));
            batsmanButtons[i].setBackground(new Color(70, 130, 180));
            batsmanButtons[i].setForeground(Color.WHITE);
            batsmanButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    batsmanChoice = num;
                    waitingLabel.setText("Waiting for bowler's selection...");
                    waitingLabel.setVisible(true);
                    enableBatsmanButtons(false);
                }
            });
            batsmanButtonPanel.add(batsmanButtons[i]);
        }
        
        waitingLabel = new JLabel("");
        waitingLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        waitingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        waitingLabel.setVisible(false);
        
        // Bowler selection section
        bowlerSelectionLabel = new JLabel("Bowler - Select your number (0-6):");
        bowlerSelectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bowlerSelectionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bowlerSelectionLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        JPanel bowlerButtonPanel = new JPanel(new GridLayout(1, 7, 5, 5));
        bowlerButtonPanel.setBackground(new Color(255, 250, 240));
        for (int i = 0; i < 7; i++) {
            final int num = i;
            JButton bowlerBtn = new JButton(String.valueOf(i));
            bowlerBtn.setFont(new Font("Arial", Font.BOLD, 16));
            bowlerBtn.setPreferredSize(new Dimension(60, 50));
            bowlerBtn.setBackground(new Color(178, 34, 34));
            bowlerBtn.setForeground(Color.WHITE);
            bowlerBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bowlerChoice = num;
                    playTurn();
                }
            });
            bowlerButtonPanel.add(bowlerBtn);
        }
        
        gamePanel.add(statusLabel);
        gamePanel.add(scoreLabel);
        gamePanel.add(targetLabel);
        gamePanel.add(Box.createVerticalStrut(10));
        gamePanel.add(batsmanLabel);
        gamePanel.add(bowlerLabel);
        gamePanel.add(Box.createVerticalStrut(20));
        gamePanel.add(batsmanPrompt);
        gamePanel.add(batsmanButtonPanel);
        gamePanel.add(waitingLabel);
        gamePanel.add(Box.createVerticalStrut(10));
        gamePanel.add(bowlerSelectionLabel);
        gamePanel.add(bowlerButtonPanel);
    }
    
    /**
     * Create the end game panel.
     */
    private void createEndPanel() {
        endPanel = new JPanel();
        endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.Y_AXIS));
        endPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        endPanel.setBackground(new Color(240, 248, 255));
        
        winnerLabel = new JLabel("Game Over");
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winnerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        winnerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        finalScoreLabel = new JLabel("Final Scores");
        finalScoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        finalScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        finalScoreLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        
        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 16));
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.setPreferredSize(new Dimension(150, 40));
        playAgainButton.setBackground(new Color(34, 139, 34));
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIntroPanel();
            }
        });
        
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setPreferredSize(new Dimension(150, 40));
        exitButton.setBackground(new Color(220, 20, 60));
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        endPanel.add(winnerLabel);
        endPanel.add(finalScoreLabel);
        endPanel.add(Box.createVerticalStrut(30));
        endPanel.add(playAgainButton);
        endPanel.add(Box.createVerticalStrut(10));
        endPanel.add(exitButton);
    }
    
    /**
     * Enable or disable batsman buttons.
     */
    private void enableBatsmanButtons(boolean enable) {
        for (JButton btn : batsmanButtons) {
            btn.setEnabled(enable);
        }
    }
    
    /**
     * Play a turn when both players have selected their numbers.
     */
    private void playTurn() {
        if (batsmanChoice == -1) {
            JOptionPane.showMessageDialog(this, 
                "Batsman must select a number first!", 
                "Invalid Move", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        boolean isOut = game.playTurn(batsmanChoice, bowlerChoice);
        
        // Update UI
        updateGameDisplay();
        
        // Reset choices
        batsmanChoice = -1;
        bowlerChoice = -1;
        waitingLabel.setVisible(false);
        enableBatsmanButtons(true);
        
        // Check if game is over
        if (game.isGameOver()) {
            showEndPanel();
        }
    }
    
    /**
     * Update the game display with current information.
     */
    private void updateGameDisplay() {
        statusLabel.setText(game.getStatusMessage());
        scoreLabel.setText("<html><b>" + game.getPlayer1().getName() + ":</b> " + 
                          game.getPlayer1().getScore() + " | " +
                          "<b>" + game.getPlayer2().getName() + ":</b> " + 
                          game.getPlayer2().getScore() + "</html>");
        
        batsmanLabel.setText("<html><b>Batsman:</b> " + 
                            game.getCurrentBatsman().getName() + 
                            " (" + game.getCurrentBatsman().getScore() + " runs)</html>");
        bowlerLabel.setText("<html><b>Bowler:</b> " + 
                           game.getCurrentBowler().getName() + "</html>");
        
        if (!game.isFirstInnings()) {
            targetLabel.setText("<html><b>Target:</b> " + 
                               (game.getTargetScore() + 1) + " runs to win</html>");
            targetLabel.setVisible(true);
        } else {
            targetLabel.setVisible(false);
        }
    }
    
    /**
     * Show the intro panel.
     */
    private void showIntroPanel() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "INTRO");
    }
    
    /**
     * Show the game panel.
     */
    private void showGamePanel() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "GAME");
        updateGameDisplay();
    }
    
    /**
     * Show the end panel.
     */
    private void showEndPanel() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "END");
        
        winnerLabel.setText(game.getStatusMessage());
        finalScoreLabel.setText("<html><div style='text-align: center;'>" +
                               "<b>Final Scores:</b><br>" +
                               game.getPlayer1().getName() + ": " + 
                               game.getPlayer1().getScore() + " runs<br>" +
                               game.getPlayer2().getName() + ": " + 
                               game.getPlayer2().getScore() + " runs</div></html>");
    }
}
