package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.StarsLessThanPredicate;

/**
 * Finds and lists all persons in address book whose number of stars is less than the argument.
 * Comparator is strictly less than.
 */
public class FindStarsLessThanCommand extends Command {
    public static final String COMMAND_WORD = "findStarsLessThan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose number of stars is less than "
            + "the specified number and displays them as a list with index numbers.\n"
            + "Parameters: UPPER_BOUND\n"
            + "Example: " + COMMAND_WORD + "1";

    private final StarsLessThanPredicate predicate;

    public FindStarsLessThanCommand(StarsLessThanPredicate predicate) {
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
        if (!(other instanceof FindStarsLessThanCommand)) {
            return false;
        }

        FindStarsLessThanCommand otherFindStarsLessThanCommand = (FindStarsLessThanCommand) other;
        return predicate.equals(otherFindStarsLessThanCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }

}

