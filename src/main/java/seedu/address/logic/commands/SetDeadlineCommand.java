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
public class SetDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "add deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " DEADLINE /to TASK_NAME /in PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "The task %1$s has been set with the following deadline %2$s.";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_TASK_NOT_FOUND = "Task %1$s not found: "
            + "Please make sure the task exists in project %2$s";

    public static final String MESSAGE_WRONG_FORMAT_DEADLINE = "The deadline %1s has been entered in the wrong format. "
            + "An example of the correct format is Mar 15 2024";

    private final Task task;
    private final String deadline;
    private final Person project;

    private final String datePattern = "\\b(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\b \\d{1,2} \\d{4}\\b";

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public SetDeadlineCommand(String deadline, Task task, Person project) {
        requireNonNull(task);
        this.task = task;
        this.deadline = deadline;
        this.project = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!deadline.matches(datePattern)) {
            throw new CommandException(String.format(MESSAGE_WRONG_FORMAT_DEADLINE, deadline));
        }

        if (!model.hasPerson(project)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(project)));
        }

        Person deadlineProject = model.findPerson(project.getName());
        if (!deadlineProject.hasTask(task)) {
            throw new CommandException(String.format(
                    MESSAGE_TASK_NOT_FOUND,
                    Messages.format(task),
                    Messages.format(project)));
        }
        Task deadlineTask = deadlineProject.findTask(task.getName());

        deadlineTask.setDeadline(deadline);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(deadlineTask), deadline));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetDeadlineCommand)) {
            return false;
        }

        SetDeadlineCommand otherSetDeadlineCommand = (SetDeadlineCommand) other;
        return project.equals(otherSetDeadlineCommand.project)
                && task.equals(otherSetDeadlineCommand.task)
                && deadline.equals(otherSetDeadlineCommand.deadline);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("setDeadline", deadline)
                .toString();
    }
}
