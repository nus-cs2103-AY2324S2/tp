package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
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

    public static final String MESSAGE_ARGUMENTS = "Task Index: %1$d, Person Index: %2$s";

    private final Index taskIndex;
    private final Index personIndex;

    /**
     * @param taskIndex of the task in the filtered task list to be unassigned to the person
     * @param personIndex of the person in the filtered person list to be unassigned the task
     */
    public UnassignCommand(Index taskIndex, Index personIndex) {
        requireAllNonNull(taskIndex, personIndex);

        this.taskIndex = taskIndex;
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, taskIndex.getOneBased(), personIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnassignCommand)) {
            return false;
        }

        UnassignCommand e = (UnassignCommand) other;
        return taskIndex.equals(e.taskIndex)
                && personIndex.equals(e.personIndex);
    }
}
