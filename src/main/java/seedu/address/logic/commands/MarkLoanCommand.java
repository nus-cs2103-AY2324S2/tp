package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.DeleteLoanCommand.MESSAGE_FAILURE_PERSON;
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
 * Marks a loan as paid.
 */
public class MarkLoanCommand extends Command {
    public static final String COMMAND_WORD = "markloan";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the loan specified as paid.\n"
            + "Parameters: INDEX "
            + PREFIX_LOAN_INDEX + "LOAN_INDEX\n"
            + "Both INDEX and LOAN_INDEX must be positive integers.\n"
            + "Example: " + COMMAND_WORD + " 1 " + "l/2\n"
            + "This marks the loan of loan index 2 of the person at index 1 as paid.";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Remark command not implemented yet";
    public static final String MESSAGE_SUCCESS = "Loan marked.\n"
            + "Person Name: %1$s\n"
            + "Loan: %2$s";
    public static final String MESSAGE_FAILURE_PERSON = "No person found for Person number: %1$d";
    public static final String MESSAGE_FAILURE_LOAN = "No loan has been found "
            + "for loan number: %1$d for %2$s";
    private final Index personIndex;
    private final Index loanIndex;

    /**
     * Creates a MarkLoanCommand to delete the specified loan.
     * @param personIndex
     * @param loanIndex
     */
    public MarkLoanCommand(Index personIndex, Index loanIndex) {
        requireAllNonNull(personIndex, loanIndex);
        this.personIndex = personIndex;
        this.loanIndex = loanIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToEdit;
        LoanRecords loanRecords;
        try {
            personToEdit = lastShownList.get(personIndex.getZeroBased());
        } catch (IndexOutOfBoundsException i) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        try {
            loanRecords = personToEdit.getLoanRecords();
            loanRecords.markLoan(loanIndex.getZeroBased());
        } catch (IndexOutOfBoundsException i) {
            throw new CommandException(String.format(MESSAGE_FAILURE_LOAN, loanIndex.getOneBased(),
                    personToEdit.getName()));
        }
        return new CommandResult(generateSuccessMessage(personToEdit.getName(),
                loanRecords.getLoan(loanIndex.getZeroBased())));
    }

    /**
     * Generates a command execution success message after loan is deleted from the
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Name personName, Loan markedLoan) {
        return String.format(MESSAGE_SUCCESS, personName, markedLoan);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof MarkLoanCommand)) {
            return false;
        }
        MarkLoanCommand e = (MarkLoanCommand) other;
        return personIndex.equals(e.personIndex)
                && loanIndex.equals(e.loanIndex);
    }
}
