package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.Account.Account;
import org.poo.main.Elements;
import org.poo.main.OutputJson.SavingsAccountNotOutput;
import org.poo.main.Transactions.Transaction;
import org.poo.main.Transactions.TransactionBuilder;

import java.util.ArrayList;

/**
 * This command changes the interest rate of a savings account.
 */
public final class ChangeInterestRateCommand implements Command {
    private final Elements elements;
    private final CommandInput command;
    private final ArrayList<ObjectNode> outputList;

    public ChangeInterestRateCommand(final Elements elements, final CommandInput command,
                                     final ArrayList<ObjectNode> outputList) {
        this.elements = elements;
        this.command = command;
        this.outputList = outputList;
    }

    @Override
    public void execute() {
        Account account = elements.findAccount(command.getAccount());
        if (account.getInterestRate() != -1) {
            account.setInterestRate(command.getInterestRate());
            Transaction transaction = new TransactionBuilder()
                    .setTimestamp(command.getTimestamp())
                    .setType("changeinterest")
                    .setAmount(command.getInterestRate())
                    .build();
            elements.findUserbyAccount(command.getAccount()).addTransaction(transaction);
        } else {
            SavingsAccountNotOutput.generateNotSavingAccount(outputList, command.getTimestamp());
        }
    }
}
