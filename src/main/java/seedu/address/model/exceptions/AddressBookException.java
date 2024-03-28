package seedu.address.model.exceptions;

/**
 * Represents an error which occurs during execution of an AddressBook method.
 */
public class AddressBookException extends RuntimeException {
    public AddressBookException(String message) {
        super(message);
    }

}
