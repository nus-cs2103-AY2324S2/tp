package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.GradeSubjectFilterPredicate;

/**
 * Filters the address book based on the Grade or Subject.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters the address book based on the Grade or Subject.\n"
            + "Parameters: <GRADE|SUBJECT>\n"
            + "Example: " + COMMAND_WORD + " g/A s/Maths";

    public static final String MESSAGE_FILTER_ADDRESS_BOOK_SUCCESS = "Filtered address book by %2$s!\n";

    private final GradeSubjectFilterPredicate predicate;

    public FilterCommand(GradeSubjectFilterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(MESSAGE_FILTER_ADDRESS_BOOK_SUCCESS + Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size(), predicate));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
