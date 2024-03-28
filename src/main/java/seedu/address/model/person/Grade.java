package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's grade in the address book.
 * Guarantees: immutable; is always valid
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should contain one from this grading system:\n"
                    + "[A+, A, A-, B+, B, B-, C+, C, D+, D, F, -], and it should not be blank.";
    public static final String VALIDATION_REGEX = "[A-B][+-]?|[C-D][+]?|F|-";
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
        checkArgument(isValidGrade(score), MESSAGE_CONSTRAINTS);
        value = score;
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidGrade(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isEmpty() {
        return this.equals(new Grade());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Grade)) {
            return false;
        }

        Grade otherGrade = (Grade) other;
        return value.equals(otherGrade.value);
    }
}
