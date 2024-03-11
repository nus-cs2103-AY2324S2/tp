package seedu.address.model.classes.exceptions;

/**
 * Signals that the operation will result in duplicate Classes
 * (Classes are considered duplicates if they have the same * identity).
 */
public class DuplicateClassException extends RuntimeException {
    public DuplicateClassException() {
        super("Operation would result in duplicate classes");
    }
}