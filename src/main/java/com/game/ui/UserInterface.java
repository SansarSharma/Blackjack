package com.game.ui;

/**
 * UserInterface.java
 * Sets the basic functions needed and will be overridden
 */

import com.game.core.BlackjackGame;

public interface UserInterface {
    /**
     * Method - Setter
     * This method sets the Blackjack game instance that this UserInterface will be associated with.
     * Parameter: `game` is of type BlackjackGame.
     * No return type
     */
    public void setGame(BlackjackGame game);

    /**
     * Method - Display
     * This method displays the current state of the game, showing the cards held by both the House and the Player (You).
     * No parameters
     * No return type
     */
    public void display();

    /**
     * Method - User Prompt
     * This method prompts the user to decide if they want to draw another card (hit).
     * The method should return `true` if the user wants another card, otherwise `false`.
     * No parameters
     * Return type: Boolean
     */
    public boolean hitMe();

    /**
     * Method - Game Conclusion
     * This method displays the final state of the game, including the cards held by both the House and Player,
     * their respective scores, and the winner of the game.
     * No parameters
     * No return type
     */
    public void gameOver();
}
