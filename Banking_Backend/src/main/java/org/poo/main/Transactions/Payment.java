package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;

/**
 * Represents a payment transaction using a card.
 */
@Getter
public final class Payment implements Transaction {
    private final String description;
    private final int timestamp;
    private final double amount;
    private final String commerciant;
    private final String transactionType;
    private final String currency;

    public Payment(final int timestamp, final double amount, final String commericant,
                   final String transactionType, final String currency) {
        this.description = "Card payment";
        this.timestamp = timestamp;
        this.amount = amount;
        this.commerciant = commericant;
        this.transactionType = transactionType;
        this.currency = currency;
    }

    @Override
    public ObjectNode toObjectNode() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode transactionNode = mapper.createObjectNode();
        transactionNode.put("timestamp", this.getTimestamp());
        transactionNode.put("description", this.getDescription());
        transactionNode.put("amount", this.getAmount());
        transactionNode.put("commerciant", this.getCommerciant());
        return transactionNode;
    }
}
