package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.Elements;
import org.poo.main.Transactions.Transaction;
import org.poo.main.Transactions.TransactionBuilder;

import java.util.ArrayList;

/**
 * This command creates a new card for a specified account.
 */
public final class CreateCardCommand implements Command {
    private final Elements elements;
    private final CommandInput command;
    private final ArrayList<ObjectNode> outputList;

    public CreateCardCommand(final Elements elements, final CommandInput command,
                             final ArrayList<ObjectNode> outputList) {
        this.elements = elements;
        this.command = command;
        this.outputList = outputList;
    }

    @Override
    public void execute() {
        if (elements.getUsers().get(command.getEmail()) != null) {
            elements.getUsers().get(command.getEmail()).addCard("classic", command.getAccount());
            String cardNumber = elements.getUsers().get(command.getEmail())
                    .getAccounts().get(command.getAccount()).getCards().getLast().getCardNumber();

            Transaction transaction = new TransactionBuilder()
                    .setType("cardadded")
                    .setTimestamp(command.getTimestamp())
                    .setCardNumber(cardNumber)
                    .setCardHolder(command.getEmail())
                    .setAccountIban(command.getAccount())
                    .build();

            elements.getUsers().get(command.getEmail()).
                    addTransaction(transaction);
            elements.getUsers().get(command.getEmail()).getAccounts().
                    get(command.getAccount()).addTransaction(transaction);
        }
    }
}
