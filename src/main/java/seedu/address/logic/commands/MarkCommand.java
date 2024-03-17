package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

import java.util.List;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the TASK_ID.\n"
            + "Parameters: taskID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Mark task: %1$s";

    private final int targetIndex;

    public MarkCommand(int targetIndex) {

        this.targetIndex = targetIndex;
    }

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


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}


