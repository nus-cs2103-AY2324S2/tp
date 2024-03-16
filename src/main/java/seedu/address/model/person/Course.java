package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's course code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCourse(String)}
 */
public class Course {
    
    public static final String MESSAGE_CONSTRAINTS = 
            "Course code should adhere to the following constraints:\n"
            + "1. It should start with 2 or 3 letters, followed by exactly 4 digits.\n"
            + "2. (Optional) You can also add a letter as a postfix.\n"
            + "Examples of accepted code: CS2103, CS2103T, DSA1101.";

    /*
     * A course code should start with 2-3 alphabets, followed by exactly 4 digits.
     * Some course codes may also have an alphabet as its last character. (i.e. CS2103T)
     * (Course code format is quoted from official NUS website)
     */
    private static final String PREFIX_CODE = "[A-Z]{2,3}";
    private static final String POSTFIX_CODE = "[0-9]{4}[A-Z]{0,1}";
    public static final String VALIDATION_REGEX = PREFIX_CODE + POSTFIX_CODE;

    public final String value;

    /**
     * Constructs a {@code Course}.
     *
     * @param course A valid course code.
     */
    public Course(String course) {
        requireNonNull(course);
        checkArgument(isValidCourse(course), MESSAGE_CONSTRAINTS);
        this.value = course.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid course code.
     */
    public static boolean isValidCourse(String test) {
        return test.toUpperCase().matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Course otherName = (Course) other;
        return this.value.equals(otherName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
