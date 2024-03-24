package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 * Finds and lists all persons in Tether whose phone number contains any of the argument keywords.
 */
public class FindPhoneCommand extends FindCommand {

    public static final String COMMAND_WORD = "find_phone";

    private final PhoneContainsKeywordsPredicate predicate;

    /**
     * Creates a FindPhoneCommand with given {@code Predicate} to filter for specific {@code Person}s.
     */
    public FindPhoneCommand(PhoneContainsKeywordsPredicate predicate) {
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
        if (!(other instanceof FindPhoneCommand)) {
            return false;
        }

        FindPhoneCommand otherFindPhoneCommand = (FindPhoneCommand) other;
        return predicate.equals(otherFindPhoneCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

