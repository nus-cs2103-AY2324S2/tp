package seedu.address.model.exceptions;

/**
 * Signals that there are no more states to undo to.
 */
public class AddressBookUndoException extends RuntimeException {
    public AddressBookUndoException() {
        super("There are no previous AddressBook states to return to.");
    }
}
