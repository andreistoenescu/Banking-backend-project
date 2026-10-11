package org.poo.main.OutputJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.main.Account.Account;
import org.poo.main.Transactions.Transaction;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public final class SpendingTransactionOutput {
    private SpendingTransactionOutput() {
        // private constructor to hide implicit public one
    }

    /**
     * Generates spending transaction output report.
     *
     * @param account Account to generate report for
     * @param outputList List to add the output to
     * @param timestamp Current timestamp
     * @param timestampFrom Start timestamp for report
     * @param timestampTo End timestamp for report
     */
    public static void generateSpendingTransactionOutput(
            final Account account,
            final ArrayList<ObjectNode> outputList,
            final int timestamp,
            final int timestampFrom,
            final int timestampTo) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode reportNode = mapper.createObjectNode();
        reportNode.put("command", "spendingsReport");
        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("IBAN", account.getIBAN());
        outputNode.put("balance", account.getBalance());
        outputNode.put("currency", account.getCurrency());
        ArrayNode transactionsNode = mapper.createArrayNode();
        Map<String, Double> totals = new HashMap<>();

        for (Transaction transaction : account.getSpendingTransactions()) {
            if (transaction.getTimestamp() >= timestampFrom
                    && transaction.getTimestamp() <= timestampTo) {
                transactionsNode.add(transaction.toObjectNode());
                ObjectNode transNode = transaction.toObjectNode();
                String commerciant = transNode.get("commerciant").asText();
                double amount = transNode.get("amount").asDouble();
                totals.put(commerciant,
                        totals.getOrDefault(commerciant, 0.0) + amount);
            }
        }

        outputNode.set("transactions", transactionsNode);
        ArrayNode commerciantsNode = mapper.createArrayNode();
        totals.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    ObjectNode commerciantNode = mapper.createObjectNode();
                    commerciantNode.put("commerciant", entry.getKey());
                    commerciantNode.put("total", entry.getValue());
                    commerciantsNode.add(commerciantNode);
                });
        outputNode.set("commerciants", commerciantsNode);
        reportNode.set("output", outputNode);
        reportNode.put("timestamp", timestamp);
        outputList.add(reportNode);
    }

    /**
     * Generates output when account is not found.
     *
     * @param outputList List to add the output to
     * @param timestamp Current timestamp
     */
    public static void generateSpendingAccountNotFound(
            final ArrayList<ObjectNode> outputList,
            final int timestamp) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode reportNode = objectMapper.createObjectNode();
        reportNode.put("command", "spendingsReport");
        ObjectNode outputNode = objectMapper.createObjectNode();
        outputNode.put("description", "Account not found");
        outputNode.put("timestamp", timestamp);
        reportNode.set("output", outputNode);
        reportNode.put("timestamp", timestamp);
        outputList.add(reportNode);
    }

    /**
     * Generates error output for unsupported report type.
     *
     * @param outputList List to add the output to
     * @param timestamp Current timestamp
     */
    public static void generateSpendingError(
            final ArrayList<ObjectNode> outputList,
            final int timestamp) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode reportNode = objectMapper.createObjectNode();
        reportNode.put("command", "spendingsReport");
        ObjectNode outputNode = objectMapper.createObjectNode();
        outputNode.put("error",
                "This kind of report is not supported for a saving account");
        reportNode.set("output", outputNode);
        reportNode.put("timestamp", timestamp);
        outputList.add(reportNode);
    }
}
