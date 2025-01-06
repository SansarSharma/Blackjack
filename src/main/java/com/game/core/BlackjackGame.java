package com.game.core;

/**
 * BlackjackGame.java
 * This class defines the logic, rules, and state management for the game of Blackjack.
 * It is the core of the game's functionality.
 */

import com.game.ui.UserInterface;

public class BlackjackGame {

    // Declaring Instance Variables

    // Instance Variables of type CardPile which represent the card deck, the house's cards, and the player's cards
    private final CardPile deck;
    private final CardPile houseCards;
    private final CardPile yourCards;

    // Instance Variables of type boolean which is used to track whether the house or player has finished their turn
    private boolean houseDone;
    private boolean playerDone;

    // Instance Variables of type UserInterface and is used to interact with the game
    private final UserInterface ui;

    /**
     * Constructor
     * This is used to create the BlackjackGame object, and have all the necessary instance variables to have a value once this constructor is called
     * Parameter: `ui` is of type UserInterface.
     * There is no return type as constructors do not have a return type, even void
     */

    public BlackjackGame(UserInterface ui) {
        this.ui = ui; // Linking the provided user interface to this game instance
        ui.setGame(this); // Registering this game with the user interface

        // Initializing the deck with 52 cards (13 ranks * 4 suits)
        deck = new CardPile();
        for (int i = 2; i < 15; i++) { // Outer loop: iterates through all card ranks (2 to Ace)
            for (int j = 0; j < 4; j++) { // Inner loop: iterates through the four suits (Clubs, Diamonds, Hearts, Spades)
                deck.add(new Card(i, j, true)); // Adding a new card of the current rank and suit to the deck
            }
        }

        // Initializing card piles for the house and player
        houseCards = new CardPile();
        yourCards = new CardPile();

        // Setting initial flags for house and player turns to false
        houseDone = false;
        playerDone = false;
    }

    /**
     * Method - Game Starter
     * This method starts the game of Blackjack by initializing the game state.
     * No parameters
     * Void return type
     */
    public void start() {
        // Check if the deck is empty before starting. If its empty then exit the method
        if (deckIsEmpty()) {
            System.out.println("Deck is empty. Cannot start the game.");
            return;
        }
        dealInitialCards(); // If the deck is not empty then deal the initial cards to both the house and player
        ui.display(); // Then Display the current state of the game.
    }

    /**
     * Method - Gameplay Handler
     * This method handles the main gameplay loop, allowing the player and house to take turns.
     * No parameters
     * Void return type
     */
    public void play() {
        // Check if the deck is empty before starting. If its empty then exit the method
        if (deckIsEmpty()) {
            System.out.println("Deck is empty. Game cannot proceed.");
            return;
        }

        // Main gameplay loop. This continues until both house and player are done
        while (!houseDone || !playerDone) {
            // Check if the deck becomes empty mid-game then exit the loop.
            if (deckIsEmpty()) {
                System.out.println("Deck depleted. Ending the game.");
                break;
            }

            // Play only if the house is not yet done their turn
            if (!houseDone && housePlays()) {
                ui.display(); // Update the game display after house's move
            }

            // Play only is the player is not yet done their turn
            if (!playerDone && playerPlays()) {
                ui.display(); // Update the game display after player's move
            }
        }
    }

    /**
     * Method - Game Finisher
     * This method concludes the game by revealing all cards and announcing the winner.
     * No parameters
     * Void return type
     */
    public void end() {
        getHouseCards().getCards().get(0).setFaceUp(true); // Reveal the house's hidden card which is their first card
        ui.gameOver(); // Calls the user interface to display the game results
    }

    /**
     * Method - Gameplay Logic
     * This method handles the house's moves during its turn.
     * No parameters
     * Boolean return type - Returns true if a move is made, otherwise it's false.
     */
    private boolean housePlays() {
        // House draws cards if their score is below 17 and the deck is not empty
        if (!deckIsEmpty() && calculateScore(getHouseCards()) < 17) {
            getHouseCards().add(deck.removeRandom()); // Add a random card from the deck to the house's hand
            return true; // Indicate that a move was made
        }
        houseDone = true; // Mark the house as done with its turn
        return false;
    }

    /**
     * Method - Gameplay Logic
     * This method handles the player's moves during its turn.
     * No parameters
     * Boolean return type - Returns true if a move is made, otherwise it's false.
     */
    private boolean playerPlays() {
        // Check if the player wants to draw a card ("Hit") and if the deck is not empty
        if (ui.hitMe() && !deckIsEmpty()) {
            getYourCards().add(deck.removeRandom()); // Add a random card from the deck to the player's hand

            // Check if the player has busted (score > 21)
            if (calculateScore(getYourCards()) > 21) {
                playerDone = true; // End the player's turn
                houseDone = true; // End the house's turn as the game is over
            }
            return true; // Indicate that a move was made
        }
        playerDone = true; // Mark the player as done with their turn
        return false;
    }

    /**
     * Method - Initial Setup
     * This method deals the initial cards to both the House and the Player.
     * No parameters
     * Void return type
     */
    private void dealInitialCards() {
        // Check if there are enough cards to start the game. If its empty then exit the method
        if (deck.getCards().size() < 4) {
            System.out.println("Not enough cards to deal. Ending the game.");
            houseDone = true;
            playerDone = true;
            return;
        }

        // Deal the cards. The house gets two cards with one facing down, and the player gets two cards
        Card card = deck.removeRandom();
        card.setFaceUp(false); // The house's first card is dealt face down
        getHouseCards().add(card);
        getHouseCards().add(deck.removeRandom());
        getYourCards().add(deck.removeRandom());
        getYourCards().add(deck.removeRandom());
    }

    /**
     * Method - Checker
     * This method checks if the deck is empty.
     * No parameters
     * Boolean return type - Returns true if the deck is empty, otherwise it's false.
     */
    private boolean deckIsEmpty() {
        return deck.getCards().isEmpty(); // Returns true if the deck has no cards left
    }

    /**
     * Method - Score Calculator
     * This method calculates the score of a card pile while following the Blackjack rules.
     * Parameter: `p` is of type CardPile
     * Integer return type - Returns the calculated score as an integer.
     */
    public int calculateScore(CardPile p) {
        int score = 0; // Initialize the score to 0
        int aceCount = 0; // Track the number of Aces for flexible scoring

        // Iterate through each card in the pile and calculate the score
        for (Card card : p.getCards()) {
            // If the card is an Ace, initially count it as 11
            if (card.getRank() == Card.ACE) {
                score += 11;
                aceCount++;
            }
            // Face cards are worth 10 points
            else if (card.getRank() >= Card.JACK && card.getRank() <= Card.KING) {
                score += 10;
            }
            // Numeric cards are worth their rank value
            else {
                score += card.getRank();
            }
        }

        // Adjust the score if Aces need to be counted as 1 instead of 11
        while (score > 21 && aceCount > 0) {
            score -= 10; // Reduce the score by 10 for each Ace converted to 1
            aceCount--;
        }

        return score; // Return the final calculated score
    }

    /**
     * Method - Getter
     * This method gives the house's cards
     * No parameters
     * Return type: CardPile
     */
    public CardPile getHouseCards() {
        return houseCards; // Return the house's card pile
    }

    /**
     * Method - Getter
     * This method gives the player's cards
     * No parameters
     * Return type: CardPile
     */
    public CardPile getYourCards() {
        return yourCards; // Return the player's card pile
    }
}
