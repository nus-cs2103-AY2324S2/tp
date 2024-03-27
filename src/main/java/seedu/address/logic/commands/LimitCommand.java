package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.library.Threshold;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Sets the limit threshold for Merit Score to not allow borrowers from borrowing books from the library.
 */
public class LimitCommand extends Command {
    public static final String COMMAND_WORD = "limit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the limit for the Merit Score such that any "
            + "borrower with less than or equal to the limit cannot borrow any books from the library.\n"
            + "Parameters: INTEGER (cannot include spaces or + signs)\n"
            + "Example: " + COMMAND_WORD + " -3";

    public static final String MESSAGE_LIMIT_THRESHOLD_SUCCESS = "Limit set to: %d";
    //TODO finish execute to edit library threshold
    //TODO Use LimitCommandParser somewhere?
    //TODO Make error for duplicate threshold
    public final Threshold threshold;

    /**
     * @param threshold Limit for the Merit Score during borrowing.
     */
    public LimitCommand(Threshold threshold) {
        requireNonNull(threshold);

        this.threshold = threshold;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
        // return new CommandResult(String.format(MESSAGE_LIMIT_THRESHOLD_SUCCESS, threshold));
    }
}
