package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in Persons with duplicate Ids.
 */
public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException() {
        super("Operation would result in persons with duplicate id");
    }
}
