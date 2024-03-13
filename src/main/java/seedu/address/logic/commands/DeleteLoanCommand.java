package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.LoanRecords;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a loan from the address book.
 */
public class DeleteLoanCommand extends Command {
    public static final String COMMAND_WORD = "deleteloan";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the specified loan number of the person identified"
            + "by the index number used. "
            + "Parameters: NAME, INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " n/John Doe "
            + "i/1";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Delete loan command not implemented yet";
    public static final String MESSAGE_ARGUMENTS = "Name: %1$s, Index: %2$d";
    public static final String MESSAGE_SUCCESS = "Loan deleted: Name:%1$s, Index: %2$d";
    public static final String MESSAGE_FAILURE = "No loan found for Name:%1$s, Index: %2$d";
    private final Name name;
    private final Index index;

    /**
     * Creates a DeleteLoanCommand to delete the specified loan.
     * @param name
     * @param index
     */
    public DeleteLoanCommand(Name name, Index index) {
        requireAllNonNull(name, index);
        this.name = name;
        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        // find the first instance of a person with the given name
        Person personToEdit = lastShownList.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (personToEdit == null) {
            throw new CommandException(String.format(MESSAGE_FAILURE, name, index.getOneBased()));
        }
        if (index.getZeroBased() >= personToEdit.getLoanRecords().size()) {
            // in reality, it's loan index outside of list range. We will be concerned about it later.
            throw new CommandException(Messages.MESSAGE_INVALID_LOAN_INDEX);
        }
        LoanRecords loanRecords = personToEdit.getLoanRecords();
        // delete specified loan number
        loanRecords.removeLoan(loanRecords.getLoan(index.getZeroBased()));
        return new CommandResult(generateSuccessMessage(index.getOneBased()));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(int index) {
        return String.format(MESSAGE_SUCCESS, name, "Loan number ", index, " successfully removed!");
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
        return this.index.equals(e.index)
                && this.name.equals(e.name);
    }
}
