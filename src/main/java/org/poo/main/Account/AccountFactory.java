package org.poo.main.Account;

/**
 * Factory class for creating different types of bank accounts.
 */
public final class AccountFactory {
    /**
     * Private constructor to prevent instantiation.
     */
    private AccountFactory() {
        // Prevent instantiation
    }

    /**
     * Create an account based on type, interest rate, and currency.
     *
     * @param type account type
     * @param interestRate interest rate
     * @param currency account currency
     * @return created account
     * @throws IllegalArgumentException if account type is unsupported
     */
    public static Account createAccount(final String type,
                                        final double interestRate,
                                        final String currency) {
        if ("savings".equalsIgnoreCase(type)) {
            return new SavingsAccount(interestRate, currency);
        } else if ("classic".equalsIgnoreCase(type)) {
            return new ClassicAccount(currency);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
