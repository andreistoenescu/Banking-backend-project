package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;

/**
 * Represents a transaction where a card is deleted.
 */
@Getter
public final class DeleteCard implements Transaction {
    private final int timestamp;
    private final String transactionType;
    private final String cardNumber;
    private final String cardHolder;
    private final String accountIban;
    private final String description;

    public DeleteCard(final int timestamp, final String cardNumber,
                      final String cardHolder, final String accountIban) {
        this.timestamp = timestamp;
        this.transactionType = "deletecard";
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.accountIban = accountIban;
        this.description = "The card has been destroyed";
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
