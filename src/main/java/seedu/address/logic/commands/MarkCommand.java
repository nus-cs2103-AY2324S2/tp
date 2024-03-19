package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Marks a task as completed.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the TASK_ID.\n"
            + "Parameters: taskID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Mark task: %1$s";

    private final int targetIndex;

    /**
     * Constructs a MarkCommand to mark the task at the specified index.
     * @param targetIndex The index of the task to mark.
     */
    public MarkCommand(int targetIndex) {

        this.targetIndex = targetIndex;
    }

    /**
     * Executes the MarkCommand to mark a task as completed.
     * @param model The model in which the command should be executed.
     * @return The result of the command execution.
     * @throws CommandException If there is an error executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> taskList = model.getFilteredTaskList();

        Task taskToMark = null;

        for (Task t : taskList) {
            if (t.getTaskId().taskId == targetIndex) {
                model.deleteTask(t);
                taskToMark = t;
                taskToMark.markTask();
                model.addTask(taskToMark);
            }
        }

        if (taskToMark == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASKID);
        }

        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, Messages.format(taskToMark)));
    }

    /**
     * Returns a string representation of this MarkCommand.
     * @return A string representation of this MarkCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
