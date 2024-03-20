package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {
    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAssignTaskCommand(Task task, int index) {
        return AssignTaskCommand.COMMAND_WORD + " " + getTaskDetails(task, index);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASK + task.getTaskTitle() + " ");
        sb.append(PREFIX_DEADLINE + task.getDeadline().toString() + " ");
        sb.append(PREFIX_TO + String.valueOf(index));
        return sb.toString();
    }
}
