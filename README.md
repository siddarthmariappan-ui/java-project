# 🏏 Hand Cricket Game

A simple, interactive Hand Cricket game built with Java Swing that provides an engaging two-player gaming experience. This game simulates the classic hand cricket game where players use numbers (0-6) to represent their choices, similar to the popular street game.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Game Rules](#game-rules)
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [Class Documentation](#class-documentation)
- [Game Flow](#game-flow)
- [Screenshots & UI Details](#screenshots--ui-details)
- [Troubleshooting](#troubleshooting)
- [Future Enhancements](#future-enhancements)

---

## 🎯 Overview

Hand Cricket Game is a desktop application that allows two players to compete in a digital version of the traditional hand cricket game. The game features a clean, user-friendly interface with three main screens: an introduction screen, the main game screen, and an end screen. The application follows object-oriented principles with a modular design, making it easy to understand and extend.

**Key Highlights:**
- ✅ Beginner-friendly code structure
- ✅ Clean and intuitive graphical interface
- ✅ Real-time score tracking
- ✅ Two-innings gameplay
- ✅ No external dependencies required
- ✅ Fully documented codebase

---

## 🛠 Tech Stack

### Core Technologies

#### **Java SE (Standard Edition)**
- **Version:** Java 8 or higher
- **Purpose:** Core programming language for the entire application
- **Why Java:** Platform-independent, object-oriented, and well-suited for desktop applications

#### **Java Swing**
- **Package:** `javax.swing.*`
- **Purpose:** GUI framework for creating the graphical user interface
- **Components Used:**
  - `JFrame` - Main application window
  - `JPanel` - Container for organizing UI components
  - `JButton` - Interactive buttons for number selection
  - `JLabel` - Text display for scores, status, and instructions
  - `JTextField` - Input fields for player names
  - `CardLayout` - Layout manager for switching between screens
  - `BoxLayout` / `GridLayout` / `FlowLayout` - Various layout managers for component organization

#### **AWT (Abstract Window Toolkit)**
- **Package:** `java.awt.*`
- **Purpose:** Provides foundational classes for GUI components (used alongside Swing)
- **Used For:** Layout managers, colors, fonts, and event handling

#### **Java Event Handling**
- **Interface:** `ActionListener`
- **Purpose:** Handles user interactions (button clicks)
- **Implementation:** Inner classes and lambda expressions for event callbacks

### Technology Choices Explained

1. **Java Swing over JavaFX:**
   - Swing is included in standard JDK (no extra installation needed)
   - Sufficient for this simple game's requirements
   - Lightweight and straightforward for beginners

2. **CardLayout for Screen Management:**
   - Efficiently manages multiple screens (intro, game, end)
   - Easy to switch between panels
   - Maintains clean separation of UI components

3. **No External Libraries:**
   - Reduces dependencies and complexity
   - Easier for beginners to understand
   - No build tool configuration needed (Maven/Gradle)

### System Requirements

- **Operating System:** Windows, macOS, or Linux
- **Java Version:** JDK 8 or higher
- **Memory:** Minimum 128 MB RAM
- **Storage:** Less than 1 MB for source files
- **Display:** Any monitor resolution (minimum 600x500 pixels)

---

## 🎮 Game Rules

### Basic Rules

1. **Two Players:** The game requires exactly two players.

2. **Number Selection:**
   - Each player selects a number from **0 to 6**
   - These numbers represent the "fingers" shown in traditional hand cricket
   - 0 = No runs (dot ball)
   - 1-6 = Runs scored

3. **Gameplay Mechanics:**
   - **First Innings:** Player 1 bats, Player 2 bowls
   - **Batting:** The batsman chooses a number (0-6)
   - **Bowling:** The bowler chooses a number (0-6)
   - **If numbers match:** The batsman is **OUT** ❌
   - **If numbers don't match:** The batsman scores runs equal to their chosen number ✅

4. **Second Innings:**
   - After the first batsman is out, roles are swapped
   - Player 2 now bats, Player 1 bowls
   - Player 2's goal is to score more runs than Player 1's total score
   - The target score is Player 1's score + 1

5. **Winning Conditions:**
   - The player with the **higher total score** wins
   - If scores are equal, the game results in a **TIE**
   - In second innings, if the batting player exceeds the target before getting out, they win immediately

### Example Gameplay

```
First Innings:
Player 1 (Batsman) selects: 4
Player 2 (Bowler) selects: 2
Result: Numbers don't match → Player 1 scores 4 runs (Total: 4)

Player 1 (Batsman) selects: 3
Player 2 (Bowler) selects: 3
Result: Numbers match → Player 1 is OUT! Final score: 4 runs

Second Innings:
Target: 5 runs (Player 1's 4 + 1)
Player 2 (Batsman) selects: 2
Player 1 (Bowler) selects: 5
Result: Numbers don't match → Player 2 scores 2 runs (Total: 2)

Player 2 (Batsman) selects: 3
Player 1 (Bowler) selects: 1
Result: Numbers don't match → Player 2 scores 3 runs (Total: 5)

Winner: Player 2 wins! (5 runs > 4 runs)
```

---

## ✨ Features

### Current Features

1. **User-Friendly Interface**
   - Clean and intuitive design
   - Color-coded buttons for easy identification
   - Clear status messages and instructions

2. **Three-Screen Navigation**
   - **Introduction Screen:** Welcome page with rules and player name input
   - **Game Screen:** Main gameplay interface with score tracking
   - **End Screen:** Results display with play again option

3. **Real-Time Updates**
   - Live score tracking
   - Instant status messages
   - Dynamic target score display (during second innings)

4. **Input Validation**
   - Prevents invalid number selections
   - Ensures proper turn sequence

5. **Game State Management**
   - Tracks innings, scores, and player roles
   - Automatically determines winners

6. **Replayability**
   - "Play Again" button to restart without closing the application
   - Easy exit option

---

## 📁 Project Structure

```
java_project/
│
├── Player.java              # Player class - represents a game player
├── Game.java                # Game class - core game logic
├── HandCricketUI.java       # UI class - Swing interface implementation
├── HandCricketGame.java     # Main class - application entry point
└── README.md                # This documentation file
```

### File Organization

- **Total Files:** 4 Java source files + 1 README
- **Total Lines of Code:** ~650 lines (including comments)
- **Code Organization:** Each class in its own file for modularity

---

## 💻 Installation & Setup

### Prerequisites

1. **Install Java Development Kit (JDK)**
   - Download from: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
   - Verify installation:
     ```bash
     java -version
     javac -version
     ```
   - Both commands should display version information (JDK 8 or higher)

2. **Set JAVA_HOME (Optional but Recommended)**
   - **Windows:**
     - Add to Environment Variables: `JAVA_HOME = C:\Program Files\Java\jdk-X.X.X`
   - **Linux/macOS:**
     - Add to `.bashrc` or `.zshrc`: `export JAVA_HOME=/path/to/jdk`

### Download Project Files

1. Ensure all Java files are in the same directory:
   - `Player.java`
   - `Game.java`
   - `HandCricketUI.java`
   - `HandCricketGame.java`

2. Verify file structure matches the project structure above.

---

## 🚀 How to Run

### Step 1: Open Terminal/Command Prompt

- **Windows:** Press `Win + R`, type `cmd`, press Enter
- **macOS:** Press `Cmd + Space`, type `Terminal`, press Enter
- **Linux:** Press `Ctrl + Alt + T`

### Step 2: Navigate to Project Directory

```bash
cd D:\java_project
```

(Replace with your actual project path)

### Step 3: Compile Java Files

```bash
javac *.java
```

**Expected Output:** No errors (all files compile successfully)

**What This Does:**
- Compiles all `.java` files in the current directory
- Generates `.class` files (bytecode) for each Java class
- Checks for syntax errors

**Troubleshooting Compilation:**
- If you see errors, check:
  - All 4 Java files are in the same directory
  - Java JDK is properly installed
  - File names match class names exactly (case-sensitive)

### Step 4: Run the Application

```bash
java HandCricketGame
```

**Expected Output:**
- A window opens displaying the introduction screen
- The game is ready to play!

**Alternative Run Command (Explicit Classpath):**
```bash
java -cp . HandCricketGame
```

### Quick Start Scripts

#### Windows (run.bat)
Create a file named `run.bat`:
```batch
@echo off
javac *.java
java HandCricketGame
pause
```

#### Linux/macOS (run.sh)
Create a file named `run.sh`:
```bash
#!/bin/bash
javac *.java
java HandCricketGame
```

Make it executable:
```bash
chmod +x run.sh
```

---

## 📚 Class Documentation

### 1. Player.java

**Purpose:** Represents a player in the game with name and score tracking.

**Class Structure:**
```java
public class Player
```

**Fields:**
- `private String name` - Player's name
- `private int score` - Player's current score

**Methods:**
- `Player(String name)` - Constructor: Creates a new player with given name
- `String getName()` - Returns the player's name
- `int getScore()` - Returns the player's current score
- `void addRuns(int runs)` - Adds runs to the player's score
- `void resetScore()` - Resets score to zero (for new game)
- `String toString()` - Returns formatted string with name and score

**Usage Example:**
```java
Player player1 = new Player("Alice");
player1.addRuns(5);
System.out.println(player1.getScore()); // Output: 5
```

---

### 2. Game.java

**Purpose:** Manages all game logic, state, and turn-based gameplay.

**Class Structure:**
```java
public class Game
```

**Fields:**
- `private Player player1` - First player
- `private Player player2` - Second player
- `private Player currentBatsman` - Player currently batting
- `private Player currentBowler` - Player currently bowling
- `private boolean isGameOver` - Game completion status
- `private boolean isFirstInnings` - Innings indicator
- `private int targetScore` - Target for second innings
- `private String statusMessage` - Current game status text

**Methods:**
- `Game(String player1Name, String player2Name)` - Constructor: Initializes new game
- `boolean playTurn(int batsmanChoice, int bowlerChoice)` - Processes one turn, returns true if batsman is out
- `boolean isGameOver()` - Checks if game has ended
- `boolean isFirstInnings()` - Returns true if first innings is active
- `Player getCurrentBatsman()` - Returns current batsman
- `Player getCurrentBowler()` - Returns current bowler
- `Player getPlayer1()` - Returns player 1
- `Player getPlayer2()` - Returns player 2
- `int getTargetScore()` - Returns target score (0 if first innings)
- `String getStatusMessage()` - Returns current status message
- `void resetGame()` - Resets game for new match
- `private void determineWinner()` - Internal method to determine game winner

**Key Logic Flow:**
1. `playTurn()` compares batsman and bowler choices
2. If match → batsman out, switch innings if needed
3. If no match → add runs to batsman's score
4. Check win conditions after each turn

**Usage Example:**
```java
Game game = new Game("Alice", "Bob");
boolean isOut = game.playTurn(4, 2); // Alice bats 4, Bob bowls 2
// Result: isOut = false, Alice scores 4 runs
```

---

### 3. HandCricketUI.java

**Purpose:** Creates and manages the entire graphical user interface using Java Swing.

**Class Structure:**
```java
public class HandCricketUI extends JFrame
```

**Fields:**
- `private Game game` - Game instance
- `private JPanel mainPanel` - Main container with CardLayout
- `private JPanel introPanel` - Introduction screen
- `private JPanel gamePanel` - Main game screen
- `private JPanel endPanel` - End game screen
- Various UI components (buttons, labels, text fields)

**Key Methods:**
- `HandCricketUI()` - Constructor: Sets up the entire UI
- `private void createIntroPanel()` - Creates introduction screen
- `private void createGamePanel()` - Creates main game screen
- `private void createEndPanel()` - Creates end game screen
- `private void playTurn()` - Handles turn execution
- `private void updateGameDisplay()` - Updates UI with current game state
- `private void showIntroPanel()` - Shows introduction screen
- `private void showGamePanel()` - Shows game screen
- `private void showEndPanel()` - Shows end screen
- `private void enableBatsmanButtons(boolean enable)` - Enables/disables batsman buttons

**UI Design Details:**
- **Colors Used:**
  - Background: Light blue (#F0F8FF) for intro/end, Beige (#FFF8F0) for game
  - Batsman buttons: Steel blue (#4682B4)
  - Bowler buttons: Firebrick (#B22222)
  - Start button: Forest green (#228B22)
  - Exit button: Crimson (#DC143C)

- **Layout Strategy:**
  - CardLayout for screen switching
  - BoxLayout for vertical stacking
  - GridLayout for button arrays
  - FlowLayout for input fields

**Event Handling:**
- ActionListeners on all buttons
- Sequential input flow (batsman selects first, then bowler)
- Real-time UI updates after each turn

---

### 4. HandCricketGame.java

**Purpose:** Main entry point that launches the application.

**Class Structure:**
```java
public class HandCricketGame
```

**Methods:**
- `public static void main(String[] args)` - Entry point, creates and displays UI

**Key Implementation:**
- Uses `SwingUtilities.invokeLater()` to ensure thread-safe GUI creation
- Sets system look and feel for native appearance
- Creates `HandCricketUI` instance and makes it visible

**Why SwingUtilities.invokeLater()?**
- Swing components must be created on the Event Dispatch Thread (EDT)
- Prevents thread-safety issues
- Ensures proper GUI initialization

**Usage:**
```bash
java HandCricketGame
```

---

## 🎬 Game Flow

### Complete Game Sequence

1. **Application Start**
   ```
   User runs: java HandCricketGame
   → HandCricketGame.main() is called
   → HandCricketUI window is created
   → Introduction screen is displayed
   ```

2. **Introduction Screen**
   ```
   User sees: Welcome message, rules, input fields
   → User enters Player 1 name (or uses default)
   → User enters Player 2 name (or uses default)
   → User clicks "Start Game"
   → Game object is created with player names
   → Game screen is displayed
   ```

3. **First Innings - Gameplay Loop**
   ```
   Display: "Player 1 is batting, Player 2 is bowling"
   → Batsman selects number (0-6)
   → Buttons disabled, "Waiting for bowler" message shown
   → Bowler selects number (0-6)
   → Game.playTurn() is called
   → If numbers match: Batsman out, innings ends
   → If numbers don't match: Score added, continue
   → Loop continues until batsman is out
   ```

4. **Innings Transition**
   ```
   First innings ends (batsman out)
   → Target score calculated (Player 1 score + 1)
   → Roles swapped: Player 2 bats, Player 1 bowls
   → Game screen updated with target score
   → Second innings begins
   ```

5. **Second Innings - Gameplay Loop**
   ```
   Display: "Target: X runs. Player 2 is batting"
   → Same gameplay loop as first innings
   → Additional check: If Player 2 exceeds target → Win immediately
   → If Player 2 gets out: Compare scores
   ```

6. **Game End**
   ```
   Game determines winner:
   → If Player 2 score > Player 1 score → Player 2 wins
   → If Player 1 score > Player 2 score → Player 1 wins
   → If scores equal → Tie
   → End screen is displayed
   → Shows winner and final scores
   ```

7. **Post-Game Options**
   ```
   User can:
   → Click "Play Again" → Returns to introduction screen
   → Click "Exit" → Closes application
   ```

### State Transitions

```
[Introduction] → (Start Game) → [Game Screen] → (Game Over) → [End Screen]
                                                        ↓
                                                 (Play Again)
                                                        ↓
                                                 [Introduction]
```

---

## 🖼 Screenshots & UI Details

### Introduction Screen
- **Title:** "🏏 Hand Cricket Game 🏏" (large, bold font)
- **Instructions:** Game rules displayed in readable format
- **Input Fields:** Two text fields for player names (default: "Player 1", "Player 2")
- **Start Button:** Large green button to begin game

### Game Screen
- **Status Label:** Shows current game events and results
- **Score Display:** Both players' scores shown side by side
- **Target Score:** Displayed only during second innings (red text)
- **Batsman/Bowler Labels:** Shows current roles
- **Number Buttons:**
  - **Batsman Section:** 7 blue buttons (0-6)
  - **Bowler Section:** 7 red buttons (0-6)
- **Waiting Message:** Appears after batsman selects number

### End Screen
- **Winner Announcement:** Large text showing winner
- **Final Scores:** Detailed score breakdown for both players
- **Play Again Button:** Green button to restart
- **Exit Button:** Red button to close application

---

## 🔧 Troubleshooting

### Common Issues and Solutions

#### Issue 1: "javac is not recognized"
**Problem:** Java compiler not found in PATH

**Solutions:**
- Verify JDK installation: `java -version`
- Add JDK bin directory to PATH environment variable
- Use full path to javac: `C:\Program Files\Java\jdk-X\bin\javac *.java`

#### Issue 2: "Error: Could not find or load main class HandCricketGame"
**Problem:** Class files not found or wrong directory

**Solutions:**
- Ensure you're in the directory containing `.class` files
- Recompile: `javac *.java`
- Check file name matches class name exactly (case-sensitive)
- Use: `java -cp . HandCricketGame`

#### Issue 3: Window doesn't appear
**Problem:** GUI not displaying

**Solutions:**
- Check if window opened behind other windows
- Verify graphics display is working
- Try running with: `java -Djava.awt.headless=false HandCricketGame`

#### Issue 4: Buttons not responding
**Problem:** Event handling issues

**Solutions:**
- Ensure all Java files compiled without errors
- Restart the application
- Check for console error messages

#### Issue 5: Scores not updating
**Problem:** Game logic or UI update issue

**Solutions:**
- Verify both players selected numbers
- Check console for error messages
- Ensure `updateGameDisplay()` is called after each turn

### Debugging Tips

1. **Enable Console Output:**
   - Add `System.out.println()` statements in code
   - Check console for error messages

2. **Verify Compilation:**
   ```bash
   javac -verbose *.java
   ```

3. **Run with Error Details:**
   ```bash
   java -Xcheck:jni HandCricketGame
   ```

---

## 🚀 Future Enhancements

### Potential Improvements

1. **Enhanced Graphics**
   - Add animations for number selection
   - Include cricket-themed icons and images
   - Improve visual feedback for scoring events

2. **Additional Features**
   - Difficulty levels (AI opponent)
   - Tournament mode (multiple matches)
   - Statistics tracking (win/loss records)
   - Sound effects for scoring and wickets

3. **Game Variations**
   - Overs-based gameplay (limited turns)
   - Power-play mode (bonus runs for certain numbers)
   - Multiple difficulty modes

4. **Technical Improvements**
   - Save/Load game state
   - Settings menu (themes, colors)
   - High score leaderboard
   - Network multiplayer support

5. **Code Enhancements**
   - Unit testing framework
   - Configuration file support
   - Internationalization (multiple languages)

---

## 📝 Code Style & Best Practices

### Conventions Followed

1. **Naming:**
   - Classes: PascalCase (e.g., `HandCricketUI`)
   - Methods: camelCase (e.g., `playTurn()`)
   - Variables: camelCase (e.g., `currentBatsman`)
   - Constants: UPPER_SNAKE_CASE (if applicable)

2. **Comments:**
   - JavaDoc comments for all classes and public methods
   - Inline comments for complex logic
   - Section comments for major code blocks

3. **Structure:**
   - One class per file
   - Clear separation of concerns
   - Modular design for easy maintenance

4. **Error Handling:**
   - Try-catch blocks where needed
   - Graceful degradation (continue with defaults if L&F fails)

---

## 📄 License

This project is provided as-is for educational purposes. Feel free to modify and extend it for learning or personal use.

---

## 👨‍💻 Author

Created as an educational project to demonstrate Java Swing GUI development and object-oriented programming principles.

---

## 🙏 Acknowledgments

- Inspired by the traditional hand cricket game played in many countries
- Built using standard Java libraries (Swing, AWT)
- Designed with beginner-friendly principles in mind

---

## 📞 Support

For issues or questions:
1. Check the [Troubleshooting](#troubleshooting) section
2. Review the code comments for implementation details
3. Verify Java installation and version compatibility

---

**Last Updated:** 2024
**Version:** 1.0
**Status:** ✅ Fully Functional

---

*Enjoy playing Hand Cricket! 🏏*
