package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanRecords;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a loan from the address book.
 */
public class DeleteLoanCommand extends Command {
    public static final String COMMAND_WORD = "deleteloan";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the specified loan index of the person who is identified "
            + "by the index number. "
            + "Parameters: INDEX "
            + PREFIX_LOAN_INDEX + "LOAN_INDEX\n"
            + "Both INDEX and LOAN_INDEX must be positive integers.\n"
            + "Example: " + COMMAND_WORD + " 1 " + "l/1";
    public static final String MESSAGE_ARGUMENTS = "Person number: %1$d, Loan Index: %2$d";
    public static final String MESSAGE_SUCCESS = "Loan deleted.\n"
            + "Person Name: %1$s\n"
            + "Loan: %2$s";
    public static final String MESSAGE_FAILURE_PERSON = "No person found for Person number: %1$d";
    public static final String MESSAGE_FAILURE_LOAN = "No loan has been found "
            + "for loan number: %1$d for %2$s";
    private final Index personIndex;
    private final Index loanIndex;

    /**
     * Creates a DeleteLoanCommand to delete the specified loan.
     * @param personIndex
     * @param loanIndex
     */
    public DeleteLoanCommand(Index personIndex, Index loanIndex) {
        requireAllNonNull(personIndex, loanIndex);
        this.personIndex = personIndex;
        this.loanIndex = loanIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        if (loanIndex.getZeroBased() >= personToEdit.getLoanRecords().size()) {
            // in reality, it's loan index outside of list range. We will be concerned about it later.
            throw new CommandException(String.format(MESSAGE_FAILURE_LOAN, loanIndex.getOneBased(),
                    personToEdit.getName()));
        }
        LoanRecords loanRecords = personToEdit.getLoanRecords();
        // delete specified loan number
        Loan loanToRemove = loanRecords.getLoan(loanIndex.getZeroBased());
        loanRecords.removeLoan(loanToRemove);
        return new CommandResult(generateSuccessMessage(personToEdit.getName(), loanToRemove));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Name personName, Loan removedLoan) {
        return String.format(MESSAGE_SUCCESS, personName, removedLoan);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteLoanCommand)) {
            return false;
        }

        DeleteLoanCommand e = (DeleteLoanCommand) other;
        return personIndex.equals(e.personIndex)
                && loanIndex.equals(e.loanIndex);
    }
}
