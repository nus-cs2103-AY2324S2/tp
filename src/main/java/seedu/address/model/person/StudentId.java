package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "StudentId should start with A follow by 7 digits and ends with a letter, and it should not be blank.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}]+";

    public final String ID;

    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        ID = studentId;
    }

    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return ID;
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
        return ID.equals(otherName.ID);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}
