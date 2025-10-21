package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Represents a transaction indicating that the card is still frozen.
 */
public final class StillFrozen implements Transaction {
    private final String description;
    private final int timestamp;
    private final String transactionType;

    public StillFrozen(final int timestamp) {
        this.description = "The card is frozen";
        this.timestamp = timestamp;
        this.transactionType = "stillfrozen";
    }

    public String getDescription() {
        return description;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    @Override
    public ObjectNode toObjectNode() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode transactionNode = mapper.createObjectNode();
        transactionNode.put("timestamp", this.getTimestamp());
        transactionNode.put("description", this.getDescription());
        return transactionNode;
    }
}
