package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will not result in successful addition
 * since there isn't currently a duplicate Person in the model (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePersonNotFoundException extends RuntimeException {
    public DuplicatePersonNotFoundException() {
        super("Operation would not result in duplicate persons; This person doesn't currently exist");
    }
}
