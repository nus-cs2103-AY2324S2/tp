package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * Adds a person to the address book.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task in a project "
            + "Parameters: "
            + "PROJECT_NAME, TASK_NAME";

    public static final String MESSAGE_SUCCESS = "%1$s has been added to the project %1$s.";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: Please make sure the project exists.";
    public static final String MESSAGE_DUPLICATE_TASK = "Task %1$s already exists in project %1$s";

    private final Person toAdd;
    private final Project taskProject;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddTaskCommand(Person person, Project taskProject) {
        requireNonNull(person);
        requireNonNull(person);
        toAdd = person;
        this.taskProject = taskProject;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TASK, Messages.format(toAdd)));
        }

        /*
            if (!model.hasProject(taskProject)) {
                throw new CommandException(String.format(MESSAGE_PROJECT_NOT_FOUND, Messages.format(taskProject)));
            }
        */


        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
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
        return toAdd.equals(otherAddTaskCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
