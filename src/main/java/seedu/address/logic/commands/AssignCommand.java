package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Assigns an existing task to an existing person in the address book.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns the task identified "
            + "by the index number used in the last task listing "
            + "to the person identified "
            + "by the index number used in the last person listing. "
            + "Does nothing if the task is already assigned to the person.\n"
            + "Parameters: TASK_INDEX (must be a positive integer) "
            + "to/ [PERSON_INDEX (must be a positive integer)]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "to/ 2";

    public static final String MESSAGE_ARGUMENTS = "Task index: %1$d, Person index: %2$s";

    private final Index taskIndex;
    private final Index personIndex;

    /**
     * @param taskIndex of the task in the filtered task list to be assigned to the person
     * @param personIndex of the person in the filtered person list to be assigned the task
     */
    public AssignCommand(Index taskIndex, Index personIndex) {
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
        if (!(other instanceof AssignCommand)) {
            return false;
        }

        AssignCommand e = (AssignCommand) other;
        return taskIndex.equals(e.taskIndex)
                && personIndex.equals(e.personIndex);
    }
}
