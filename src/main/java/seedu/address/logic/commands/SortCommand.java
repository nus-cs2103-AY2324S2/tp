package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonComparator;
import seedu.address.model.person.SortCriteria;
import seedu.address.model.person.SortOrder;

/**
 * Sorts the client list in the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": sorts the client list in the address book. "
            + "Parameters: "
            + "CRITERIA "
            + PREFIX_SORT_ORDER + "ORDER\n"
            + "Example: " + COMMAND_WORD + " "
            + "priority "
            + PREFIX_SORT_ORDER + "desc";

    private static final String MESSAGE_SUCCESS = "Client list sorted by %s in %s order.";
    private final SortCriteria sortCriteria;
    private final SortOrder sortOrder;

    /**
     * Creates a SortCommand to sort the client list.
     */
    public SortCommand(SortCriteria sortCriteria, SortOrder sortOrder) {
        requireNonNull(sortCriteria);
        requireNonNull(sortOrder);

        this.sortCriteria = sortCriteria;
        this.sortOrder = sortOrder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortFilteredPersonList(PersonComparator.getComparator(sortCriteria, sortOrder));
        return new CommandResult(getMessageSuccess(sortCriteria, sortOrder));
    }

    /**
     * Returns a success message based on the sort criteria and sort order.
     *
     * @param sortCriteria the sort criteria
     * @param sortOrder the sort order
     * @return the success message
     */
    public static String getMessageSuccess(SortCriteria sortCriteria, SortOrder sortOrder)
            throws IllegalArgumentException {
        if (sortCriteria == null || sortOrder == null) {
            throw new IllegalArgumentException("SortCriteria and SortOrder cannot be null.");
        }
        if (sortCriteria == SortCriteria.INVALID || sortOrder == SortOrder.INVALID) {
            return String.format(MESSAGE_SUCCESS, SortCriteria.NAME, SortOrder.ASC);
        }
        return String.format(MESSAGE_SUCCESS, sortCriteria, sortOrder);
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
        return sortCriteria.equals(otherSortCommand.sortCriteria)
                && sortOrder.equals(otherSortCommand.sortOrder);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sortCriteria", sortCriteria)
                .add("sortOrder", sortOrder)
                .toString();
    }
}
