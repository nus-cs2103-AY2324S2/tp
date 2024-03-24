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
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "delete task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " TASK_NAME /in PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "%1$s has been deleted from %2$s";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";
    public static final String MESSAGE_TASK_NOT_FOUND = "Task %1$s not found: "
            + "Please make sure the task exists in project %2$s";

    private final Task toDelete;
    private final Person taskProject;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public DeleteTaskCommand(Task task, Person taskProject) {
        requireNonNull(task);
        requireNonNull(taskProject);
        this.toDelete = task;
        this.taskProject = taskProject;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasPerson(taskProject)) {
            throw new CommandException(String.format(
                MESSAGE_PROJECT_NOT_FOUND,
                Messages.format(taskProject)));
        }
        Person combineTask = model.findPerson(taskProject.getName());
        if (!combineTask.hasTask(toDelete)) {
            throw new CommandException(String.format(
                MESSAGE_TASK_NOT_FOUND,
                Messages.format(toDelete),
                Messages.format(taskProject)));
        }
        combineTask.removeTask(toDelete);
        return new CommandResult(String.format(
            MESSAGE_SUCCESS,
            Messages.format(toDelete),
            Messages.format(taskProject)));
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

        DeleteTaskCommand otherDeleteTaskCommand = (DeleteTaskCommand) other;
        return toDelete.equals(otherDeleteTaskCommand.toDelete)
                && taskProject.equals(otherDeleteTaskCommand.taskProject);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDelete", toDelete)
                .toString();
    }
}
