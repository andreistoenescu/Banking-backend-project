package org.poo.main.Account;

import org.poo.main.Card.Card;
import org.poo.main.Transactions.Transaction;

import java.util.List;

/**
 * Interface representing a bank account with various methods.
 */
public interface Account {
    /**
     * Get the type of account.
     * @return account type as a string
     */
    String getType();

    /**
     * Get the IBAN of the account.
     * @return IBAN as a string
     */
    String getIBAN();

    /**
     * Get the current balance of the account.
     * @return balance as a double
     */
    double getBalance();

    /**
     * Add funds to the account.
     * @param balance amount to add
     */
    void addFunds(double balance);

    /**
     * Get the currency of the account.
     * @return currency as a string
     */
    String getCurrency();

    /**
     * Get the minimum balance for the account.
     * @return minimum balance as a double
     */
    double getMinimumBalance();

    /**
     * Get the list of cards associated with the account.
     * @return list of cards
     */
    List<Card> getCards();

    /**
     * Add a card to the account.
     * @param card card to add
     */
    void addCard(Card card);

    /**
     * Set the balance of the account.
     * @param balance new balance
     */
    void setBalance(double balance);

    /**
     * Set an alias for the account.
     * @param alias alias name
     * @param account account to alias
     */
    void setAlias(String alias, Account account);

    /**
     * Set the minimum balance for the account.
     * @param minimumBalance new minimum balance
     */
    void setMinimumBalance(double minimumBalance);

    /**
     * Add a transaction to the account.
     * @param transaction transaction to add
     */
    void addTransaction(Transaction transaction);

    /**
     * Get all transactions for the account.
     * @return list of transactions
     */
    List<Transaction> getTransactions();

    /**
     * Add a spending transaction to the account.
     * @param transaction spending transaction to add
     */
    void addSpendingTransaction(Transaction transaction);

    /**
     * Get all spending transactions.
     * @return list of spending transactions
     */
    List<Transaction> getSpendingTransactions();

    /**
     * Get the interest rate for the account.
     * @return interest rate as a double
     */
    double getInterestRate();

    /**
     * Set the interest rate for the account.
     * @param interestRate new interest rate
     */
    void setInterestRate(double interestRate);
}
