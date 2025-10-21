package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.Elements;
import org.poo.main.OutputJson.DeleteAccountOutput;
import org.poo.main.Transactions.Transaction;
import org.poo.main.Transactions.TransactionBuilder;

import java.util.ArrayList;

/**
 * Command class for handling account removal operations.
 */
public final class RemoveAccountCommand implements Command {
    private final Elements elements;
    private final String email;
    private final ArrayList<ObjectNode> outputList;
    private final int timestamp;
    private final String iban;

    public RemoveAccountCommand(final Elements elements, final CommandInput command,
                                final ArrayList<ObjectNode> outputList) {
        this.elements = elements;
        this.outputList = outputList;
        this.timestamp = command.getTimestamp();
        this.iban = command.getAccount();
        this.email = command.getEmail();
    }

    /**
     * Executes the remove account command.
     */
    public void execute() {
        if (elements.getUsers().get(email).getAccounts().get(iban) != null) {
            if (elements.getUsers().get(email).getAccounts().get(iban).getBalance() == 0.) {
                elements.getUsers().get(email).getAccounts().remove(iban);
                DeleteAccountOutput.generateDeleteAccountOutput(outputList, timestamp);
            } else {
                DeleteAccountOutput.generateDeleteAccountOutputFail(outputList, timestamp);
                Transaction transaction = new TransactionBuilder()
                        .setTimestamp(timestamp)
                        .setType("nodeleteaccount")
                        .build();
                elements.getUsers().get(email).addTransaction(transaction);
            }
        }
    }
}
