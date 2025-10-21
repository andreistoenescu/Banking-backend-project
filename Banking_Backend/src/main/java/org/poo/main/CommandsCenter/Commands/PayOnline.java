package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.Account.Account;
import org.poo.main.Card.Card;
import org.poo.main.Elements;
import org.poo.main.OutputJson.CardNotFoundOutput;
import org.poo.main.Transactions.Transaction;
import org.poo.main.Transactions.TransactionBuilder;
import org.poo.main.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * Command to process an online payment using a card.
 */
public class PayOnline implements Command {
    private final Elements elements;
    private final CommandInput command;
    private final ArrayList<ObjectNode> outputList;

    public PayOnline(final Elements elements, final CommandInput command,
                     final ArrayList<ObjectNode> outputList) {
        this.elements = elements;
        this.command = command;
        this.outputList = outputList;
    }

    /**
     * Executes the online payment operation.
     */
    public void execute() {
        boolean found = false;
        User user = elements.getUsers().get(command.getEmail());

        for (Map.Entry<String, Account> entry : user.getAccounts().entrySet()) {
            Account account = entry.getValue();

            for (int i = 0; i < account.getCards().size(); i++) {
                Card card = account.getCards().get(i);
                if (card.getCardNumber().equals(command.getCardNumber())) {
                    found = true;

                    if (card.getStatus().equalsIgnoreCase("frozen")) {
                        Transaction transaction = new TransactionBuilder()
                                .setType("stillfrozen")
                                .setTimestamp(command.getTimestamp())
                                .build();
                        user.addTransaction(transaction);
                        return;
                    }

                    double payingAmount = command.getAmount();
                    if (!command.getCurrency().equalsIgnoreCase(account.getCurrency())) {
                        payingAmount = elements.getExchangeRate(command.getCurrency(),
                                account.getCurrency()) * payingAmount;
                    }

                    if (account.getBalance() < payingAmount) {
                        Transaction transaction = new TransactionBuilder()
                                .setType("nofunds")
                                .setTimestamp(command.getTimestamp())
                                .build();
                        user.addTransaction(transaction);
                        return;
                    }

                    account.setBalance(account.getBalance() - payingAmount);
                    Transaction transaction = new TransactionBuilder()
                            .setType("payment")
                            .setAmount(payingAmount)
                            .setTimestamp(command.getTimestamp())
                            .setCommerciant(command.getCommerciant())
                            .build();
                    user.addTransaction(transaction);
                    account.addTransaction(transaction);
                    account.addSpendingTransaction(transaction);

                    if (card.getType().equals("onetime")) {
                        card.use();
                        Transaction transactionCard = new TransactionBuilder()
                                .setType("deletecard")
                                .setTimestamp(command.getTimestamp())
                                .setCardNumber(card.getCardNumber())
                                .setCardHolder(command.getEmail())
                                .setAccountIban(account.getIBAN())
                                .build();
                        account.getCards().remove(i);
                        Card cardAdded = account.getCards().getLast();
                        Transaction transactionAdd = new TransactionBuilder()
                                .setType("cardadded")
                                .setTimestamp(command.getTimestamp())
                                .setCardNumber(cardAdded.getCardNumber())
                                .setCardHolder(command.getEmail())
                                .setAccountIban(account.getIBAN())
                                .build();
                        user.addTransaction(transactionCard);
                        user.addTransaction(transactionAdd);
                    }

                    return;
                }
            }
        }

        if (!found) {
            CardNotFoundOutput.generateNoExistentCard(outputList, command.getTimestamp());
        }
    }
}
