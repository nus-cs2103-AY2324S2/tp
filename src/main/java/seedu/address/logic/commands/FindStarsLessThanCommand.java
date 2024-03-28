package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.StarsLessThanPredicate;

/**
 * Finds and lists all students in address book whose number of stars is less than the argument.
 * Comparator is strictly less than.
 */
public class FindStarsLessThanCommand extends Command {
    public static final String COMMAND_WORD = "findStarsLT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose number of stars is less than "
            + "the specified number and displays them as a list with index numbers.\n"
            + "Parameters: UPPER_BOUND\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VALID_UPPER_BOUND = "Please input a single positive upper bound, "
            + "as the number of stars are non-negative.";
    private final StarsLessThanPredicate predicate;

    public FindStarsLessThanCommand(StarsLessThanPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
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
