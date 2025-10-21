package org.poo.main.CommandsCenter.Commands;

import org.poo.fileio.CommandInput;
import org.poo.main.Account.Account;
import org.poo.main.Card.Card;
import org.poo.main.Elements;
import org.poo.main.Transactions.Transaction;
import org.poo.main.Transactions.TransactionBuilder;
import org.poo.main.User;

import java.util.Map;

/**
 * Command to delete a card from a user's account.
 */
public class DeleteCardCommand implements Command {
    private final Elements elements;
    private final String cardNumber;
    private final CommandInput command;

    public DeleteCardCommand(final Elements elements, final CommandInput command) {
        this.elements = elements;
        this.command = command;
        this.cardNumber = command.getCardNumber();
    }

    /**
     * Executes the delete card operation.
     */
    public void execute() {
        User user = elements.getUsers().get(command.getEmail());
        for (Map.Entry<String, Account> entry : user.getAccounts().entrySet()) {
            Account account = entry.getValue();
            for (Card card : account.getCards()) {
                if (card.getCardNumber().equals(cardNumber)) {
                    account.getCards().remove(card);
                    Transaction transaction = new TransactionBuilder()
                            .setType("deletecard")
                            .setTimestamp(command.getTimestamp())
                            .setCardNumber(cardNumber)
                            .setCardHolder(command.getEmail())
                            .setAccountIban(account.getIBAN())
                            .build();
                    user.addTransaction(transaction);
                    break;
                }
            }
        }
    }
}
