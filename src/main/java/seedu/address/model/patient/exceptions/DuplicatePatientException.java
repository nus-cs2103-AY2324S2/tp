package seedu.address.model.patient.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePatientException extends RuntimeException {
    public DuplicatePatientException() {
        super("Operation would result in duplicate persons");
    }
}
