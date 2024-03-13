package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.tag.TagsOrFoundPredicate;

/**
 * Finds and lists all persons in address book who contains any of tags in the argument.
 * Tag matching is case sensitive.
 */
public class FindTagsOrCommand extends Command {

    public static final String COMMAND_WORD = "findTagsOr";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who contains any of the tags "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " car health death";

    private final TagsOrFoundPredicate predicate;

    public FindTagsOrCommand(TagsOrFoundPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindTagsOrCommand)) {
            return false;
        }

        FindTagsOrCommand otherFindTagOrCommand = (FindTagsOrCommand) other;
        return predicate.equals(otherFindTagOrCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }

}
