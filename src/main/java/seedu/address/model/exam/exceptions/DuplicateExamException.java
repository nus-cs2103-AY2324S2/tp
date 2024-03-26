package seedu.address.model.exam.exceptions;

/**
 * Signals that the operation will result in duplicate Exams (Exams are considered duplicates if they have the same
 * identity).
 */
public class DuplicateExamException extends RuntimeException {
    public DuplicateExamException() {
        super("Operation would result in duplicate Exams");
    }
}
