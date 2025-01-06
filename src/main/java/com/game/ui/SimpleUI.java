package com.game.ui;

/**
 * SimpleUI.java
 * Overrides the functions from UserInterface.java
 */


import com.game.core.BlackjackGame;
import java.util.Scanner;

public class SimpleUI implements UserInterface {

    // Declaring Instance Variables

    // Instance Variable of type BlackjackGame, which represents the game logic linked to this UI
    private BlackjackGame game;

    // Instance Variable of type Scanner, used to read user input from the console
    private final Scanner user = new Scanner(System.in);

    /**
     * Method - Setter
     * This method links the SimpleUI instance to a specific BlackjackGame instance.
     * Parameter: `game` is of type BlackjackGame.
     * Void return type
     */
    @Override
    public void setGame(BlackjackGame game) {
        this.game = game; // Assigns the provided BlackjackGame instance to this UI
    }

    /**
     * Method - Display
     * This method displays the current state of the game, including the cards held by the house and the player.
     * No parameters
     * Void return type
     */
    @Override
    public void display() {
        // Display the house's cards
        System.out.println("######################################\n\t         House Holds: \n######################################\n"
                + this.game.getHouseCards().toString()); // Retrieves the house's cards and displays them

        // Display the player's cards
        System.out.println("--------------------------------------\n\t          You Hold: \n--------------------------------------\n"
                + this.game.getYourCards().toString()); // Retrieves the player's cards and displays them
    }

    /**
     * Method - Player Action
     * This method prompts the player to decide whether they want to draw another card or not.
     * No parameters
     * Boolean return type - Returns true if the player chooses to draw another card, otherwise false.
     */
    @Override
    public boolean hitMe() {
        // Prompt the user for input
        System.out.println("|---------------------------------------------------|\n| Would you like to draw another card? Type Y or N  |\n|---------------------------------------------------|");

        // Loop until a valid input is provided
        while (true) {
            String choice = user.nextLine().trim().toLowerCase(); // Read user input and normalize it to lowercase
            // If the user types "y", they want another card
            if (choice.equals("y")) {
                return true;
            }
            // If the user types "n", they don't want another card
            else if (choice.equals("n")) {
                return false;
            }
            // If the input is invalid, prompt the user again
            else {
                System.out.println("Invalid input. Please type 'Y' or 'N'.");
            }
        }
    }

    /**
     * Method - Game Over Handler
     * This method is called when the game ends. It displays the final scores and announces the winner.
     * No parameters
     * Void return type
     */
    @Override
    public void gameOver() {
        this.display(); // Display the final state of the game (both house and player cards)

        // Calculate the player's and house's final scores
        int yourScore = game.calculateScore(game.getYourCards()); // Get the player's score
        int houseScore = game.calculateScore(game.getHouseCards()); // Get the house's score

        // Display the final scores
        System.out.println("House Score: " + houseScore + ", Your Score: " + yourScore);

        // Determine and announce the winner
        // Player's score exceeds 21 (bust)
        if (yourScore > 21) {
            System.out.println("|-------------|\n| House Wins! |\n|-------------| \n(You Busted)");
        }
        // House's score exceeds 21 (bust)
        else if (houseScore > 21) {
            System.out.println("|----------|\n| You Win! |\n|----------| \n(House Busted)");
        }
        // Player's score is higher than the house's
        else if (yourScore > houseScore) {
            System.out.println("|----------|\n| You Win! |\n|----------| \n(Your Score is Higher)");
        }
        // House's score is higher than the player's
        else if (yourScore < houseScore) {
            System.out.println("|-------------|\n| House Wins! |\n|-------------| \n(House Score is Higher)");
        }
        // Scores are equal (tie)
        else {
            System.out.println("|------------|\n| It's a Tie |\n|------------| \n(Both Scores are Equal)");
        }

        // Thank the player for playing
        System.out.println("\nThanks for playing BlackJack!\n");
    }
}
