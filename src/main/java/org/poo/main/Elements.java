package org.poo.main;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.ExchangeInput;
import org.poo.fileio.ObjectInput;
import org.poo.fileio.UserInput;
import org.poo.main.Account.Account;
import org.poo.main.OutputJson.PrintUsersOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Class responsible for managing users and exchange rates in the system.
 * This class is not designed for extension.
 */
public final class Elements {
    private final HashMap<String, User> users;
    private final ExchangeRate exchanges;

    /**
     * Constructs a new Elements instance with empty users map and exchange rates.
     */
    public Elements() {
        users = new LinkedHashMap<>();
        exchanges = new ExchangeRate();
    }

    /**
     * Returns the exchange rate manager instance.
     *
     * @return the ExchangeRate instance
     */
    public ExchangeRate getExchanges() {
        return exchanges;
    }

    /**
     * Adds users from input data to the system.
     *
     * @param inputData the input data containing user information
     */
    public void addUser(final ObjectInput inputData) {
        for (UserInput input : inputData.getUsers()) {
            User user = new User(input.getFirstName(), input.getLastName(), input.getEmail());
            users.put(input.getEmail(), user);
        }
    }

    /**
     * Finds an account by its IBAN.
     *
     * @param iban the IBAN to search for
     * @return the Account if found, null otherwise
     */
    public Account findAccount(final String iban) {
        Account account = null;
        for (User user : users.values()) {
            if (user.getAccounts().get(iban) != null) {
                account = user.getAccounts().get(iban);
            }
        }
        return account;
    }

    /**
     * Finds a user by their account IBAN.
     *
     * @param iban the IBAN to search for
     * @return the User if found, null otherwise
     */
    public User findUserbyAccount(final String iban) {
        for (User user : users.values()) {
            for (Account account : user.getAccounts().values()) {
                if (account.getIBAN().equals(iban)) {
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * Adds exchange rates from input data to the system.
     *
     * @param inputData the input data containing exchange rate information
     */
    public void addExchangeRate(final ObjectInput inputData) {
        for (ExchangeInput input : inputData.getExchangeRates()) {
            exchanges.addExchangeRate(input.getFrom(), input.getTo(), input.getRate());
        }
    }

    /**
     * Gets the exchange rate between two currencies.
     *
     * @param from source currency code
     * @param to target currency code
     * @return the exchange rate
     */
    public double getExchangeRate(final String from, final String to) {
        return exchanges.getExchangeRate(from, to);
    }

    /**
     * Returns the map of users in the system.
     *
     * @return HashMap containing users with their email as key
     */
    public HashMap<String, User> getUsers() {
        return users;
    }

    /**
     * Prints users information to the output list.
     *
     * @param outputList the list to add output to
     * @param timestamp the timestamp for the output
     */
    public void printUsers(final ArrayList<ObjectNode> outputList, final int timestamp) {
        PrintUsersOutput.generatePrintUsersOutput(outputList, users, timestamp);
    }
}
