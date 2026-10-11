package org.poo.main.OutputJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;

public final class CardNotFoundCheckStatus {
    private CardNotFoundCheckStatus() {
        // private constructor to hide implicit public one
    }

    /**
     * Generates output for non-existent card status check.
     *
     * @param outputList List to add the output to
     * @param timestamp Current timestamp
     */
    public static void generateNoExistentCard(final ArrayList<ObjectNode> outputList,
                                              final int timestamp) {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode commandNode = mapper.createObjectNode();
        commandNode.put("command", "checkCardStatus");

        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("timestamp", timestamp);
        outputNode.put("description", "Card not found");

        commandNode.set("output", outputNode);
        commandNode.put("timestamp", timestamp);

        outputList.add(commandNode);
    }
}
