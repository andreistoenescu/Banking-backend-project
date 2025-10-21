package org.poo.main.CommandsCenter.Commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.Account.Account;
import org.poo.main.Elements;
import org.poo.main.OutputJson.SavingsAccountNotOutputInterest;

import java.util.ArrayList;

/**
 * Command to add interest rate to an account.
 */
public class AddInterestRateCommand implements Command {
    private final Elements elements;
    private final CommandInput command;
    private final ArrayList<ObjectNode> outputList;

    public AddInterestRateCommand(final Elements elements, final CommandInput command,
                                  final ArrayList<ObjectNode> outputList) {
        this.elements = elements;
        this.command = command;
        this.outputList = outputList;
    }

    /**
     * Executes the command to apply interest to an account.
     */
    @Override
    public void execute() {
        Account account = elements.findAccount(command.getAccount());
        if (account.getInterestRate() != -1) {
            double amount = account.getInterestRate() * account.getBalance();
            account.addFunds(amount);
        } else {
            SavingsAccountNotOutputInterest.
                    generateNotSavingAccount(outputList, command.getTimestamp());
        }
    }
}
