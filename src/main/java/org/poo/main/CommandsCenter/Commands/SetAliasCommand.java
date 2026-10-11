package org.poo.main.CommandsCenter.Commands;

import org.poo.fileio.CommandInput;
import org.poo.main.Account.Account;
import org.poo.main.Elements;

/**
 * Command class for handling alias setting operations.
 */
public final class SetAliasCommand implements Command {
    private final CommandInput input;
    private final Elements elements;

    public SetAliasCommand(final Elements elements, final CommandInput input) {
        this.elements = elements;
        this.input = input;
    }

    /**
     * Executes the set alias command.
     */
    public void execute() {
        Account account = elements.findAccount(input.getAccount());
        if (account == null) {
            System.out.println("No such account");
        }
        elements.getUsers().get(input.getEmail()).addAlias(input.getAlias(), account);
    }
}
