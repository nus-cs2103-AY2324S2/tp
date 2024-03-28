package seedu.address.model.coursemate.exceptions;

/**
 * Signals that the operation is unable to find the specified courseMate.
 */
public class CourseMateNotFoundException extends RuntimeException {

    /**
     * Basic empty constructor.
     */
    public CourseMateNotFoundException() {
    }

    /**
     * Constructor that builds an exception using a {@code label}.
     * This is the string used to find the CourseMate.
     */
    public CourseMateNotFoundException(String label) {
        super("Could not find a CourseMate that matches: " + label);
    }
}
