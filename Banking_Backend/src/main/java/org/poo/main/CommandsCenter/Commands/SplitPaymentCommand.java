package org.poo.main.CommandsCenter.Commands;

import org.poo.fileio.CommandInput;
import org.poo.main.Account.Account;
import org.poo.main.Elements;
import org.poo.main.Transactions.Transaction;
import org.poo.main.Transactions.TransactionBuilder;
import org.poo.main.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Command class for handling split payment operations between multiple accounts.
 */
public final class SplitPaymentCommand implements Command {
    private final CommandInput command;
    private final Elements elements;

    public SplitPaymentCommand(final Elements elements, final CommandInput command) {
        this.command = command;
        this.elements = elements;
    }

    /**
     * Executes the split payment command.
     */
    public void execute() {
        List<Account> accountsForSplit = new ArrayList<>();
        List<Account> insufficientFundsAccounts = new ArrayList<>();
        double amountToPayForEach = command.getAmount() / command.getAccounts().size();

        for (String iban : command.getAccounts()) {
            Account account = elements.findAccount(iban);

            if (account == null) {
                System.err.println("Account not found for IBAN: " + iban);
                continue;
            }

            accountsForSplit.add(account);

            double convertedAmount = amountToPayForEach;
            if (!command.getCurrency().equalsIgnoreCase(account.getCurrency())) {
                convertedAmount = elements.getExchangeRate(command.getCurrency(),
                        account.getCurrency()) * amountToPayForEach;
            }

            if (account.getBalance() < convertedAmount) {
                insufficientFundsAccounts.add(account);
            }
        }

        if (!insufficientFundsAccounts.isEmpty()) {
            Transaction transaction = new TransactionBuilder()
                    .setTimestamp(command.getTimestamp())
                    .setAmount(command.getAmount())
                    .setCurrency(command.getCurrency())
                    .setAccountsInvolved(command.getAccounts())
                    .setType("splitnot")
                    .setErrorAccount(insufficientFundsAccounts
                            .get(insufficientFundsAccounts.size() - 1).getIBAN())
                    .build();

            for (String iban : command.getAccounts()) {
                User user = elements.findUserbyAccount(iban);
                if (user != null) {
                    user.addTransaction(transaction);
                    user.getAccounts().get(iban).addTransaction(transaction);
                }
            }
            return;
        }

        for (Account account : accountsForSplit) {
            double convertedAmount = amountToPayForEach;
            if (!command.getCurrency().equalsIgnoreCase(account.getCurrency())) {
                convertedAmount = elements.getExchangeRate(command.getCurrency(),
                        account.getCurrency()) * amountToPayForEach;
            }

            account.setBalance(account.getBalance() - convertedAmount);

            Transaction transaction = new TransactionBuilder()
                    .setTimestamp(command.getTimestamp())
                    .setAmount(command.getAmount())
                    .setCurrency(command.getCurrency())
                    .setAccountsInvolved(command.getAccounts())
                    .setType("split")
                    .build();

            User user = elements.findUserbyAccount(account.getIBAN());
            if (user != null) {
                user.addTransaction(transaction);
            }
        }
    }
}
