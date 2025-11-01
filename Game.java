/**
 * Game class handles all the game logic for Hand Cricket.
 * Manages player turns, scoring, and game state.
 */
public class Game {
    private Player player1;
    private Player player2;
    private Player currentBatsman;
    private Player currentBowler;
    private boolean isGameOver;
    private boolean isFirstInnings;
    private int targetScore;
    private String statusMessage;
    
    /**
     * Constructor to initialize a new game with two players.
     * @param player1Name Name of the first player
     * @param player2Name Name of the second player
     */
    public Game(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.isGameOver = false;
        this.isFirstInnings = true;
        this.currentBatsman = player1;
        this.currentBowler = player2;
        this.targetScore = 0;
        this.statusMessage = "Welcome to Hand Cricket! " + player1Name + " is batting first.";
    }
    
    /**
     * Play a turn where both players select a number (0-6).
     * @param batsmanChoice The number chosen by the batsman (0-6)
     * @param bowlerChoice The number chosen by the bowler (0-6)
     * @return true if the batsman is out (numbers match), false otherwise
     */
    public boolean playTurn(int batsmanChoice, int bowlerChoice) {
        // Validate input
        if (batsmanChoice < 0 || batsmanChoice > 6 || bowlerChoice < 0 || bowlerChoice > 6) {
            statusMessage = "Invalid choice! Please select a number between 0 and 6.";
            return false;
        }
        
        // Check if numbers match (batsman is out)
        if (batsmanChoice == bowlerChoice) {
            statusMessage = currentBatsman.getName() + " is OUT! " + 
                           currentBatsman.getName() + " scored " + 
                           currentBatsman.getScore() + " runs.";
            
            // If first innings is over, switch roles for second innings
            if (isFirstInnings) {
                targetScore = currentBatsman.getScore();
                isFirstInnings = false;
                currentBatsman = player2;
                currentBowler = player1;
                statusMessage += " Target: " + (targetScore + 1) + " runs. " + 
                                currentBatsman.getName() + " is now batting.";
            } else {
                // Second innings over - game finished
                isGameOver = true;
                determineWinner();
            }
            return true; // Batsman is out
        } else {
            // Batsman scores runs
            currentBatsman.addRuns(batsmanChoice);
            statusMessage = currentBatsman.getName() + " scored " + batsmanChoice + " runs. " +
                           "Total: " + currentBatsman.getScore();
            
            // Check if target is achieved in second innings
            if (!isFirstInnings && currentBatsman.getScore() > targetScore) {
                isGameOver = true;
                determineWinner();
                statusMessage = currentBatsman.getName() + " WINS! Target achieved!";
            }
            
            return false; // Batsman is not out
        }
    }
    
    /**
     * Determine the winner of the game.
     */
    private void determineWinner() {
        if (player1.getScore() > player2.getScore()) {
            statusMessage = player1.getName() + " WINS! Final Score: " + 
                           player1.getScore() + " vs " + player2.getScore();
        } else if (player2.getScore() > player1.getScore()) {
            statusMessage = player2.getName() + " WINS! Final Score: " + 
                           player2.getScore() + " vs " + player1.getScore();
        } else {
            statusMessage = "It's a TIE! Both players scored " + 
                           player1.getScore() + " runs.";
        }
    }
    
    /**
     * Check if the game is over.
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver() {
        return isGameOver;
    }
    
    /**
     * Check if it's the first innings.
     * @return true if first innings, false if second innings
     */
    public boolean isFirstInnings() {
        return isFirstInnings;
    }
    
    /**
     * Get the current batsman.
     * @return The player who is currently batting
     */
    public Player getCurrentBatsman() {
        return currentBatsman;
    }
    
    /**
     * Get the current bowler.
     * @return The player who is currently bowling
     */
    public Player getCurrentBowler() {
        return currentBowler;
    }
    
    /**
     * Get player 1.
     * @return Player 1
     */
    public Player getPlayer1() {
        return player1;
    }
    
    /**
     * Get player 2.
     * @return Player 2
     */
    public Player getPlayer2() {
        return player2;
    }
    
    /**
     * Get the target score for the second innings.
     * @return The target score (0 if first innings)
     */
    public int getTargetScore() {
        return targetScore;
    }
    
    /**
     * Get the current status message.
     * @return The status message
     */
    public String getStatusMessage() {
        return statusMessage;
    }
    
    /**
     * Reset the game to start a new match.
     */
    public void resetGame() {
        player1.resetScore();
        player2.resetScore();
        isGameOver = false;
        isFirstInnings = true;
        currentBatsman = player1;
        currentBowler = player2;
        targetScore = 0;
        statusMessage = "New game started! " + player1.getName() + " is batting first.";
    }
}
