package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.IsSameIdPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {
    /*
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": shows the summary stats"
            + "Example: " + COMMAND_WORD;*/

    public static final String MESSAGE_SUCCESS = "Viewing the stats of students";

    public static final String COMMAND_WORD = "view";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the specific student using their ID or name\n"
            + "Parameters: NAME or ID\n"
            + "Example: " + COMMAND_WORD + " John OR " + COMMAND_WORD + " 12345";

    private final NameContainsKeywordsPredicate namePredicate;
    private final IsSameIdPredicate idPredicate;

    /**
     * Constructor for a non search based view command (stats or all)
     */
    public ViewCommand() {
        this.namePredicate = null;
        this.idPredicate = null;
    }

    /**
     * Constructor for a view by name command.
     * @param predicate Name to search for in list.
     */
    public ViewCommand(NameContainsKeywordsPredicate predicate) {
        this.namePredicate = predicate;
        this.idPredicate = null;
    }

    /**
     * Constructor for a view by id command.
     * @param id Id to search for in list.
     */
    public ViewCommand(IsSameIdPredicate id) {
        this.namePredicate = null;
        this.idPredicate = id;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (namePredicate != null) {
            model.updateFilteredPersonList(namePredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
        } else if (idPredicate != null) {
            model.updateFilteredPersonList(idPredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
        } else {
            return new CommandResult(MESSAGE_SUCCESS, false, false, true);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return namePredicate.equals(otherViewCommand.namePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("namePredicate", namePredicate)
                .toString();
    }
}
