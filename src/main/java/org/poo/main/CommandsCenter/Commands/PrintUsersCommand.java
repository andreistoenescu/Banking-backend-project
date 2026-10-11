package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.main.Elements;

import java.util.ArrayList;

/**
 * Command to print all users.
 */
public class PrintUsersCommand implements Command {
    private final Elements elements;
    private final ArrayList<ObjectNode> outputList;
    private final int timestamp;

    public PrintUsersCommand(final Elements elements, final ArrayList<ObjectNode> outputList,
                             final int timestamp) {
        this.elements = elements;
        this.outputList = outputList;
        this.timestamp = timestamp;
    }

    /**
     * Executes the print users operation.
     */
    public void execute() {
        elements.printUsers(outputList, timestamp);
    }
}
