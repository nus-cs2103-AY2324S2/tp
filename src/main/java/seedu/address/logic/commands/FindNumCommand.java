package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindNumCommand extends Command {

    public static final String COMMAND_WORD = "findnum";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose phone number contain any of "
            + "the specified number and displays them as a list with index numbers.\n"
            + "Parameters: 8_DIGIT_PHONE_NUMBER [MORE_8_DIGIT_PHONE_NUMBERS]...\n"
            + "Example: " + COMMAND_WORD + " 87438807";

    private final NameContainsKeywordsPredicate predicate;

    public FindNumCommand(NameContainsKeywordsPredicate predicate) {
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
        if (!(other instanceof FindNumCommand)) {
            return false;
        }

        FindNumCommand otherFindCommand = (FindNumCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
