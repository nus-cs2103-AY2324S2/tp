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
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " TASK_NAME /to PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "%1$s has been added to the project %2$s.";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_DUPLICATE_TASK = "Task %1$s already exists in project %2$s";

    private final Task toAdd;
    private final Person taskProject;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddTaskCommand(Task task, Person taskProject) {
        requireNonNull(task);
        requireNonNull(taskProject);
        this.toAdd = task;
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

        if (combineTask.hasTask(toAdd)) {
            throw new CommandException(String.format(
                    MESSAGE_DUPLICATE_TASK,
                    Messages.format(toAdd),
                    Messages.format(combineTask)));
        }

        combineTask.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd), Messages.format(combineTask)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        AddTaskCommand otherAddTaskCommand = (AddTaskCommand) other;
        return toAdd.equals(otherAddTaskCommand.toAdd)
                && taskProject.equals(otherAddTaskCommand.taskProject);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
