package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.DoctorContainsKeywordsPredicate;

/**
 * Queries and returns all doctors whose name matches the input string.
 * Keyword matching is case insensitive.
 * Query more than one name at a time is supported
 */
public class QueryDoctorCommand extends Command {

    public static final String COMMAND_WORD = "querydoctor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all doctors whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final DoctorContainsKeywordsPredicate predicate;

    public QueryDoctorCommand(DoctorContainsKeywordsPredicate predicate) {
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

        // instanceof handles nulls
        if (!(other instanceof QueryDoctorCommand)) {
            return false;
        }

        QueryDoctorCommand otherQueryDoctorCommand = (QueryDoctorCommand) other;
        return predicate.equals(otherQueryDoctorCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
