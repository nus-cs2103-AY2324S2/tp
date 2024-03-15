package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "FitBook has been cleared!";
    public static final String MESSAGE_CONFIRM = "Are you sure you want to clear ALL clients from FitBook? Enter "
            + "'clear /confirm' to confirm.";
    private final boolean confirmed;

    public ClearCommand(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (!confirmed) {
            return new CommandResult(MESSAGE_CONFIRM);
        } else {
            model.setAddressBook(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
