package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a task in the task list.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deletetask";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Deleted Task: %1$s";
    public static final String MESSAGE_INDEX_TOO_LARGE = "The index is not valid, use \"listtask\" to "
            + "display all tasks.";

    public static final String MESSAGE_INDEX_BELOW_ONE = "The index must be greater than 0.";

    private final Index taskIndexToDelete;

    /**
     * Creates an DeleteTaskCommand to add the specified {@code index}
     */
    public DeleteTaskCommand(Index index) {
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
        List<Task> lastShownList = model.getTaskList().getSerializeTaskList();

        if (taskIndexToDelete.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INDEX_TOO_LARGE);
        }

        Task taskToDelete = lastShownList.get(taskIndexToDelete.getZeroBased());
        model.deleteTask(taskToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatTask(taskToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        DeleteTaskCommand otherDeleteCommand = (DeleteTaskCommand) other;
        return taskIndexToDelete.equals(otherDeleteCommand.taskIndexToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDelete", taskIndexToDelete.getOneBased())
                .toString();
    }
}
