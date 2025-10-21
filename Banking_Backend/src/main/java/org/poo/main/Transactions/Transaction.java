package org.poo.main.Transactions;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Interface representing a transaction with essential methods for serialization and timestamp retrieval.
 */
public interface Transaction {
    /**
     * Converts the transaction details to an ObjectNode for JSON serialization.
     *
     * @return the transaction details as an ObjectNode.
     */
    ObjectNode toObjectNode();

    /**
     * Retrieves the timestamp of the transaction.
     *
     * @return the transaction timestamp.
     */
    int getTimestamp();
}
