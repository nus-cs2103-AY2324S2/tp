package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.commons.core.index.Index;
import seedu.address.model.tag.TagStatus;

/**
 * Changes the remark of an existing person in the address book.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    // to be updated
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            //+  PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 ";
            //+ PREFIX_REMARK + "Likes to swim.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Tag: %2$s";

    private final Index index;
    private final String tagName;
    private final TagStatus tagStatus;

    public MarkCommand(Index index, String tagName, TagStatus tagStatus) {
        requireAllNonNull(index, tagName, tagStatus);
        this.index = index;
        this.tagName = tagName;
        this.tagStatus = tagStatus;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), tagName));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return index.equals(e.index)
                && tagName.equals(e.tagName)
                && tagStatus.equals(tagStatus);
    }
}