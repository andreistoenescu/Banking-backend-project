package org.poo.main.OutputJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;

public final class DeleteAccountOutput {
    private DeleteAccountOutput() {
        // private constructor to hide implicit public one
    }

    /**
     * Generates output for successful account deletion.
     *
     * @param outputList List to add the output to
     * @param timestamp Current timestamp
     */
    public static void generateDeleteAccountOutput(final ArrayList<ObjectNode>
                                                           outputList, final int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode commandNode = mapper.createObjectNode();
        commandNode.put("command", "deleteAccount");
        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("success", "Account deleted");
        outputNode.put("timestamp", timestamp);
        commandNode.set("output", outputNode);
        commandNode.put("timestamp", timestamp);
        outputList.add(commandNode);
    }

    /**
     * Generates output for failed account deletion.
     *
     * @param outputList List to add the output to
     * @param timestamp Current timestamp
     */
    public static void generateDeleteAccountOutputFail(final ArrayList<ObjectNode> outputList,
                                                       final int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode commandNode = mapper.createObjectNode();
        commandNode.put("command", "deleteAccount");
        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("error",
                "Account couldn't be deleted - see org.poo.transactions for details");
        outputNode.put("timestamp", timestamp);
        commandNode.set("output", outputNode);
        commandNode.put("timestamp", timestamp);
        outputList.add(commandNode);
    }
}
