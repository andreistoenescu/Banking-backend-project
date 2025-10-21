package org.poo.main.Card;

import org.poo.main.Account.Account;
import org.poo.utils.Utils;

/**
 * Represents a one-time use card with special usage restrictions.
 */
public final class OneTimeUseCard implements Card {
    private boolean isUsed;
    private final Account account;
    private final String cardNumber;
    private String status;
    private final String type;

    /**
     * Constructs a new OneTimeUseCard.
     *
     * @param account The account associated with the card
     * @param cardNumber The card's unique number
     */
    public OneTimeUseCard(final Account account, final String cardNumber) {
        isUsed = false;
        this.account = account;
        this.cardNumber = cardNumber;
        status = "active";
        this.type = "onetime";
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
     * Retrieves the account associated with the card.
     *
     * @return The account of the card
     */
    @Override
    public Account getAccountofCard() {
        return account;
    }

    /**
     * Uses the card, generating a new card if not already used.
     */
    @Override
    public void use() {
        if (!isUsed) {
            isUsed = true;
            status = "used";

            String newCardNumber = Utils.generateCardNumber();
            Card newCard = new OneTimeUseCard(account, newCardNumber);
            account.getCards().add(newCard);
        }
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
     * Sets the status of the card.
     *
     * @param status The new status to be set
     */
    @Override
    public void setStatus(final String status) {
        this.status = status;
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
}
