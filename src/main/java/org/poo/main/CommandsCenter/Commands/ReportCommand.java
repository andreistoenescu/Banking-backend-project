package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.Account.Account;
import org.poo.main.Elements;
import org.poo.main.OutputJson.ReportOutput;

import java.util.ArrayList;

/**
 * Command class for handling report generation operations.
 */
public final class ReportCommand implements Command {
    private final Elements elements;
    private final CommandInput command;
    private final ArrayList<ObjectNode> outputList;

    public ReportCommand(final Elements elements, final CommandInput command,
                         final ArrayList<ObjectNode> outputList) {
        this.elements = elements;
        this.command = command;
        this.outputList = outputList;
    }

    /**
     * Executes the report generation command.
     */
    public void execute() {
        Account account = elements.findAccount(command.getAccount());
        if (account != null) {
            ReportOutput.generateRaportOutput(outputList, account,
                    command.getTimestamp(), command.getStartTimestamp(),
                    command.getEndTimestamp());
        } else {
            ReportOutput.generateAccountNotFound(outputList, command.getTimestamp());
        }
    }
}
