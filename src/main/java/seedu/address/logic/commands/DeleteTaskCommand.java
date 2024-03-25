package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the address book.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "deletetask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the task identified by the taskID used in the displayed task list.\n"
            + "Parameters: taskID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted task: %1$s";

    private final int targetIndex;

    public DeleteTaskCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        Task taskToDelete;

        for (Task t : lastShownList) {
            if (t.getTaskId().taskId == targetIndex) {
                taskToDelete = t;
                taskToDelete.removeAssignments();
                model.deleteTask(taskToDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, Messages.format(taskToDelete)));
            }
        }

        throw new CommandException(Messages.MESSAGE_INVALID_TASKID);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
