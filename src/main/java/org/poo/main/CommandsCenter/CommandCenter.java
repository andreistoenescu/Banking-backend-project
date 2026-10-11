package org.poo.main.CommandsCenter;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;
import org.poo.main.CommandsCenter.Commands.AddAccountCommand;
import org.poo.main.CommandsCenter.Commands.AddFundsCommand;
import org.poo.main.CommandsCenter.Commands.AddInterestRateCommand;
import org.poo.main.CommandsCenter.Commands.AddOnetimeCardCommand;
import org.poo.main.CommandsCenter.Commands.CardStatusCommand;
import org.poo.main.CommandsCenter.Commands.ChangeInterestRateCommand;
import org.poo.main.CommandsCenter.Commands.Command;
import org.poo.main.CommandsCenter.Commands.CreateCardCommand;
import org.poo.main.CommandsCenter.Commands.DeleteCardCommand;
import org.poo.main.CommandsCenter.Commands.PayOnline;
import org.poo.main.CommandsCenter.Commands.PrintTransactionsCommand;
import org.poo.main.CommandsCenter.Commands.PrintUsersCommand;
import org.poo.main.CommandsCenter.Commands.RemoveAccountCommand;
import org.poo.main.CommandsCenter.Commands.ReportCommand;
import org.poo.main.CommandsCenter.Commands.SendMoneyCommand;
import org.poo.main.CommandsCenter.Commands.SetAliasCommand;
import org.poo.main.CommandsCenter.Commands.SetMinimumBalanceCommand;
import org.poo.main.CommandsCenter.Commands.SpendingReportCommand;
import org.poo.main.CommandsCenter.Commands.SplitPaymentCommand;
import org.poo.main.Elements;

import java.util.ArrayList;

/**
 * The CommandCenter class processes and executes various banking commands.
 * It is responsible for interpreting command inputs and triggering appropriate actions.
 */
public final class CommandCenter {

    private final Elements elements;
    private final ArrayList<CommandInput> commandInput;
    private ArrayList<ObjectNode> outputList;

    /**
     * Constructs a CommandCenter object.
     *
     * @param input Input data containing commands and elements.
     */
    public CommandCenter(final ObjectInput input) {
        this.elements = new Elements();
        this.elements.addUser(input);
        this.elements.addExchangeRate(input);

        this.outputList = new ArrayList<>();
        this.commandInput = new ArrayList<>();

        for (final CommandInput inputCommand : input.getCommands()) {
            this.commandInput.add(inputCommand);
        }
    }

    /**
     * Executes a specific command based on the command input.
     *
     * @param command The command to be executed.
     * @return The executed Command object or null if no matching command.
     */
    public Command executeCommands(final CommandInput command) {
        switch (command.getCommand().toLowerCase()) {
            case "printusers":
                return new PrintUsersCommand(elements, outputList, command.getTimestamp());
            case "addaccount":
                return new AddAccountCommand(elements, command, outputList);
            case "createcard":
                return new CreateCardCommand(elements, command, outputList);
            case "addfunds":
                return new AddFundsCommand(elements, command.getAmount(), command.getAccount());
            case "deleteaccount":
                return new RemoveAccountCommand(elements, command, outputList);
            case "createonetimecard":
                return new AddOnetimeCardCommand(elements, command);
            case "deletecard":
                return new DeleteCardCommand(elements, command);
            case "payonline":
                return new PayOnline(elements, command, outputList);
            case "sendmoney":
                return new SendMoneyCommand(elements, command);
            case "setalias":
                return new SetAliasCommand(elements, command);
            case "printtransactions":
                return new PrintTransactionsCommand(elements, outputList, command);
            case "setminimumbalance":
                return new SetMinimumBalanceCommand(elements, command);
            case "checkcardstatus":
                return new CardStatusCommand(elements, command, outputList);
            case "splitpayment":
                return new SplitPaymentCommand(elements, command);
            case "report":
                return new ReportCommand(elements, command, outputList);
            case "spendingsreport":
                return new SpendingReportCommand(elements, command, outputList);
            case "changeinterestrate":
                return new ChangeInterestRateCommand(elements, command, outputList);
            case "addinterest":
                return new AddInterestRateCommand(elements, command, outputList);
            default:
                System.out.println("Unknown command: " + command.getCommand());
                break;
        }
        return null;
    }

    /**
     * Interprets and processes all commands from the input.
     *
     * @param input The input containing the list of commands.
     */
    public void interpretCommands(final ObjectInput input) {
        for (final CommandInput command : commandInput) {
            final Command cmd = executeCommands(command);
            if (cmd != null) {
                cmd.execute();
            }
        }
    }

    /**
     * Retrieves the output list.
     *
     * @return The list of outputs as JSON nodes.
     */
    public ArrayList<ObjectNode> getOutputList() {
        return outputList;
    }

    /**
     * Sets the output list.
     *
     * @param outputList The list of outputs to set.
     */
    public void setOutputList(final ArrayList<ObjectNode> outputList) {
        this.outputList = outputList;
    }
}
