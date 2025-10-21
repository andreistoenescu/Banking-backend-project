package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a transaction where a new card is added.
 */
@Getter
@Setter
public final class CardAdded implements Transaction {
    private final int timestamp;
    private final String transactionType;
    private final String cardNumber;
    private final String cardHolder;
    private final String accountIban;
    private final String description;

    public CardAdded(final int timestamp, final String cardNumber,
                     final String cardHolder, final String accountIban) {
        this.timestamp = timestamp;
        this.transactionType = "cardadded";
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.accountIban = accountIban;
        this.description = "New card created";
    }

    @Override
    public ObjectNode toObjectNode() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode transactionNode = mapper.createObjectNode();
        transactionNode.put("timestamp", this.getTimestamp());
        transactionNode.put("description", this.getDescription());
        transactionNode.put("card", this.getCardNumber());
        transactionNode.put("cardHolder", this.getCardHolder());
        transactionNode.put("account", this.getAccountIban());
        return transactionNode;
    }
}
