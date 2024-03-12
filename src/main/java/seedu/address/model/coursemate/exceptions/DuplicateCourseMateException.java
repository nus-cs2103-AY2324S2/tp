package seedu.address.model.coursemate.exceptions;

/**
 * Signals that the operation will result in duplicate Course Mates
 * (Course Mates are considered duplicates if they have the same identity).
 */
public class DuplicateCourseMateException extends RuntimeException {
    public DuplicateCourseMateException() {
        super("Operation would result in duplicate course mates");
    }
}
