import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main class to start the Hand Cricket Game application.
 * This is the entry point of the program that initializes and displays the game UI.
 */
public class HandCricketGame {
    /**
     * Main method to launch the game.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create and show the game UI using Swing's Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                HandCricketUI gameUI = new HandCricketUI();
                gameUI.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }
    
    /**
     * Sets up the game's user interface.
     * Handles the UI initialization and look and feel.
     */
    private static void setupUI() {
        try {
            // Set the look and feel to system default for better appearance
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            logError("Failed to set system look and feel", e);
            // Continue with default look and feel
        }
        
        try {
            // Create and show the game window
            new HandCricketUI().setVisible(true);
        } catch (HeadlessException e) {
            showErrorAndExit("This game requires a graphical display", e);
        } catch (Exception e) {
            showErrorAndExit("Failed to initialize game UI", e);
        }
    }
    
    /**
     * Logs an error message to standard error.
     * @param message The error message
     * @param error The exception that occurred
     */
    private static void logError(String message, Exception error) {
        System.err.println(message + ": " + error.getMessage());
    }
    
    /**
     * Shows an error message dialog and exits the application.
     * @param message The error message to display
     * @param error The exception that occurred
     */
    private static void showErrorAndExit(String message, Exception error) {
        logError(message, error);
        
        try {
            JOptionPane.showMessageDialog(null,
                message + "\n\nError: " + error.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException dialogError) {
            // If dialog fails, we already logged to stderr
        }
        
        System.exit(1);
    }
}
