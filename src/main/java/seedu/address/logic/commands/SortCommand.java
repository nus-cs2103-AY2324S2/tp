package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

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

    public static final String MESSAGE_SUCCESS = "Client list sorted.";
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
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(PersonComparator.getComparator(sortCriteria, sortOrder));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
