package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.HasUpcomingPredicate;
import seedu.address.model.person.Person;

/**
 * Represents a command that sorts all persons by upcoming dates.
 */
public class UpcomingCommand extends Command {

    public static final String COMMAND_WORD = "upcoming";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons by upcoming dates.\n";

    public static final String MESSAGE_SUCCESS = "Sorted all persons by upcoming dates,"
            + " starting with the earliest date.";

    private static final HasUpcomingPredicate HAS_UPCOMING_PREDICATE = new HasUpcomingPredicate();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(HAS_UPCOMING_PREDICATE);
        model.sortFilteredPersonList(new UpcomingComparator());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Comparator for sorting Person objects by upcoming dates
     */
    public class UpcomingComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getUpcoming().compareTo(p2.getUpcoming());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UpcomingCommand)) {
            return false;
        }

        UpcomingCommand other = (UpcomingCommand) obj;
        return super.equals(other);
    }
}
