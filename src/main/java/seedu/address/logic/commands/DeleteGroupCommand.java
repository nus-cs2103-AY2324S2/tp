package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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
            + "groups can be specified by name. "
            + "Parameters: NAME (cannot be empty and must already exist)\n"
            + "Example: " + COMMAND_WORD + " CS2103T GROUP ";

    private final Name toDelete;

    /**
     * Basic constructor for DeleteGroupCommand. Deletes a specified group.
     * @param toDelete group to be deleted
     */
    public DeleteGroupCommand(Name toDelete) {
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
            throw new CommandException("Specified group wasn't found", exception);
        }
    }
}
