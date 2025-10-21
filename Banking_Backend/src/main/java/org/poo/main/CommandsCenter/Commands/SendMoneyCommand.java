package org.poo.main.CommandsCenter.Commands;

import org.poo.fileio.CommandInput;
import org.poo.main.Account.Account;
import org.poo.main.Elements;
import org.poo.main.Transactions.Transaction;
import org.poo.main.Transactions.TransactionBuilder;
import org.poo.main.User;

/**
 * Command class for handling money transfer operations between accounts.
 */
public final class SendMoneyCommand implements Command {
    private final Elements elements;
    private final CommandInput input;

    public SendMoneyCommand(final Elements elements, final CommandInput input) {
        this.elements = elements;
        this.input = input;
    }

    /**
     * Executes the money transfer command.
     */
    public void execute() {
        User sender = elements.getUsers().get(input.getEmail());
        Account senderAccount = sender.getAccounts().get(input.getAccount());
        Account receiverAccount = determineReceiverAccount(sender);

        if (senderAccount == null || receiverAccount == null) {
            return;
        }

        processMoneyTransfer(senderAccount, receiverAccount);
    }

    private Account determineReceiverAccount(final User sender) {
        Account aliasAccount = sender.getAccountAlias(input.getAlias());
        if (aliasAccount != null && aliasAccount.getIBAN().equals(input.getAccount())) {
            return aliasAccount;
        }
        return elements.findAccount(input.getReceiver());
    }

    private void processMoneyTransfer(final Account senderAccount,
                                      final Account receiverAccount) {
        double amountToSend = input.getAmount();
        double amountToReceive = convertCurrencyIfNeeded(senderAccount,
                receiverAccount, amountToSend);

        if (hasSufficientFunds(senderAccount, amountToSend)) {
            performTransfer(senderAccount, receiverAccount, amountToSend, amountToReceive);
        } else {
            recordInsufficientFundsTransaction();
        }
    }

    private double convertCurrencyIfNeeded(final Account senderAccount,
                                           final Account receiverAccount, final double amount) {
        if (!senderAccount.getCurrency().equals(receiverAccount.getCurrency())) {
            return elements.getExchangeRate(senderAccount.getCurrency(),
                    receiverAccount.getCurrency()) * amount;
        }
        return amount;
    }

    private boolean hasSufficientFunds(final Account senderAccount,
                                       final double amount) {
        return senderAccount.getBalance() >= amount
                && senderAccount.getBalance() - amount >= 0;
    }

    private void performTransfer(final Account senderAccount,
                                 final Account receiverAccount,
                                 final double amountSent, final double amountReceived) {
        senderAccount.setBalance(senderAccount.getBalance() - amountSent);
        receiverAccount.setBalance(receiverAccount.getBalance() + amountReceived);

        Transaction senderTransaction = createTransaction(senderAccount,
                receiverAccount, amountSent, "sent");
        elements.getUsers().get(input.getEmail()).addTransaction(senderTransaction);
        elements.getUsers().get(input.getEmail()).getAccounts()
                .get(senderAccount.getIBAN()).addTransaction(senderTransaction);

        recordReceiverTransaction(senderAccount, receiverAccount, amountReceived);
    }

    private Transaction createTransaction(final Account senderAccount,
                                          final Account receiverAccount,
                                          final double amount, final String transferType) {
        return new TransactionBuilder()
                .setType("transfer")
                .setDescription(input.getDescription())
                .setSenderIban(senderAccount.getIBAN())
                .setReceiverIban(receiverAccount.getIBAN())
                .setAmount(amount)
                .setTransferType(transferType)
                .setTimestamp(input.getTimestamp())
                .setCurrency(senderAccount.getCurrency())
                .build();
    }

    private void recordReceiverTransaction(final Account senderAccount,
                                           final Account receiverAccount,
                                           final double amountReceived) {
        User receiverUser = elements.findUserbyAccount(receiverAccount.getIBAN());
        if (receiverUser != null) {
            Transaction receiverTransaction = new TransactionBuilder()
                    .setType("transfer")
                    .setDescription(input.getDescription())
                    .setSenderIban(senderAccount.getIBAN())
                    .setReceiverIban(receiverAccount.getIBAN())
                    .setAmount(amountReceived)
                    .setTransferType("received")
                    .setTimestamp(input.getTimestamp())
                    .setCurrency(receiverAccount.getCurrency())
                    .build();

            receiverUser.addTransaction(receiverTransaction);
            receiverUser.getAccounts().get(receiverAccount.getIBAN())
                    .addTransaction(receiverTransaction);
        }
    }

    private void recordInsufficientFundsTransaction() {
        Transaction insufficientFundsTransaction = new TransactionBuilder()
                .setType("nofunds")
                .setTimestamp(input.getTimestamp())
                .build();
        elements.getUsers().get(input.getEmail())
                .addTransaction(insufficientFundsTransaction);
        elements.getUsers().get(input.getEmail()).getAccounts()
                .get(input.getAccount()).addTransaction(insufficientFundsTransaction);
    }
}
