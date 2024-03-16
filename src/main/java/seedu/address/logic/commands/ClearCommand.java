package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_CONFIRMATION = "Are you sure you want to clear ALL contacts? [y/n]";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_CANCELLED = "Clear cancelled!";

    private static boolean awaitingConfirmation = false;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (!model.isAwaitingClear()) {
            model.setAwaitingClear(true);
            return new CommandResult(MESSAGE_CONFIRMATION);
        } else {
            model.setAwaitingClear(false); // Reset the confirmation state
            if (model.isConfirmClear()) {
                model.setConfirmClear(false);
                model.setAddressBook(new AddressBook());
                return new CommandResult(MESSAGE_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_CANCELLED);
            }
        }
    }

    public static boolean isAwaitingConfirmation() {
        return awaitingConfirmation;
    }

    public static void setAwaitingConfirmation(boolean awaitingConfirmation) {
        ClearCommand.awaitingConfirmation = awaitingConfirmation;
    }
}
