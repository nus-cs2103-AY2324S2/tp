package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

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

    public static final String COMMAND_WORD = "set deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the deadline to a task in a project "
            + "Parameters: "
            + "TASK_NAME, DEADLINE";

    public static final String MESSAGE_SUCCESS = "The task %1$s has been set with the following deadline %2$s.";

    public static final String MESSAGE_WRONG_FORMAT_DEADLINE = "The deadline %1s has been entered in the wrong format.";

    private final Task task;
    private final String deadline;
    private final Person project;

    private final String datePattern = "\\d{4}-\\d{2}-\\d{2}";

    private LocalDate deadlineDate;

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

        Person deadlineProject = model.findPerson(project.getName());
        Task deadlineTask = deadlineProject.findTask(task.getName());

        this.deadlineDate = LocalDate.parse(deadline);
        deadlineTask.setDeadline(deadline);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(deadlineTask), deadline));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("setDeadline", deadline)
                .toString();
    }
}
