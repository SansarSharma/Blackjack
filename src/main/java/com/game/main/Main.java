package com.game.main;

/**
 * Main.java
 * This is where the classes created will be instantiated and be used to run the game BlackJack
 */


import com.game.core.BlackjackGame;
import com.game.ui.SimpleUI;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Declaring Variables
        Scanner scanner = new Scanner(System.in); // Scanner is used to read user input for replaying the game
        boolean keepPlaying = true; // Boolean variable used to keep track of whether the player wants to keep playing

        // While loop to keep the game running until the player decides to stop
        while (keepPlaying) {

            // Instantiate the user interface and game
            // Creates a SimpleUI object, which handles interactions between the user and the game
            SimpleUI ui = new SimpleUI();

            // Creates a BlackjackGame object, which contains all the logic for running the game
            BlackjackGame game = new BlackjackGame(ui);

            // Run the game lifecycle
            // Start the game by initializing the state and dealing initial cards
            game.start();

            // Handles the main gameplay loop where the player and house take turns
            game.play();

            // Ends the game by revealing cards and announcing the winner
            game.end();

            // Ask if the user wants to play again
            // Prompts the user for input to determine if they want to replay the game
            System.out.println("\nWould you like to play again? (Y/N)\n");

            // Reads the user's response, trims whitespace, and converts it to lowercase for consistency
            String response = scanner.nextLine().trim().toLowerCase();

            // Checks if the user entered "n" (no)
            if (response.equals("n")) {
                // Sets keepPlaying to false inorder to exit the loop and end the program
                keepPlaying = false;
                System.out.println("Thanks for playing! Goodbye!");
            }
            // Checks if the user entered anything other than "y" (yes)
            else if (!response.equals("y")) {
                // Handles invalid input by exiting the loop and ending the program
                System.out.println("Invalid input. Exiting the game.");
                keepPlaying = false;
            } else {
                // Clears the console if the user wants to play again
                clearConsole();
            }
        }

        // Closes the Scanner object to release resources
        scanner.close();
    }

    // This method clears the console screen to provide a clean display for the next game
    private static void clearConsole() {
        System.out.print("\033[H\033[2J"); // ANSI escape codes to clear the screen
        System.out.flush(); // Ensures the output is immediately displayed
    }
}
