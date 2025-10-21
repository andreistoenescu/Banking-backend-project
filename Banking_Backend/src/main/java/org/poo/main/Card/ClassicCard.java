package org.poo.main.Card;

import org.poo.main.Account.Account;

/**
 * Represents a classic card with standard card functionality.
 */
public class ClassicCard implements Card {
    private final Account account;
    private final String cardNumber;
    private String status;
    private final String type;

    /**
     * Constructs a new ClassicCard.
     *
     * @param account    The account associated with the card
     * @param cardNumber The card's unique number
     */
    public ClassicCard(final Account account, final String cardNumber) {
        this.account = account;
        this.cardNumber = cardNumber;
        this.status = "active";
        this.type = "classic";
    }

    /**
     * Retrieves the card number.
     *
     * @return The card number
     */
    @Override
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Retrieves the card type.
     *
     * @return The card type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Retrieves the account associated with the card.
     *
     * @return The account of the card
     */
    @Override
    public Account getAccountofCard() {
        return account;
    }

    /**
     * Performs the use operation for the card.
     * Currently, this method is a no-op implementation.
     */
    @Override
    public void use() {
        // No-op implementation
    }

    /**
     * Retrieves the current status of the card.
     *
     * @return The status of the card
     */
    @Override
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the card.
     *
     * @param status The new status to be set
     */
    @Override
    public void setStatus(final String status) {
        this.status = status;
    }
}
