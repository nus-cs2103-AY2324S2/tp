package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PhoneContainsDigitsPredicate;

/**
 * Finds and lists all persons in address book whose phone number contains any of the argument phone numbers.
 */
public class FindNumCommand extends Command {

    public static final String COMMAND_WORD = "findnum";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose phone number contain any of "
            + "the specified number and displays them as a list with index numbers.\n"
            + "Parameters: PHONE_NUMBER [PHONE_NUMBERS]...\n"
            + "Example: " + COMMAND_WORD + " 87438807";

    private final PhoneContainsDigitsPredicate predicate;

    public FindNumCommand(PhoneContainsDigitsPredicate predicate) {
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

        FindNumCommand otherFindNumCommand = (FindNumCommand) other;
        return predicate.equals(otherFindNumCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
