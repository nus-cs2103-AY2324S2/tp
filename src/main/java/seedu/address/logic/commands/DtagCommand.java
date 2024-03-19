package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a tag from the address book.
 */
public class DtagCommand extends Command {

    public static final String COMMAND_WORD = "dtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tag identified by the name used in the displayed tag list.\n"
            + "Parameters: TAGNAME\n"
            + "Example: " + COMMAND_WORD + " FRIEND";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted Tag: %1$s";

    private final String tagName;

    public DtagCommand(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(tagName)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG);
        }

        model.deleteTag(tagName);
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, tagName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DtagCommand)) {
            return false;
        }

        DtagCommand otherDeleteCommand = (DtagCommand) other;
        return tagName.equals(otherDeleteCommand.tagName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tagName", tagName)
                .toString();
    }
}
