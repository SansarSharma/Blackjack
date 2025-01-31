# Blackjack Console Game

This project is a console-based Blackjack game developed in Java. It was originally a lab assignment for the course COE318 at Toronto Metropolitan University. The project uses Maven as its build and dependency management tool.

## Features
- A fully functional Blackjack game playable through the terminal.
- Core game logic implemented in the `BlackjackGame` class.
- A card-handling system, including a `Card` and `CardPile` class for deck and hand management.
- A simple user interface provided by the `SimpleUI` class to interact with the game.
- Gameplay includes turn-based mechanics, scoring rules, and proper game state management.
- Object-Oriented Programming (OOP) principles with a clear separation between core game logic and user interface components.

## File Structure

- `com.game.core`: Contains the core logic of the game.
    - `BlackjackGame.java`: Handles the game state, rules, and turn management.
    - `Card.java`: Represents an individual card with rank, suit, and visibility.
    - `CardPile.java`: Manages a collection of cards (deck, player hand, house hand).
- `com.game.ui`: Manages user interaction with the game.
    - `SimpleUI.java`: Implements the user interface to display game status and handle player input.
    - `UserInterface.java`: Interface defining UI functions.
- `com.game.main`: Contains the main entry point for running the game.
    - `Main.java`: Orchestrates the game's lifecycle and user interaction.

## Requirements
- Java Development Kit (JDK) 8 or later
- Apache Maven 3.6 or later

## How to Run
1. Clone the repository or download the source code.
2. Navigate to the project directory.
3. Use Maven to compile and run the project:
   ```bash
   mvn compile  
   mvn exec:java -Dexec.mainClass="com.game.main.Main"
