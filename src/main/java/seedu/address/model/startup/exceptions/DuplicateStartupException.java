package seedu.address.model.startup.exceptions;

/**
 * Signals that the operation will result in duplicate Startups (Startups are considered duplicates if they have the
 * same identity).
 */
public class DuplicateStartupException extends RuntimeException {
    public DuplicateStartupException() {
        super("Operation would result in duplicate startups");
    }
}
