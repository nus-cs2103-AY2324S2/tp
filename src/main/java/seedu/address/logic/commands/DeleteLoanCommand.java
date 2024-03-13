package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;

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
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, "OOPS!", index.getOneBased()));
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
