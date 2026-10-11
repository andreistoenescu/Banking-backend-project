package org.poo.main.Account;

import org.poo.main.Card.Card;
import org.poo.main.Transactions.Transaction;
import org.poo.utils.Utils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Represents a Savings Account with specific properties and behaviors.
 * This class implements the Account interface and provides methods
 * for managing a savings account.
 */
public final class SavingsAccount implements Account {
    private String iban;
    private double balance;
    private double interestRate;
    private String currency;
    private String type;
    private double minimumBalance;
    private List<Card> cards;
    private LinkedHashMap<String, Account> aliases;
    private List<Transaction> transactions;
    private List<Transaction> spendingTransactions;

    /**
     * Constructs a new SavingsAccount with specified interest rate and currency.
     *
     * @param interestRate the interest rate for the savings account
     * @param currency the currency of the account
     */
    public SavingsAccount(final double interestRate, final String currency) {
        this.iban = Utils.generateIBAN();
        this.balance = 0;
        this.interestRate = interestRate;
        this.currency = currency;
        this.type = "savings";
        this.cards = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.spendingTransactions = new ArrayList<>();
        this.aliases = new LinkedHashMap<>();
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
     * Sets the currency of the account.
     *
     * @param newCurrency the new currency to set
     */
    public void setCurrency(final String newCurrency) {
        this.currency = newCurrency;
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
     * Sets the interest rate for the account.
     *
     * @param newInterestRate the new interest rate to set
     */
    @Override
    public void setInterestRate(final double newInterestRate) {
        this.interestRate = newInterestRate;
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
     * Gets the interest rate of the account.
     *
     * @return the interest rate
     */
    @Override
    public double getInterestRate() {
        return interestRate;
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
}
