package seedu.internhub.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.internhub.commons.util.ToStringBuilder;
import seedu.internhub.logic.Messages;
import seedu.internhub.model.Model;
import seedu.internhub.model.person.MatchingTagPredicate;

/**
 * Filters the list of companies to only show entries with the specified tag
 * Tag searching is case-sensitive
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the current list of companies to only display "
            + "those with the specified tag (case-sensitive) as a list with index numbers. \n"
            + "Parameter: t/ TAG \n"
            + "Example: " + COMMAND_WORD + " t/ I";

    private final MatchingTagPredicate predicate;

    public FilterCommand(MatchingTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(
                        Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size()));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
