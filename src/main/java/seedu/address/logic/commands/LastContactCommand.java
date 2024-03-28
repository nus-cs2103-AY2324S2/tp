package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.HasLastContactedPredicate;
import seedu.address.model.person.Person;

/**
 * Tags a person with last contacted date and time in the address book.
 */
public class LastContactCommand extends Command {

    public static final Comparator<Person> SORT_COMPARATOR = (person1, person2) -> {
        // Assuming getLastContact() can be null and getDateTime() can also be null.
        LocalDateTime lastContactDateTime1 = (person1.getLastcontact() != null)
                ? person1.getLastcontact().getDateTime() : null;
        LocalDateTime lastContactDateTime2 = (person2.getLastcontact() != null)
                ? person2.getLastcontact().getDateTime() : null;

        // Handling nulls to ensure they are sorted to the end.
        if (lastContactDateTime1 == null && lastContactDateTime2 == null) {
            return 0; // Both are equal in terms of sorting.
        } else if (lastContactDateTime1 == null) {
            return 1; // Nulls are considered greater to sort them to the end.
        } else if (lastContactDateTime2 == null) {
            return -1; // Non-nulls come before nulls.
        }

        // If both dates are non-null, compare them directly.
        return lastContactDateTime1.compareTo(lastContactDateTime2);
    };

    public static final String COMMAND_WORD = "lastcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show a list of last contacted clients "
            + "sorted according to dateTime";

    public static final String MESSAGE_SUCCESS = "Listed all clients with last contact dates,"
            + " starting with the oldest date";

    private static final HasLastContactedPredicate HAS_LAST_CONTACTED_PREDICATE = new HasLastContactedPredicate();


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(HAS_LAST_CONTACTED_PREDICATE);
        model.sortFilteredPersonList(SORT_COMPARATOR);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LastContactCommand)) {
            return false;
        }

        LastContactCommand otherLastContactCommand = (LastContactCommand) other;
        return HAS_LAST_CONTACTED_PREDICATE.equals(otherLastContactCommand.HAS_LAST_CONTACTED_PREDICATE);
    }
}
