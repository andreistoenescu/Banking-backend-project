package org.poo.main.OutputJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;

public final class SavingsAccountNotOutput {
    private SavingsAccountNotOutput() {
        // private constructor to hide implicit public one
    }

    /**
     * Generates output for when account is not a savings account.
     *
     * @param outputList List to add the output to
     * @param timestamp Current timestamp
     */
    public static void generateNotSavingAccount(final ArrayList<ObjectNode> outputList,
                                                final int timestamp) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode reportNode = objectMapper.createObjectNode();
        reportNode.put("command", "changeInterestRate");

        ObjectNode outputNode = objectMapper.createObjectNode();
        outputNode.put("description", "This is not a savings account");
        outputNode.put("timestamp", timestamp);

        reportNode.set("output", outputNode);
        reportNode.put("timestamp", timestamp);

        outputList.add(reportNode);
    }
}
