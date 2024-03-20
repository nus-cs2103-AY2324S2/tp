package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.ui.UiManager;

/**
 * Deletes all entries in the address book.
 */
public class DeleteAllCommand extends Command {

    public static final String COMMAND_WORD = "delete-all";
    public static final String CONFIRMATION =
            "Are you sure you want to delete all? This action is irreversible. Enter Y/N:";
    public static final String INVALID_INPUT_ALERT = "Invalid input format";
    public static final String MESSAGE_SUCCESS = "Successfully deleted all data";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
