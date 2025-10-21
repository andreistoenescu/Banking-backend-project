package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.Elements;
import org.poo.main.OutputJson.PrintTransactionsOutput;

import java.util.ArrayList;

/**
 * Command to print all transactions for a user.
 */
public class PrintTransactionsCommand implements Command {
    private final Elements elements;
    private final ArrayList<ObjectNode> outputList;
    private final CommandInput command;

    public PrintTransactionsCommand(final Elements elements,
                                    final ArrayList<ObjectNode> outputList,
                                    final CommandInput command) {
        this.elements = elements;
        this.outputList = outputList;
        this.command = command;
    }

    /**
     * Executes the print transactions operation.
     */
    public void execute() {
        PrintTransactionsOutput.generatePrintTransactionsOutput(outputList,
                elements.getUsers().get(command.getEmail()).getTransaction(),
                command.getTimestamp());
    }
}
