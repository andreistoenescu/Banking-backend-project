package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;

/**
 * Represents a transaction where the interest rate is changed.
 */
@Getter
public final class ChangeInteresRate implements Transaction {
    private final int timestamp;
    private final String description;
    private final double interestRate;

    public ChangeInteresRate(final int timestamp, final double interestRate) {
        this.timestamp = timestamp;
        this.description = "Interest rate of the account changed to ";
        this.interestRate = interestRate;
    }

    @Override
    public ObjectNode toObjectNode() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode transactionNode = mapper.createObjectNode();
        transactionNode.put("timestamp", this.getTimestamp());
        transactionNode.put("description", this.getDescription() + interestRate);
        return transactionNode;
    }
}
