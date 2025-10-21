package org.poo.main.OutputJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.main.Transactions.Transaction;
import java.util.ArrayList;

public final class PrintTransactionsOutput {
    private PrintTransactionsOutput() {
        // private constructor to hide implicit public one
    }

    /**
     * Generates output for printing transactions.
     *
     * @param outputList List to add the output to
     * @param transactions List of transactions to print
     * @param timestamp Current timestamp
     */
    public static void generatePrintTransactionsOutput(
            final ArrayList<ObjectNode> outputList,
            final ArrayList<Transaction> transactions,
            final int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode commandNode = mapper.createObjectNode();
        commandNode.put("command", "printTransactions");
        ArrayNode outputArray = mapper.createArrayNode();
        for (Transaction transaction : transactions) {
            outputArray.add(transaction.toObjectNode());
        }
        commandNode.set("output", outputArray);
        commandNode.put("timestamp", timestamp);
        outputList.add(commandNode);
    }
}
