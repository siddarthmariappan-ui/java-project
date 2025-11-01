/**
 * Player class represents a player in the Hand Cricket game.
 * Each player has a name and a score.
 */
public class Player {
    private String name;
    private int score;
    
    /**
     * Constructor to create a new player.
     * @param name The name of the player
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
    
    /**
     * Get the player's name.
     * @return The player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the player's current score.
     * @return The player's score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Add runs to the player's score.
     * @param runs The number of runs to add
     */
    public void addRuns(int runs) {
        this.score += runs;
    }
    
    /**
     * Reset the player's score to zero.
     */
    public void resetScore() {
        this.score = 0;
    }
    
    /**
     * Get a string representation of the player.
     * @return String with player name and score
     */
    @Override
    public String toString() {
        return name + " - Score: " + score;
    }
}
