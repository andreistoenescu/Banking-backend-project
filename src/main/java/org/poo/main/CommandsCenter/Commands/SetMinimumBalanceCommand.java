package org.poo.main.CommandsCenter.Commands;

import org.poo.fileio.CommandInput;
import org.poo.main.Elements;

/**
 * Command class for handling minimum balance setting operations.
 */
public final class SetMinimumBalanceCommand implements Command {
    private final Elements elements;
    private final CommandInput command;

    public SetMinimumBalanceCommand(final Elements elements, final CommandInput command) {
        this.elements = elements;
        this.command = command;
    }

    /**
     * Executes the set minimum balance command.
     */
    public void execute() {
        elements.findAccount(command.getAccount()).setMinimumBalance(command.getAmount());
    }
}
