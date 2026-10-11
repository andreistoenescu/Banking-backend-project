package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.Account.Account;
import org.poo.main.Elements;
import org.poo.main.OutputJson.SpendingTransactionOutput;

import java.util.ArrayList;

/**
 * Command class for handling spending report generation operations.
 */
public final class SpendingReportCommand implements Command {
    private final Elements elements;
    private final CommandInput command;
    private final ArrayList<ObjectNode> outputList;

    public SpendingReportCommand(final Elements elements, final CommandInput command,
                                 final ArrayList<ObjectNode> outputList) {
        this.elements = elements;
        this.command = command;
        this.outputList = outputList;
    }

    /**
     * Executes the spending report generation command.
     */
    public void execute() {
        Account account = elements.findAccount(command.getAccount());
        if (account != null) {
            if (account.getInterestRate() == -1) {
                SpendingTransactionOutput.generateSpendingTransactionOutput(
                        account, outputList, command.getTimestamp(),
                        command.getStartTimestamp(), command.getEndTimestamp());
            } else {
                SpendingTransactionOutput.generateSpendingError(outputList,
                        command.getTimestamp());
            }
        } else {
            SpendingTransactionOutput.generateSpendingAccountNotFound(outputList,
                    command.getTimestamp());
        }
    }
}
