package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_SORT_CLEARED;

import java.util.Comparator;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts the address book in some specified order.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts your contacts according"
            + "to the specified sorting method.\n"
            + "Available methods:\n"
            + "1. name\n"
            + "2. birthday\n"
            + "3. money\n"
            + "3. clear\n"
            + "Example: " + COMMAND_WORD + " birthday";
    public static final String BIRTHDAY_SORT_TYPE = "birthday";
    public static final String NAME_SORT_TYPE = "name";
    public static final String MONEY_SORT_TYPE = "money";
    public static final String CLEAR_SORT_TYPE = "clear";
    private final String sortType;
    private final Comparator<Person> personComparator;

    /**
     * Returns a new SortCommand object that takes in a {@code Comparator<Person>} to
     * sort the address book.
     */
    public SortCommand(Comparator<Person> personComparator, String sortType) {
        this.personComparator = personComparator;
        this.sortType = sortType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updatePersonComparator(personComparator);
        return new CommandResult(
                personComparator == null
                        ? MESSAGE_SORT_CLEARED
                        : String.format(
                        Messages.MESSAGE_SORTED_OVERVIEW, sortType
                )
        );
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSort = (SortCommand) other;

        if (!Objects.equals(sortType, otherSort.sortType)) {
            return false;
        }
        return Objects.equals(personComparator, otherSort.personComparator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortType, personComparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sortType", sortType)
                .toString();
    }
}
