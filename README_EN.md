# Gomoku Manbo and Catch Manbo

## Overview
This document serves as a comprehensive guide for the games "Gomoku Manbo" and "Catch Manbo." Both games are developed using the Java Swing framework, featuring intuitive user interfaces and simple操作方法. Ensure that your computer has Java Development Kit (JDK) 1.8 or higher installed and choose an IDE that supports Java, such as IntelliJ IDEA or Eclipse.

First, unzip the game's source code. After importing the code into your IDE, open the `Main.java` file, which is the entry point for the game launcher. The game launcher provides two buttons, each corresponding to one of the games.

### Launching "Gomoku Manbo"
1. Run `Main.java` in your IDE.
2. After the game launcher interface appears, click the "Launch Gomoku Manbo" button.
3. The "Gomoku Manbo" game window will open, and you can start playing. Use your mouse to click on empty spaces on the board to place Manbo, click the "Restart" button to reset the game, or click the "Regret" button to undo the last move.

### Launching "Catch Manbo"
1. Run `Main.java` in your IDE.
2. After the game launcher interface appears, click the "Launch Catch Manbo" button.
3. The "Catch Manbo" game window will open. Use the keyboard arrow keys to control the movement of the Manbo head, aiming to catch the Manbo on the screen. After the game ends, click the "End Game" button to exit.

Both games use simple algorithms to implement their core functionalities. "Gomoku Manbo" uses the Depth-First Search (DFS) algorithm to determine the winner, while "Catch Manbo" controls the movement of the Manbo head through keyboard event listening and simple coordinate updates.

## Gomoku Manbo

### Feature Description
In "Gomoku Manbo," two players take turns placing black and white Manbo on a 15x15 board, with the goal of being the first to connect five of the same Manbo in a line. At the start of the game, one player takes the black Manbo and goes first. Players alternately click on empty positions on the board to place Manbo, one at a time. If a player successfully connects five Manbo in a line, either horizontally, vertically, or diagonally, they win. During the game, players can reset the board by clicking the "Restart" button to start a new game. If you need to undo the last move, you can use the "Regret" feature. This game is easy to learn, full of strategy, and suitable for players of all ages.

### Code Implementation Principles

#### Main Classes and Functions
- **StartChessJFrame**: The main frame class responsible for creating the main game window, initializing the board and toolbar, and handling player interactions with buttons.
- **ChessBoard**: This class inherits from JPanel and implements the MouseListener interface, responsible for drawing the board, responding to mouse events, and determining the game's outcome.
- **Point**: Represents a point on the board, which is a Manbo. It records the position and color of the Manbo, the basic unit that makes up the board layout. By managing each Point object, the game can track the precise placement of each Manbo on the board.

These classes work together to provide an intuitive and interactive Gomoku Manbo gaming experience. Through these well-designed classes and their methods, the game can handle complex logic and user input, ensuring smooth gameplay and correct rules enforcement.

#### Core Methods and Algorithms
- **paintComponent(Graphics g)**: This method is responsible for drawing the board, Manbo, and grid lines.
- **mousePressed(MouseEvent e)**: Handles mouse press events to determine the placement of Manbo.
- **isWin()**: Determines if the current move forms a winning line of five in a row.
- **restartGame()**: Resets the game and clears the board.
- **goback()**: The regret feature, used to undo the last move.

### Running and Operating
- Run `Main.java` to launch the game launcher and select "Launch Gomoku Manbo" to start the game.
- Use your mouse to click on empty spaces on the board to place Manbo.
- Click the "Restart" button to reset the game.
- Click the "Regret" button to undo the last move.

## Catch Manbo

### Feature Description
In "Catch Manbo," the player's goal is to control the Manbo head on the screen to catch randomly distributed Manbo. The gameplay is simple and intuitive; players use the keyboard's arrow keys to control the direction of the Manbo head to catch the Manbo on the screen. Each time the Manbo head coincides with a Manbo, the Manbo is "eaten," and the player's score increases.

### Code Implementation Principles

#### Main Classes and Functions
- **SnakeGame**: The main class responsible for creating the game window, initializing the game state, and handling game logic. It manages game startup, score calculation, and game-ending logic, serving as the control center for the entire game.
- **GamePanel**: Inherits from JPanel, the game panel class is responsible for drawing the game interface and responding to keyboard events. It interacts directly with player input, drawing the Manbo head and Manbo, and updating the screen after the game state changes.

These two classes work together to ensure smooth game operation and an interactive player experience. Players control the Manbo head with the keyboard to catch randomly appearing Manbo on the screen, with each successful catch increasing the score. The GamePanel class responds to player keyboard operations, updating the position of the Manbo head in real-time and updating the score when the Manbo head coincides with a Manbo.

#### Core Methods and Algorithms
- **paintComponent(Graphics g)**: Draws the game interface, including the Manbo head, Manbo, and score.
- **move()**: Controls the movement of the Manbo head.
- **checkApple()**: Checks if the Manbo head has caught a Manbo.
- **locateApple()**: Randomly generates the position of the Manbo.
- **TAdapter**: A keyboard listener adapter.

### Running and Operating
- Run `Main.java` to launch the game launcher and select "Launch Catch Manbo" to start the game.
- Use the keyboard arrow keys to control the movement of the Manbo.
- When you're done playing, click the "End Game" button to view your score.

## Runtime Environment
- Java Development Kit (JDK) 1.8 or higher.
- Any IDE that supports Java (such as IntelliJ IDEA, Eclipse, etc.).