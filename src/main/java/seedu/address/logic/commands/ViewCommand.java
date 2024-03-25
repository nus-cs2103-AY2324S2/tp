package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 * This abstract class is extended by commands which are used to view different aspects
 * of a client in the address book application. It provides the structure and shared
 * properties for viewing commands.
 */
public abstract class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View a client. \n"
            + "Parameters: \n"
            + "index - Index shown in the corresponding contact list\n"
            + "Example: " + COMMAND_WORD + "c 2";

    public static final String MESSAGE_SUCCESS = "Success message: ViewCommand";
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
