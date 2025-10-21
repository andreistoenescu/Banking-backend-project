package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.Account.Account;
import org.poo.main.Card.Card;
import org.poo.main.Elements;
import org.poo.main.OutputJson.CardNotFoundCheckStatus;
import org.poo.main.Transactions.Transaction;
import org.poo.main.Transactions.TransactionBuilder;
import org.poo.main.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * This command checks the status of a card and freezes it if the balance is below the minimum.
 */
public final class CardStatusCommand implements Command {
    private final Elements elements;
    private final CommandInput command;
    private final ArrayList<ObjectNode> outputList;

    public CardStatusCommand(final Elements elements, final CommandInput command,
                             final ArrayList<ObjectNode> outputList) {
        this.elements = elements;
        this.command = command;
        this.outputList = outputList;
    }

    @Override
    public void execute() {
        boolean found = false;

        for (Map.Entry<String, User> userEntry : elements.getUsers().entrySet()) {
            User user = userEntry.getValue();
            for (Map.Entry<String, Account> accountEntry : user.getAccounts().entrySet()) {
                Account account = accountEntry.getValue();
                for (Card card : account.getCards()) {
                    if (card.getCardNumber().equals(command.getCardNumber())) {
                        found = true;
                        if (account.getMinimumBalance() >= account.getBalance()) {
                            card.setStatus("frozen");
                            Transaction transaction = new TransactionBuilder()
                                    .setType("gettingfrozen")
                                    .setTimestamp(command.getTimestamp())
                                    .build();
                            user.addTransaction(transaction);
                            return;
                        }
                    }
                }
            }
        }
        if (!found) {
            CardNotFoundCheckStatus.generateNoExistentCard(outputList, command.getTimestamp());
        }
    }
}
