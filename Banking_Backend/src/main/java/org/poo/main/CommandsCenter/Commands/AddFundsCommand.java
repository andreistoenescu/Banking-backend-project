package org.poo.main.CommandsCenter.Commands;

import org.poo.main.Elements;
import org.poo.main.User;

import java.util.Map;

/**
 * Command to add funds to a specified account.
 */
public class AddFundsCommand implements Command {
    private final Elements elements;
    private final double amount;
    private final String iban;

    public AddFundsCommand(final Elements elements, final double amount, final String iban) {
        this.elements = elements;
        this.amount = amount;
        this.iban = iban;
    }

    /**
     * Executes the command to add funds to an account.
     */
    @Override
    public void execute() {
        for (Map.Entry<String, User> userEntry : elements.getUsers().entrySet()) {
            User user = userEntry.getValue();
            if (user.getAccounts().get(iban) != null) {
                user.getAccounts().get(iban).addFunds(amount);
            }
        }
    }
}
