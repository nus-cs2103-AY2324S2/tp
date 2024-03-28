package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's enrolled subjects in the address book.
 * Guarantees: immutable; is always valid
 */
public class Subject {
    public static final String MESSAGE_CONSTRAINTS =
            "Subject should only contain alphanumeric characters and spaces, and it should not be blank.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String value;

    public Subject() {
        value = "No subject";
    }

    /**
     * Creates a Subject List for a student.
     *
     * @param subject Initial subject enrollment.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject), MESSAGE_CONSTRAINTS);
        value = subject;
    }

    /**
     * Returns true if a given string is a valid subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isEmpty() {
        return this.equals(new Subject());
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
        if (!(other instanceof Subject)) {
            return false;
        }

        Subject otherSubject = (Subject) other;
        return value.equals(otherSubject.value);
    }
}
