package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Format search instructions for every command for display.
 */
public class SearchTagCommand extends Command {

    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_SUCCESS = "found all person with the tag";

    public static final String MESSAGE_NO_TAG = "no person with this tag is found";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Search people who are associated with this tag "
            + "by listing the person that it is associated with.\n"
            + "Parameters: TAG (must be an existing tag) "
            + "[TAG]\n"
            + "Example: " + COMMAND_WORD + " school";

    private final Tag tag;

    /**
     * The constructor for SearchCommand
     * @param tag the tag that you want to search the list for
     */
    public SearchTagCommand(Tag tag) {
        requireAllNonNull(tag);
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasTag(tag)) {
            throw new CommandException(MESSAGE_NO_TAG);
        }
        model.updateTagPersonList(tag);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SearchTagCommand)) {
            return false;
        }

        SearchTagCommand e = (SearchTagCommand) other;
        return tag.equals(e.tag);
    }
}
