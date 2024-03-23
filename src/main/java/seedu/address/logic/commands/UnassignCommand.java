package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Unassigns an existing task to an existing person in the address book.
 */
public class UnassignCommand extends Command {

    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unassigns the task identified "
            + "by the index number used in the last task listing "
            + "to the person identified "
            + "by the index number used in the last person listing. "
            + "Does nothing if the task not assigned to the person.\n"
            + "Parameters: TASK_INDEX (must be a positive integer) "
            + "to/ [PERSON_INDEX (must be a positive integer)]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "to/ 2";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Unassign command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
