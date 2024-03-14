package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's grade in the address book.
 * Guarantees: immutable; is always valid
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should only contain a single letter from A to D, and it should not be blank";
    public static final String VALIDATION_REGEX = "[A-C][+-]?|D";
    public final String value;

    /**
     * Creates an undefined grade for the student.
     */
    public Grade() {
        value = "-";
    }

    /**
     * Creates a grade for a student.
     *
     * @param score Grade letter.
     */
    public Grade(String score) {
        requireNonNull(score);
        value = score;
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidGrade(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && value == ((Grade) other).value); // state check
    }
}
