package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;

/**
 * Adds a task to a project.
 */
public class SetStatusCommand extends Command {

    public static final String COMMAND_WORD = "set status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the status of a task in a project "
            + "Parameters: "
            + "TASK_NAME, STATUS, PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "The task %1$s has the following status %2$s.";

    public static final String MESSAGE_WRONG_FORMAT_STATUS = "The status %1s has been entered in the wrong format.";

    private final Task task;
    private final String status;
    private final Person project;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public SetStatusCommand(String status, Task task, Person project) {
        requireNonNull(task);
        requireNonNull(project);
        requireNonNull(status);
        this.task = task;
        this.status = status;
        this.project = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person statusProject = model.findPerson(project.getName());
        Task statusTask = statusProject.findTask(task.getName());

        if (status.equals("complete")) {
            statusTask.setComplete();
            return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(statusTask), status));
        } else if (status.equals("incomplete")) {
            statusTask.setIncomplete();
            return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(statusTask), status));
        } else {
            throw new CommandException(String.format(MESSAGE_WRONG_FORMAT_STATUS, status));
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("set status", status)
                .toString();
    }
}
