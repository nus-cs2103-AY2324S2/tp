package staffconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.logic.Messages;
import staffconnect.model.Model;
import staffconnect.model.person.Person;

/**
 * Sorts and lists all persons in staff book in ascending order.
 * Attribute shorthand matching is not case insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort all persons by the attribute specified "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: ATTRIBUTE\n"
            + "NAME: [n] "
            + "PHONE: [p] "
            // + "MODULE: [m] "
            // + "FACULTY: [f] "
            + "VENUE: [v] "
            + "Example: " + COMMAND_WORD + " n";

    private final Comparator<Person> comparator;

    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedPersonList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherFindCommand = (SortCommand) other;
        return comparator.equals(otherFindCommand.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comparator", comparator)
                .toString();
    }
}
