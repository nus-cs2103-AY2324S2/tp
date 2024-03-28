package staffconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Comparator;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.model.Model;
import staffconnect.model.person.Person;

/**
 * Sorts and lists all persons in staff book in ascending order.
 * Attribute shorthand matching is not case-insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SORT_LIST_SUCCESS = "Sorted list: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort all persons by the attribute specified "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [ATTRIBUTE]\n"
            + "[" + PREFIX_NAME + "] "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_MODULE + "] "
            + "[" + PREFIX_FACULTY + "] "
            + "[" + PREFIX_VENUE + "] "
            + "Example: " + COMMAND_WORD + " n";

    private final Comparator<Person> comparator;

    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        requireNonNull(comparator);
        model.updateSortedPersonList(comparator);
        return new CommandResult(
                String.format(MESSAGE_SORT_LIST_SUCCESS, comparator));
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

        SortCommand otherSortCommand = (SortCommand) other;
        return comparator.equals(otherSortCommand.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comparator", comparator)
                .toString();
    }
}
