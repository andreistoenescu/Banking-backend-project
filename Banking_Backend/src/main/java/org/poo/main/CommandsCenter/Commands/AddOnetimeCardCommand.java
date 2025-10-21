package org.poo.main.CommandsCenter.Commands;

import org.poo.fileio.CommandInput;
import org.poo.main.Elements;
import org.poo.main.Transactions.Transaction;
import org.poo.main.Transactions.TransactionBuilder;

/**
 * Command to add a one-time card to an account.
 */
public class AddOnetimeCardCommand implements Command {
    private final Elements elements;
    private final CommandInput command;

    public AddOnetimeCardCommand(final Elements elements, final CommandInput command) {
        this.elements = elements;
        this.command = command;
    }

    /**
     * Executes the command to add a one-time card.
     */
    @Override
    public void execute() {
        elements.getUsers().get(command.getEmail())
                .addCard("onetime", command.getAccount());

        String cardNumber = elements.getUsers().get(command.getEmail())
                .getAccounts().get(command.getAccount())
                .getCards().getLast().getCardNumber();

        Transaction transaction = new TransactionBuilder()
                .setType("cardadded")
                .setTimestamp(command.getTimestamp())
                .setCardNumber(cardNumber)
                .setCardHolder(command.getEmail())
                .setAccountIban(command.getAccount())
                .build();

        elements.getUsers().get(command.getEmail()).addTransaction(transaction);
        elements.getUsers().get(command.getEmail())
                .getAccounts().get(command.getAccount()).addTransaction(transaction);
    }
}
