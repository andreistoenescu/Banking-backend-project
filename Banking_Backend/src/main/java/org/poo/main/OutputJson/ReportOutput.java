package org.poo.main.OutputJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.main.Account.Account;
import org.poo.main.Transactions.Transaction;
import java.util.ArrayList;

public final class ReportOutput {
    private ReportOutput() {
        // private constructor to hide implicit public one
    }

    /**
     * Generates report output for an account.
     *
     * @param outputList List to add the output to
     * @param account Account to generate report for
     * @param timestamp Current timestamp
     * @param timestampFrom Start timestamp for report
     * @param timestampTo End timestamp for report
     */
    public static void generateRaportOutput(
            final ArrayList<ObjectNode> outputList,
            final Account account,
            final int timestamp,
            final int timestampFrom,
            final int timestampTo) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode reportNode = objectMapper.createObjectNode();
        reportNode.put("command", "report");
        ObjectNode outputNode = objectMapper.createObjectNode();
        outputNode.put("IBAN", account.getIBAN());
        outputNode.put("balance", account.getBalance());
        outputNode.put("currency", account.getCurrency());
        ArrayNode transactionsNode = objectMapper.createArrayNode();
        for (Transaction transaction : account.getTransactions()) {
            if (transaction.getTimestamp() >= timestampFrom
                    && transaction.getTimestamp() <= timestampTo) {
                transactionsNode.add(transaction.toObjectNode());
            }
        }
        outputNode.set("transactions", transactionsNode);
        reportNode.set("output", outputNode);
        reportNode.put("timestamp", timestamp);
        outputList.add(reportNode);
    }

    /**
     * Generates output for when account is not found.
     *
     * @param outputList List to add the output to
     * @param timestamp Current timestamp
     */
    public static void generateAccountNotFound(
            final ArrayList<ObjectNode> outputList,
            final int timestamp) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode reportNode = objectMapper.createObjectNode();
        reportNode.put("command", "report");
        ObjectNode outputNode = objectMapper.createObjectNode();
        outputNode.put("description", "Account not found");
        outputNode.put("timestamp", timestamp);
        reportNode.set("output", outputNode);
        reportNode.put("timestamp", timestamp);
        outputList.add(reportNode);
    }
}
