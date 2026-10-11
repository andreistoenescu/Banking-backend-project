package org.poo.main.Card;

import org.poo.main.Account.Account;

/**
 * Factory class for creating different types of cards.
 */
public final class CardFactory {
    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private CardFactory() {
        // Prevent instantiation
    }

    /**
     * Creates a card based on the specified type.
     *
     * @param type The type of card to create
     * @param account The account associated with the card
     * @param cardNumber The card number
     * @return A new Card instance
     * @throws IllegalArgumentException if an unsupported card type is provided
     */
    public static Card createCard(final String type,
                                  final Account account, final String cardNumber) {
        if ("classic".equals(type)) {
            return new ClassicCard(account, cardNumber);
        }

        if ("onetime".equals(type)) {
            return new OneTimeUseCard(account, cardNumber);
        }

        throw new IllegalArgumentException("Unsupported type: " + type);
    }
}
