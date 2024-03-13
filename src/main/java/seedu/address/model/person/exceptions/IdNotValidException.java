package seedu.address.model.person.exceptions;

/**
 * Signals that the input id is not valid.
 */
public class IdNotValidException extends RuntimeException {
    public IdNotValidException(String message) {
        super(message);
    }
}
