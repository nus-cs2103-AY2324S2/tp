package educonnect.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate Students
 * (Students are considered duplicates if they share same identifiers).
 */
public class DuplicateStudentException extends RuntimeException {
    public DuplicateStudentException() {
        super("Operation would result in students with duplicate unique identifiers");
    }
}
