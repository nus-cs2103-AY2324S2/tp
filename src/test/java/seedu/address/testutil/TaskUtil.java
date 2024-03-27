package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddTaskCommand(Task task) {
        return AddTaskCommand.COMMAND_WORD + " "
                + PREFIX_NAME + task.getName() + " "
                + PREFIX_TASK_DESCRIPTION + task.getDescription();
    }

    /**
     * Returns an delete command string for adding the {@code person}.
     */
    public static String getDeleteTaskCommand(Index index) {
        return DeleteTaskCommand.COMMAND_WORD + " " + index.getOneBased();
    }
}
