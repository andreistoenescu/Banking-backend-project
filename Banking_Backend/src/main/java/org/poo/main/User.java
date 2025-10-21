package org.poo.main;

import org.poo.main.Account.Account;
import org.poo.main.Account.AccountFactory;
import org.poo.main.Card.Card;
import org.poo.main.Card.CardFactory;
import org.poo.main.Transactions.Transaction;
import org.poo.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Represents a user with accounts, aliases, and transactions.
 */
public final class  User {
    private String name;
    private String surname;
    private String email;
    private LinkedHashMap<String, Account> accounts;
    private HashMap<String, Account> aliases;
    private ArrayList<Transaction> transactions;

    /**
     * Constructor for User.
     *
     * @param name the user's first name.
     * @param surname the user's surname.
     * @param email the user's email address.
     */
    public User(final String name, final String surname, final String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        accounts = new LinkedHashMap<>();
        aliases = new HashMap<>();
        transactions = new ArrayList<>();
    }

    /**
     * Adds an account for the user.
     *
     * @param type the type of the account.
     * @param interestRate the interest rate of the account.
     * @param currency the currency of the account.
     * @param transaction the initial transaction for the account.
     */
    public void addAccount(final String type, final double interestRate, final String currency, final Transaction transaction) {
        final Account account = AccountFactory.createAccount(type, interestRate, currency);
        accounts.put(account.getIBAN(), account);
        accounts.get(account.getIBAN()).addTransaction(transaction);
    }

    /**
     * Adds an alias for an account.
     *
     * @param alias the alias for the account.
     * @param account the account to associate with the alias.
     */
    public void addAlias(final String alias, final Account account) {
        aliases.put(alias, account);
    }

    /**
     * Gets an account by its alias.
     *
     * @param alias the alias of the account.
     * @return the account associated with the alias.
     */
    public Account getAccountAlias(final String alias) {
        return aliases.get(alias);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    /**
     * Adds a card to an account.
     *
     * @param type the type of the card.
     * @param iban the IBAN of the associated account.
     */
    public void addCard(final String type, final String iban) {
        final String cardNumber = Utils.generateCardNumber();

        final Account account = getAccounts().get(iban);
        if (account == null) {
            throw new IllegalArgumentException("Account with IBAN " + iban + " does not exist.");
        }
        final Card card = CardFactory.createCard(type, account, cardNumber);

        account.getCards().add(card);
    }

    /**
     * Adds a transaction to the user's transaction list.
     *
     * @param transaction the transaction to add.
     */
    public void addTransaction(final Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Gets the list of transactions for the user.
     *
     * @return the list of transactions.
     */
    public ArrayList<Transaction> getTransaction() {
        return transactions;
    }
}
