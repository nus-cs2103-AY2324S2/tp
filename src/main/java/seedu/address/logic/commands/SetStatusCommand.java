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

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [complete/incomplete] /to TASK_NAME /in PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "The task %1$s has the following status %2$s.";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_TASK_NOT_FOUND = "Task %1$s not found: "
            + "Please make sure the task exists in project %2$s";

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

        if (!model.hasPerson(project)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(project)));
        }

        Person statusProject = model.findPerson(project.getName());

        if (!statusProject.hasTask(task)) {
            throw new CommandException(String.format(
                    MESSAGE_TASK_NOT_FOUND,
                    Messages.format(task),
                    Messages.format(project)));
        }

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
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetStatusCommand)) {
            return false;
        }

        SetStatusCommand otherSetStatusCommand = (SetStatusCommand) other;
        return project.equals(otherSetStatusCommand.project)
                && task.equals(otherSetStatusCommand.task)
                && status.equals(otherSetStatusCommand.status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("set status", status)
                .toString();
    }
}
