package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipModel;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class InternshipCommand {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code InternshipModel} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(InternshipModel model) throws CommandException;

}
