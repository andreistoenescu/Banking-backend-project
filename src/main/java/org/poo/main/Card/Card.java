package org.poo.main.Card;

import org.poo.main.Account.Account;

/**
 * Represents a generic card with basic functionality.
 * Defines methods for card operations and information retrieval.
 */
public interface Card {

    /**
     * Performs the use operation for the card.
     * Implementation may vary based on the specific card type.
     */
    void use();

    /**
     * Retrieves the account associated with this card.
     *
     * @return The account linked to the card
     */
    Account getAccountofCard();

    /**
     * Retrieves the current status of the card.
     *
     * @return The card's current status (e.g., "active", "blocked")
     */
    String getStatus();

    /**
     * Retrieves the unique card number.
     *
     * @return The card's unique identification number
     */
    String getCardNumber();

    /**
     * Retrieves the type of the card.
     *
     * @return The card type (e.g., "classic", "credit")
     */
    String getType();

    /**
     * Sets the status of the card.
     *
     * @param status The new status to be set for the card
     */
    void setStatus(String status);
}
