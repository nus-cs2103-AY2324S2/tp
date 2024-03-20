package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupNotFoundException;

/**
 * Deletes a preexisting group.
 */
public class DeleteGroupCommand extends Command {
    public static final String COMMAND_WORD = "delete-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a preexisting group. "
            + "groups can be specified by name.\n"
            + "Parameters: NAME (cannot be empty and must already exist)\n"
            + "Example: " + COMMAND_WORD + " CS2103T GROUP ";

    private final Name toDelete;

    /**
     * Basic constructor for {@code DeleteGroupCommand}. Deletes a specified group.
     * @param toDelete group to be deleted
     */
    public DeleteGroupCommand(Name toDelete) {
        requireNonNull(toDelete);
        this.toDelete = toDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Group groupToDelete = model.findGroup(toDelete);
            model.deleteGroup(groupToDelete);

            return new CommandResult(String.format("Deleted group %s",
                    toDelete));
        } catch (GroupNotFoundException exception) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_NAME, exception);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteGroupCommand)) {
            return false;
        }

        DeleteGroupCommand otherDelete = (DeleteGroupCommand) other;
        return otherDelete.toDelete.equals(toDelete);
    }
}
