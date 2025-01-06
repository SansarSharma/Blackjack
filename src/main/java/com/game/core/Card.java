package com.game.core;

/**
 * Card.java
 * This class defines a playing card with attributes such as rank, suit, and visibility (face-up or face-down).
 * It provides functionality to represent the card as a string, compare cards, and handle game-specific logic.
 */

public class Card implements Comparable<Card> {

    // Declaring symbolic constants for the suits
    public static final int CLUB = 0;
    public static final int DIAMOND = 1;
    public static final int HEART = 2;
    public static final int SPADE = 3;

    // Declaring symbolic constants for the face card ranks
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;

    // Declaring Instance Variables

    // Instance Variable of type int which represents the rank of the card (2 to 14 for Ace)
    private int rank = 0;

    // Instance Variable of type int which represents the suit of the card (0 to 3 for Clubs, Diamonds, Hearts, Spades)
    private int suit = 0;

    // Instance Variable of type boolean which represents whether the card is face-up or face-down
    private boolean faceUp;

    /**
     * Constructor
     * This is used to create a Card object with the specified rank, suit, and visibility.
     * Parameter: `rank` is an integer from 2 to 14 representing the rank of the card.
     * Parameter: `suit` is an integer from 0 to 3 representing the suit of the card.
     * Parameter: `faceUp` is a boolean that determines whether the card is face-up (true) or face-down (false).
     */
    public Card(int rank, int suit, boolean faceUp) {
        this.rank = rank; // Assigning the rank of the card
        this.suit = suit; // Assigning the suit of the card
        this.faceUp = faceUp; // Setting whether the card is face-up or face-down
    }

    /**
     * Method - Getter
     * This method retrieves the visibility of the card (face-up or face-down).
     * No parameters
     * Return type: boolean
     */
    public boolean isFaceUp() {
        return this.faceUp; // Return whether the card is face-up
    }

    /**
     * Method - Setter
     * This method sets the visibility of the card (face-up or face-down).
     * Parameter: `faceUp` is a boolean that determines whether the card is face-up or face-down.
     * No return type
     */
    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp; // Set the face-up state of the card
    }

    /**
     * Method - Getter
     * This method retrieves the rank of the card.
     * No parameters
     * Return type: int
     */
    public int getRank() {
        return this.rank; // Return the rank of the card
    }

    /**
     * Method - Getter
     * This method retrieves the suit of the card.
     * No parameters
     * Return type: int
     */
    public int getSuit() {
        return this.suit; // Return the suit of the card
    }

    /**
     * Method - Comparator
     * This method compares two cards based on their rank.
     * Parameter: `c` is of type Card and represents the card to compare with.
     * Return type: int - Returns a value indicating whether this card's rank is less than, equal to, or greater than the other card's rank.
     */
    @Override
    public int compareTo(Card c) {
        return Integer.compare(this.rank, c.rank); // Compare the rank of this card with the rank of another card
    }

    /**
     * Method - Equality Checker
     * This method checks whether two cards are equal based on their rank and suit.
     * Parameter: `ob` is an Object that represents the card to compare with.
     * Return type: boolean - Returns true if the cards have the same rank and suit, otherwise false.
     */
    @Override
    public boolean equals(Object ob) {
        // Check if the object is not an instance of Card then return false
        if (!(ob instanceof Card)) {
            return false;
        }
        Card c = (Card) ob; // Cast the object to a Card
        return c.rank == this.rank && c.suit == this.suit; // Check if the rank and suit are the same
    }

    /**
     * Method - Hash Generator
     * This method generates a unique hash code for the card based on its rank and suit.
     * No parameters
     * Return type: int
     */
    @Override
    public int hashCode() {
        int hash = 7; // Initialize the hash with a prime number
        hash = 31 * hash + this.getRank(); // Incorporate the rank into the hash
        hash = 31 * hash + this.getSuit(); // Incorporate the suit into the hash
        return hash; // Return the generated hash
    }

    /**
     * Method - String Converter
     * This method converts the card to a string representation based on its visibility.
     * No parameters
     * Return type: String
     */
    @Override
    public String toString() {
        // If the card is face-up then return the rank and suit as a string
        if (this.faceUp) {
            return this.getRankString() + " of " + this.getSuitString();
        }
        // If the card is face-down then return a placeholder for the face down cards
        else {
            return "?";
        }
    }

    /**
     * Method - Rank String Converter
     * This method converts the rank of the card to its string representation.
     * No parameters
     * Return type: String
     */
    public String getRankString() {
        // Check the rank of the card
        switch (this.rank) {
            case JACK:
                return "Jack";
            case QUEEN:
                return "Queen";
            case KING:
                return "King";
            case ACE:
                return "Ace";
            default:
                return Integer.toString(this.rank); // Return the rank as a string for numeric cards
        }
    }

    /**
     * Method - Suit String Converter
     * This method converts the suit of the card to its string representation.
     * No parameters
     * Return type: String
     */
    public String getSuitString() {
        // Check the suit of the card
        switch (this.suit) {
            case CLUB:
                return "Clubs";
            case DIAMOND:
                return "Diamonds";
            case HEART:
                return "Hearts";
            case SPADE:
                return "Spades";
            default:
                return "Unknown"; // Return "Unknown" for invalid suits
        }
    }
}
