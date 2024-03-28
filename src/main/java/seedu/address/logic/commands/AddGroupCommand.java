package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Adds a group to the address book.
 */
public class AddGroupCommand extends Command {

    public static final String COMMAND_WORD = "addgroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a group to the address book. "
            + "Parameters: "
            + PREFIX_GROUP + "GROUP NAME ";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the address book";

    private final Group toAdd;

    /**
     * Creates an AddGroupCommand
     */
    public AddGroupCommand(Group group) {
        requireNonNull(group);
        toAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addGroup(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddGroupCommand)) {
            return false;
        }

        AddGroupCommand otherAddGroupCommand = (AddGroupCommand) other;
        return toAdd.equals(otherAddGroupCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
