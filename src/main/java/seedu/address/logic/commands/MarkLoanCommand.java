package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;



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
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
