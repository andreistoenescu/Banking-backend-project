package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.Elements;
import org.poo.main.Transactions.Transaction;
import org.poo.main.Transactions.TransactionBuilder;
import java.util.ArrayList;

/**
 * Command to add an account.
 */
public class AddAccountCommand implements Command {
    private final Elements elements;
    private final CommandInput command;
    private final ArrayList<ObjectNode> outputList;

    public AddAccountCommand(final Elements elements, final CommandInput input,
                             final ArrayList<ObjectNode> outputList) {
        this.elements = elements;
        this.command = input;
        this.outputList = outputList;
    }

    /**
     * Executes the command to add an account.
     */
    @Override
    public void execute() {
        Transaction transaction = new TransactionBuilder()
                .setType("accountadded")
                .setTimestamp(command.getTimestamp())
                .build();

        elements.getUsers().get(command.getEmail())
                .addAccount(command.getAccountType(),
                        command.getInterestRate(), command.getCurrency(), transaction);

        elements.getUsers().get(command.getEmail()).addTransaction(transaction);
    }
}
