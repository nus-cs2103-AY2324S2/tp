package seedu.findvisor.logic.commands;

import seedu.findvisor.logic.commands.exceptions.CommandException;
import seedu.findvisor.model.Model;

/**
 * Edits a remark to an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a remark of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Plans to own a property by the age of 30.";

    public static final String MESSAGE_WORK_IN_PROGRESS = "Command is still a work in progress.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_WORK_IN_PROGRESS);
    }
}
