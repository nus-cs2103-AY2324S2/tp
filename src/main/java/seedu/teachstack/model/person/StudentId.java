package seedu.teachstack.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.teachstack.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's studentId in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "StudentId should start with A follow by 7 digits and ends with a letter, and it should not be blank.";

    // The studentId must start with A followed by digits and end with a letter.
    public static final String VALIDATION_REGEX = "A\\d{7}[A-Z]";

    public final String id;

    /**
     * Constructs a {@code StudentId}.
     *
     * @param studentId A valid studentId.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        id = studentId;
    }

    /**
     * Returns true if a given string is a valid studentId.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentId)) {
            return false;
        }

        StudentId otherName = (StudentId) other;
        return id.equals(otherName.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
