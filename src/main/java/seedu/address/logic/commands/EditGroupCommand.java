package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Adds a group to the address book.
 */
public class EditGroupCommand extends Command {

    public static final String COMMAND_WORD = "editgroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit the Telegram group link "
            + "Parameters: "
            + PREFIX_GROUP + "GROUP NAME "
            + PREFIX_TELEGRAM + "TELEGRAM INVITE LINK ";

    public static final String MESSAGE_SUCCESS = "Group edited: %1$s";
    public static final String MESSAGE_NOT_FOUND = "Group is not found";

    private final Group toEdit;
    private final String link;

    /**
     * Creates an AddGroupCommand
     */
    public EditGroupCommand(Group group, String link) {
        requireNonNull(group);
        this.toEdit = group;
        this.link = link;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasGroup(toEdit)) {
            throw new CommandException(MESSAGE_NOT_FOUND);
        }

        Group editedGroup = new Group(toEdit.groupName, this.link);

        model.setGroup(toEdit, editedGroup);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditGroupCommand)) {
            return false;
        }

        EditGroupCommand otherAddGroupCommand = (EditGroupCommand) other;
        return toEdit.equals(otherAddGroupCommand.toEdit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toEdit)
                .toString();
    }
}
