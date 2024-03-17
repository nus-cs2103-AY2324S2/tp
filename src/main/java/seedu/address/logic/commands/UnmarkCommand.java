package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Unmarks a task, marking it as incomplete.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the task identified by the TASK_ID.\n"
            + "Parameters: taskID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Unmark task: %1$s";

    private final int targetIndex;

    /**
     * Constructs an UnmarkCommand to unmark the task at the specified index.
     * @param targetIndex The index of the task to unmark.
     */
    public UnmarkCommand(int targetIndex) {

        this.targetIndex = targetIndex;
    }

    /**
     * Executes the UnmarkCommand to unmark a task.
     * @param model The model in which the command should be executed.
     * @return The result of the command execution.
     * @throws CommandException If there is an error executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> taskList = model.getFilteredTaskList();

        Task taskToUnmark = null;

        for (Task t : taskList) {
            if (t.getTaskId().taskId == targetIndex) {
                model.deleteTask(t);
                taskToUnmark = t;
                taskToUnmark.unmarkTask();
                model.addTask(taskToUnmark);
            }
        }

        if (taskToUnmark == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASKID);
        }

        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, Messages.format(taskToUnmark)));
    }

    /**
     * Returns a string representation of this UnmarkCommand.
     * @return A string representation of this UnmarkCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
