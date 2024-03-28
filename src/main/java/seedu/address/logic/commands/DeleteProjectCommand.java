package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a project identified using it's name from the project list.
 */
public class DeleteProjectCommand extends Command {

    public static final String COMMAND_WORD = "delete project";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " PROJECT_NAME\n";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "%1$s has been deleted from the project list.";

    private final String targetName;

    public DeleteProjectCommand(String targetName) {
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(new Person(new Name(targetName)))) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    targetName));
        }

        Person targetProject = model.findPerson(new Name(targetName));

        model.deletePerson(targetProject);
        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, Messages.format(targetProject)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteProjectCommand)) {
            return false;
        }

        DeleteProjectCommand otherDeleteCommand = (DeleteProjectCommand) other;
        return targetName.equals(otherDeleteCommand.targetName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .toString();
    }
}
