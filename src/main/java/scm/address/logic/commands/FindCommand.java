package scm.address.logic.commands;

import static java.util.Objects.requireNonNull;

import scm.address.commons.util.ToStringBuilder;
import scm.address.logic.Messages;
import scm.address.model.Model;
import scm.address.model.person.AddressContainsKeywordsPredicate;
import scm.address.model.person.NameContainsKeywordsPredicate;
import scm.address.model.person.TagsContainKeywordsPredicate;

/**
 * Finds and lists all persons in contact manager whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate namePredicate;
    private final AddressContainsKeywordsPredicate addressPredicate;
    private final TagsContainKeywordsPredicate tagsPredicate;


    public FindCommand(NameContainsKeywordsPredicate namePredicate,
                       AddressContainsKeywordsPredicate addressPredicate,
                       TagsContainKeywordsPredicate tagsPredicate) {
        this.namePredicate = namePredicate;
        this.addressPredicate = addressPredicate;
        this.tagsPredicate = tagsPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(namePredicate.and(addressPredicate.and(tagsPredicate)));

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
        return namePredicate.equals(otherFindCommand.namePredicate)
                && addressPredicate.equals(otherFindCommand.addressPredicate)
                && tagsPredicate.equals(otherFindCommand.tagsPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name predicate", namePredicate)
                .add("address predicate", addressPredicate)
                .add("tags predicate", tagsPredicate)
                .toString();
    }
}
