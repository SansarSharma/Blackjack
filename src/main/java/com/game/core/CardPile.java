package com.game.core;

import java.util.ArrayList;

/**
 * CardPile.java
 * This class represents a collection (pile) of cards.
 * It provides functionality to add, remove, and display cards in the pile.
 */
public class CardPile {

    // Instance Variable

    // Instance variable of type ArrayList<Card>, which holds the cards in the pile
    private final ArrayList<Card> cards = new ArrayList<>();

    /**
     * Constructor
     * This is used to create a CardPile object and initializes the instance variable `cards`.
     * No parameters
     * No return type, as constructors do not have a return type, even void.
     */
    public CardPile() {
        // No additional initialization needed as the cards ArrayList is already initialized
    }

    /**
     * Method - Adder
     * This method is used to add a card to the pile.
     * Parameter: `card` is of type Card, representing the card to be added.
     * No return type (void).
     */
    public void add(Card card) {
        this.cards.add(card); // Add the specified card to the cards list
    }

    /**
     * Method - Remover
     * This method removes a card chosen at random from the pile.
     * It ensures that the pile is not empty before attempting to remove a card.
     * No parameters
     * Return type: Card - The card that is removed from the pile.
     */
    public Card removeRandom() {
        // Check if the pile is empty. If it is, throw an exception to indicate no cards are left to remove
        if (this.cards.isEmpty()) {
            throw new IllegalStateException("No cards left to remove.");
        }

        // Generate a random index to select a card from the pile
        int number = (int) (Math.random() * this.cards.size());

        // Remove and return the card at the randomly chosen index
        return this.cards.remove(number);
    }

    /**
     * Method - String Representation
     * This method returns a string representation of the pile.
     * The string lists each card in the pile, separated by new lines.
     * No parameters
     * Return type: String - The string representation of the pile.
     */
    @Override
    public String toString() {
        // Initialize a StringBuilder to build the string representation
        StringBuilder cardList = new StringBuilder("\n");

        // Iterate over each card in the pile and append its string representation
        for (Card card : this.cards) {
            cardList.append(card.toString()).append("\n"); // Add the card's string representation followed by a new line
        }

        // Return the built string
        return cardList.toString();
    }

    /**
     * Method - Getter
     * This method returns the collection of cards in the pile.
     * No parameters
     * Return type: ArrayList<Card> - The list of cards in the pile.
     */
    public ArrayList<Card> getCards() {
        return cards; // Return the list of cards
    }
}
