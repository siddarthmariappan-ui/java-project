import javax.swing.UIManager;

/**
 * Main class to start the Hand Cricket Game application.
 * This is the entry point of the program.
 */
public class HandCricketGame {
    /**
     * Main method to launch the game.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create and show the game UI using Swing's Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Set the look and feel to system default for better appearance
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    // If setting look and feel fails, continue with default
                    e.printStackTrace();
                }
                
                // Create and display the game window
                HandCricketUI gameUI = new HandCricketUI();
                gameUI.setVisible(true);
            }
        });
    }
}
