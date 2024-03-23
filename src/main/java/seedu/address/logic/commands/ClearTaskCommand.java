package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Unassigns all tasks assigned to an existing person in the address book.
 */
public class ClearTaskCommand extends Command {

    public static final String COMMAND_WORD = "cleartask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unassigns all tasks assigned to the person identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: PERSON_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "ClearTask command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
