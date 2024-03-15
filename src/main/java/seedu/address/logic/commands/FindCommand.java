package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.EmailMatchesPredicate;
import seedu.address.model.person.GroupMatchesPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneMatchesPredicate;
import seedu.address.model.person.TagMatchesPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    // This string is used by Predicates to help them know when a parameter isn't required.
    public static final String NOT_REQUIRED_VALUE = "$$NOT_REQUIRED$$";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate namePredicate;
    private final EmailMatchesPredicate emailPredicate;
    private final GroupMatchesPredicate groupPredicate;
    private final PhoneMatchesPredicate phonePredicate;
    private final TagMatchesPredicate tagMatchesPredicate;

    public FindCommand(NameContainsKeywordsPredicate predicate, EmailMatchesPredicate e, GroupMatchesPredicate g, PhoneMatchesPredicate p, TagMatchesPredicate t) {
        namePredicate = predicate;
        emailPredicate = e;
        groupPredicate = g;
        phonePredicate = p;
        tagMatchesPredicate = t;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(namePredicate
                .and(emailPredicate)
                .and(groupPredicate)
                .and(phonePredicate)
                .and(tagMatchesPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return namePredicate.equals(otherFindCommand.namePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", namePredicate)
                .toString();
    }
}
