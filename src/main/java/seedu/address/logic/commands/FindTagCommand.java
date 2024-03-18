package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.TagsContainKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose tag contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "find-tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " friends family schoolmates ";

    private final List<String> tagKeywords;

    public FindTagCommand(List<String> tagKeywords) {
        this.tagKeywords = tagKeywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(tagKeywords);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindTagCommand)) {
            return false;
        }

        FindTagCommand otherFindTagCommand = (FindTagCommand) other;
        return tagKeywords.equals(otherFindTagCommand.tagKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tagKeywords", tagKeywords)
                .toString();
    }
}
