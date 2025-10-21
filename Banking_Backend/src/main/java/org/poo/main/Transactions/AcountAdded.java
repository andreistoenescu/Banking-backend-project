package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a transaction where a new account is added.
 */
@Getter
@Setter
public final class AcountAdded implements Transaction {
    private final String transactionType;
    private final String description;
    private final int timestamp;

    public AcountAdded(final int timestamp, final String transactionType) {
        this.timestamp = timestamp;
        this.description = "New account created";
        this.transactionType = transactionType;
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
