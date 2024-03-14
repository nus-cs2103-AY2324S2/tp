package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public static final String MESSAGE_CONFIRM = "Are you sure you want to clear the address book? Use\n"
            + "clear --force\n"
            + "to confirm clearing of the address book.";
    private final boolean isForced;

    public ClearCommand() {
        isForced = false;
    }

    public ClearCommand(boolean isForced) {
        this.isForced = isForced;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (!(isForced)) {
            return new CommandResult(MESSAGE_CONFIRM);
        }
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearCommand // instanceof handles nulls
                && isForced == ((ClearCommand) other).isForced); // state check
    }

}
