package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Member;
import seedu.address.model.project.Task;

/**
 * Adds a task to a project.
 */
public class AssignPersonCommand extends Command {

    public static final String COMMAND_WORD = "add person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "PERSON_NAME"
            + "/to TASK_NAME"
            + "/in PROJECT_NAME";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_TASK_NOT_FOUND = "Task %1s not found: "
            + "Please make sure the task exists.";

    public static final String MESSAGE_SUCCESS = "The person %1$s has been assigned to the following task %2$s.";

    private final Task task;
    private final Person project;

    private final Member member;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AssignPersonCommand(String member, Task task, Person project) {
        requireNonNull(task);
        this.task = task;
        this.project = project;
        this.member = new Member(member);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person projectAssign = model.findPerson(project.getName());

        if (projectAssign.equals(null)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(project)));
        }

        Task assignTask = projectAssign.findTask(task.getName());

        if (assignTask.equals(null)) {
            throw new CommandException(String.format(
                    MESSAGE_TASK_NOT_FOUND,
                    Messages.format(task)
            ));
        }

        assignTask.assignPerson(this.member);

        return new CommandResult(String.format(MESSAGE_SUCCESS, member, Messages.format(task)));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("set Member", member)
                .toString();
    }
}
