package seedu.address.logic.commands;

import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose number of stars is less than the argument.
 * Comparator is strictly less than.
 */
public class FindStarsLessThanCommand {
    public static final String COMMAND_WORD = "findStarsLessThan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose number of stars is less than "
            + "the specified number and displays them as a list with index numbers.\n"
            + "Parameters: UPPER_BOUND\n"
            + "Example: " + COMMAND_WORD + "1";

    private final StarsLessThanPredicate predicate;

    public FindStarsLessThanCommand(StarsLessThanPredicate predicate) {
        this.predicate = predicate;
    }

}
