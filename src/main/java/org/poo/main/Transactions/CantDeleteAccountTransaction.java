package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a transaction where account deletion fails due to remaining funds.
 */
@Getter
@Setter
public final class CantDeleteAccountTransaction implements Transaction {
    private final int timestamp;
    private final String description;
    private final String transactionType = "CantDeleteAccount";

    public CantDeleteAccountTransaction(final int timestamp) {
        this.timestamp = timestamp;
        this.description = "Account couldn't be deleted - there are funds remaining";
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

