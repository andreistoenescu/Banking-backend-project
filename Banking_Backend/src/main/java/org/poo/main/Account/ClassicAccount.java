package org.poo.main.Account;

import org.poo.main.Card.Card;
import org.poo.main.Transactions.Transaction;
import org.poo.utils.Utils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Represents a Classic Bank Account with specific properties and behaviors.
 * This class implements the Account interface and provides methods
 * for managing a classic account.
 */
public final class ClassicAccount implements Account {
    private String iban;
    private double balance;
    private String currency;
    private String type;
    private double minimumBalance;
    private List<Card> cards;
    private LinkedHashMap<String, Account> aliases;
    private List<Transaction> transactions;
    private List<Transaction> spendingTransactions;
    private boolean isFrozen = false;

    /**
     * Constructs a new ClassicAccount with the specified currency.
     *
     * @param currency the currency of the account
     */
    public ClassicAccount(final String currency) {
        this.iban = Utils.generateIBAN();
        this.balance = 0;
        this.currency = currency;
        this.type = "classic";
        this.cards = new ArrayList<>();
        this.aliases = new LinkedHashMap<>();
        this.transactions = new ArrayList<>();
        this.spendingTransactions = new ArrayList<>();
    }

    /**
     * Gets the interest rate for the account.
     * Always returns -1 for a classic account.
     *
     * @return always -1
     */
    @Override
    public double getInterestRate() {
        return -1;
    }

    /**
     * Sets the interest rate.
     * No-op for classic account as it does not support interest rates.
     *
     * @param interestRate ignored for classic accounts
     */
    @Override
    public void setInterestRate(final double interestRate) {
        // No-op for classic account
    }

    /**
     * Adds a spending transaction to the account.
     *
     * @param transaction the transaction to add
     */
    @Override
    public void addSpendingTransaction(final Transaction transaction) {
        this.spendingTransactions.add(transaction);
    }

    /**
     * Retrieves the list of spending transactions.
     *
     * @return list of spending transactions
     */
    @Override
    public List<Transaction> getSpendingTransactions() {
        return spendingTransactions;
    }

    /**
     * Adds a transaction to the account.
     *
     * @param transaction the transaction to add
     */
    @Override
    public void addTransaction(final Transaction transaction) {
        this.transactions.add(transaction);
    }

    /**
     * Retrieves the list of all transactions.
     *
     * @return list of transactions
     */
    @Override
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    /**
     * Gets the type of the account.
     *
     * @return the account type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Gets the IBAN of the account.
     *
     * @return the account's IBAN
     */
    @Override
    public String getIBAN() {
        return iban;
    }

    /**
     * Gets the current account balance.
     *
     * @return the account balance
     */
    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the IBAN for the account.
     *
     * @param newIban the new IBAN to set
     */
    public void setIBAN(final String newIban) {
        this.iban = newIban;
    }

    /**
     * Adds funds to the account balance.
     *
     * @param fundsToAdd the amount of funds to add
     */
    @Override
    public void addFunds(final double fundsToAdd) {
        this.balance += fundsToAdd;
    }

    /**
     * Sets the account balance.
     *
     * @param newBalance the new balance to set
     */
    @Override
    public void setBalance(final double newBalance) {
        this.balance = newBalance;
    }

    /**
     * Retrieves the list of cards associated with the account.
     *
     * @return list of cards
     */
    @Override
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Adds a card to the account.
     *
     * @param card the card to add
     */
    @Override
    public void addCard(final Card card) {
        cards.add(card);
    }

    /**
     * Sets an alias for the account.
     *
     * @param alias the alias name
     * @param account the account to associate with the alias
     */
    @Override
    public void setAlias(final String alias, final Account account) {
        this.aliases.put(alias, account);
    }

    /**
     * Sets the minimum balance for the account.
     *
     * @param amount the minimum balance to set
     */
    @Override
    public void setMinimumBalance(final double amount) {
        this.minimumBalance = amount;
    }

    /**
     * Sets the currency of the account.
     *
     * @param newCurrency the new currency to set
     */
    public void setCurrency(final String newCurrency) {
        this.currency = newCurrency;
    }

    /**
     * Gets the currency of the account.
     *
     * @return the account's currency
     */
    @Override
    public String getCurrency() {
        return currency;
    }

    /**
     * Gets the minimum balance for the account.
     *
     * @return the minimum balance
     */
    @Override
    public double getMinimumBalance() {
        return minimumBalance;
    }
}
