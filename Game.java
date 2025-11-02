import java.util.Random;

/**
 * Game class handles all the game logic for Hand Cricket.
 * Player bats against computer's bowling.
 */
public class Game {
    private Player player;
    private int playerScore;
    private boolean isGameOver;
    private String statusMessage;
    private Random random;
    private int lastComputerChoice;
    private int playerChoice;

    /**
     * Constructor to initialize game state with player name.
     * @param playerName Name of the human player
     */
    public Game(String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        this.player = new Player(playerName.trim());
        this.playerScore = 0;
        this.isGameOver = false;
        this.random = new Random();
        this.playerChoice = -1;
        this.lastComputerChoice = -1;
        this.statusMessage = "Welcome to Hand Cricket! " + playerName.trim() + " is batting.";
    }

    /**
     * Get computer's bowling choice (0-6).
     * @return A random number between 0 and 6
     */
    private int getComputerBowling() {
        return random.nextInt(7);
    }

    /**
     * Play a turn with player's batting choice. Computer's bowling is automatic.
     * @param playerBatting The number chosen by the player for batting (0-6)
     * @return true if the player is out (numbers match), false otherwise
     */
    public boolean playTurn(int playerBatting) {
        // Validate input
        if (playerBatting < 0 || playerBatting > 6) {
            statusMessage = "Invalid choice! Please select a number between 0 and 6.";
            return false;
        }

        this.playerChoice = playerBatting;
        this.lastComputerChoice = getComputerBowling();

        // Check if player is out (numbers match)
        if (playerBatting == lastComputerChoice) {
            isGameOver = true;
            statusMessage = "OUT! Computer bowled " + lastComputerChoice + ". " +
                          "Final score: " + playerScore + " runs.";
            return true;
        } else {
            // Add runs to score
            playerScore += playerBatting;
            statusMessage = "You scored " + playerBatting + " runs! " +
                          "(Computer bowled " + lastComputerChoice + ") " +
                          "Total score: " + playerScore;
            return false;
        }
    }

    // Getters
    public boolean isGameOver() { return isGameOver; }
    public int getPlayerScore() { return playerScore; }
    public String getStatusMessage() { return statusMessage; }
    public Player getPlayer() { return player; }
    public int getPlayerChoice() { return playerChoice; }
    public int getLastComputerChoice() { return lastComputerChoice; }

    /**
     * Reset the game to start a new match.
     */
    public void resetGame() {
        playerScore = 0;
        isGameOver = false;
        playerChoice = -1;
        lastComputerChoice = -1;
        statusMessage = "New game started! " + player.getName() + " is batting.";
    }
}