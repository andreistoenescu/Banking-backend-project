package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;

/**
 * Represents a transaction that fails due to insufficient funds.
 */
@Getter
public final class NoFunds implements Transaction {
    private final int timestamp;
    private final String description;
    private final String transactionType = "nofunds";

    public NoFunds(final int timestamp) {
        this.timestamp = timestamp;
        this.description = "Insufficient funds";
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
