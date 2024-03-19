package seedu.address.model.exceptions;

/**
 * Represents an error which occurs during execution of AddressBook commands.
 */
public class AddressBookException extends Exception {
    public AddressBookException(String message) {
        super(message);
    }
}
