package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;

/**
 * Represents a transaction where the account gets frozen due to insufficient funds.
 */
@Getter
public final class GettingFrozen implements Transaction {
    private final String description;
    private final int timestamp;
    private final String transactionType;

    public GettingFrozen(final int timestamp) {
        this.timestamp = timestamp;
        this.transactionType = "gettingfrozen";
        this.description = "You have reached the minimum amount of funds, the card will be frozen";
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
