package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be
 * executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Returns the command word of the command.
     *
     * @return Command word of the command.
     */
    public abstract String getCommandWord();

    /**
     * Returns the usage message of the command.
     *
     * @return Usage message of the command.
     */
    public abstract String getMessageUsage();

}
