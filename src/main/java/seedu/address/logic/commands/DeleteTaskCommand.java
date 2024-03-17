package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a task to the task list.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deletetask";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "<index> : Deletes a task in the task list.";
    public static final String MESSAGE_SUCCESS = "Task number %1$s has been deleted.";
    public static final String MESSAGE_INDEX_TOO_LARGE = "The index is not valid, use \"list task\" to "
            + "display all valid tasks.";
    public static final String MESSAGE_INDEX_BELOW_ONE = "The index must be greater than 0.";

    private final Integer taskIndexToDelete;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public DeleteTaskCommand(int index) {
        taskIndexToDelete = index;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (taskIndexToDelete <= 0) {
            throw new CommandException(MESSAGE_INDEX_BELOW_ONE);
        }

        if (!model.isValidTaskIndex(taskIndexToDelete)) {
            throw new CommandException(MESSAGE_INDEX_TOO_LARGE);
        }

        model.deleteTask(taskIndexToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskIndexToDelete));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDelete", taskIndexToDelete)
                .toString();
    }
}
