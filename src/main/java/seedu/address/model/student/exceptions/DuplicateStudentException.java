package seedu.address.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate Students (students are considered
 * duplicates if they have the same identity).
 */
public class DuplicateStudentException extends RuntimeException {
    public DuplicateStudentException() {
        super("Operation would result in duplicate student");
    }
}
