package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in Tether whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindNameCommand extends FindCommand {

    public static final String COMMAND_WORD = "find_name";

    private final NameContainsKeywordsPredicate predicate;

    /**
     * Creates a FindNameCommand with given {@code Predicate} to filter for specific {@code Person}s.
     */
    public FindNameCommand(NameContainsKeywordsPredicate predicate) {
        super();
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
        if (!(other instanceof FindNameCommand)) {
            return false;
        }

        FindNameCommand otherFindNameCommand = (FindNameCommand) other;
        return predicate.equals(otherFindNameCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

