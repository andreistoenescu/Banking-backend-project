package org.poo.main.CommandsCenter.Commands;

/**
 * Represents a command that can be executed.
 * <p>
 * This interface follows the Command design pattern and is intended
 * to encapsulate all command-related actions in the system.
 * Any class implementing this interface must provide an implementation
 * for the {@link #execute()} method.
 * </p>
 */
public interface Command {

    /**
     * Executes the command.
     * The behavior of this method is defined by the implementing class.
     * This method performs all actions associated with the command.
     */
    void execute();
}
