package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Represents a transfer transaction.
 */
public final class Transfer implements Transaction {
    private final String description;
    private final String senderIban;
    private final String receiverIban;
    private final double amount;
    private final String transferType;
    private final int timestamp;
    private final String transactionType;
    private final String currency;

    /**
     * Constructor for Transfer.
     *
     * @param timestamp the transaction timestamp.
     * @param description the transaction description.
     * @param senderIban the sender's IBAN.
     * @param receiverIban the receiver's IBAN.
     * @param amount the transaction amount.
     * @param transferType the type of transfer.
     * @param transactionType the transaction type.
     * @param currency the transaction currency.
     */
    public Transfer(final int timestamp, final String description, final String senderIban,
                    final String receiverIban, final double amount, final String transferType,
                    final String transactionType, final String currency) {
        this.timestamp = timestamp;
        this.description = description;
        this.senderIban = senderIban;
        this.receiverIban = receiverIban;
        this.amount = amount;
        this.transferType = transferType;
        this.transactionType = transactionType;
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public String getSenderIban() {
        return senderIban;
    }

    public String getReceiverIban() {
        return receiverIban;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransferType() {
        return transferType;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public ObjectNode toObjectNode() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode transactionNode = mapper.createObjectNode();
        transactionNode.put("timestamp", this.getTimestamp());
        transactionNode.put("description", this.getDescription());
        transactionNode.put("senderIBAN", this.getSenderIban());
        transactionNode.put("receiverIBAN", this.getReceiverIban());
        transactionNode.put("amount", this.getAmount() + " " + this.getCurrency());
        transactionNode.put("transferType", this.getTransferType());

        return transactionNode;
    }
}
