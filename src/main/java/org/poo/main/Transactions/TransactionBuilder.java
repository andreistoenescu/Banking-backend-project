package org.poo.main.Transactions;

import java.util.List;

/**
 * Builder class for constructing various types of transactions.
 */
public final class TransactionBuilder {
    private String type;
    private String description;
    private String senderIban;
    private String receiverIban;
    private double amount;
    private String transferType;
    private int timestamp;
    private String commerciant;
    private String cardNumber;
    private String cardHolder;
    private String accountIban;
    private String currency;
    private List<String> accountsInvolved;
    private String errorAccount;

    /**
     * Sets the account causing the error in the transaction.
     *
     * @param errorAccount the account causing the error.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setErrorAccount(final String errorAccount) {
        this.errorAccount = errorAccount;
        return this;
    }

    /**
     * Sets the type of transaction.
     *
     * @param type the transaction type.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setType(final String type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param description the transaction description.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the sender's IBAN.
     *
     * @param senderIban the sender's IBAN.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setSenderIban(final String senderIban) {
        this.senderIban = senderIban;
        return this;
    }

    /**
     * Sets the receiver's IBAN.
     *
     * @param receiverIban the receiver's IBAN.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setReceiverIban(final String receiverIban) {
        this.receiverIban = receiverIban;
        return this;
    }

    /**
     * Sets the transaction amount.
     *
     * @param amount the transaction amount.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setAmount(final double amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Sets the type of transfer.
     *
     * @param transferType the type of transfer.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setTransferType(final String transferType) {
        this.transferType = transferType;
        return this;
    }

    /**
     * Sets the transaction timestamp.
     *
     * @param timestamp the transaction timestamp.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Sets the commerciant information for the transaction.
     *
     * @param commerciant the commerciant details.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setCommerciant(final String commerciant) {
        this.commerciant = commerciant;
        return this;
    }

    /**
     * Sets the card number for the transaction.
     *
     * @param cardNumber the card number.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    /**
     * Sets the cardholder's name for the transaction.
     *
     * @param cardHolder the cardholder's name.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setCardHolder(final String cardHolder) {
        this.cardHolder = cardHolder;
        return this;
    }

    /**
     * Sets the account IBAN associated with the transaction.
     *
     * @param accountIban the account IBAN.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setAccountIban(final String accountIban) {
        this.accountIban = accountIban;
        return this;
    }

    /**
     * Sets the currency for the transaction.
     *
     * @param currency the currency code.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setCurrency(final String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Sets the list of accounts involved in the transaction.
     *
     * @param accountsInvolved the list of involved accounts.
     * @return the updated TransactionBuilder.
     */
    public TransactionBuilder setAccountsInvolved(final List<String> accountsInvolved) {
        this.accountsInvolved = accountsInvolved;
        return this;
    }

    /**
     * Builds and returns the appropriate transaction object based on the type.
     *
     * @return the constructed Transaction object.
     * @throws IllegalArgumentException if the transaction type is unknown.
     */
    public Transaction build() {
        switch (type.toLowerCase()) {
            case "payment":
                return new Payment(timestamp, amount, commerciant, type, currency);
            case "transfer":
                return new Transfer(
                        timestamp, description, senderIban, receiverIban, amount,
                        transferType, type, currency
                );
            case "accountadded":
                return new AcountAdded(timestamp, type);
            case "cardadded":
                return new CardAdded(timestamp, cardNumber, cardHolder, accountIban);
            case "nofunds":
                return new NoFunds(timestamp);
            case "deletecard":
                return new DeleteCard(timestamp, cardNumber, cardHolder, accountIban);
            case "stillfrozen":
                return new StillFrozen(timestamp);
            case "gettingfrozen":
                return new GettingFrozen(timestamp);
            case "split":
                return new SplitPaymentTransaction(amount, timestamp, currency, accountsInvolved);
            case "splitnot":
                return new SplitNotEnoughTransaction(amount, timestamp, currency, accountsInvolved, errorAccount);
            case "nodeleteaccount":
                return new CantDeleteAccountTransaction(timestamp);
            case "changeinterest":
                return new ChangeInteresRate(timestamp, amount);
            default:
                throw new IllegalArgumentException("Unknown transaction type: " + type);
        }
    }
}
