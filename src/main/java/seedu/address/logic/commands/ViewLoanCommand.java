package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.LoanRecords;
import seedu.address.model.person.Person;

/**
 * Represents a command to view the loans associated with a contact.
 */
public class ViewLoanCommand extends Command {
    public static final String COMMAND_WORD = "viewloan";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View loans associated with the person identified by the index used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Listed all loans associated with: %1$s";
    private final Index targetIndex;

    public ViewLoanCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShowLoan = lastShownList.get(targetIndex.getZeroBased());
        LoanRecords loanRecords = personToShowLoan.getLoanRecords();

        // TODO model.updateLoanList or something
        model.setLoanList(loanRecords.getLoanList());
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(personToShowLoan)), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewLoanCommand)) {
            return false;
        }

        ViewLoanCommand otherViewLoanCommand = (ViewLoanCommand) other;
        return targetIndex.equals(otherViewLoanCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
