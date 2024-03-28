package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's attendance status in the address book.
 *
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only be 'Present' or 'Absent', and it should not be blank";
    public static final String VALIDATION_REGEX = "(?i)(present|absent)";
    public final String value;

    /**
     * Creates an undefined attendance status for the student.
     */
    public Attendance() {
        value = "-";
    }

    /**
     * Creates an attendance status for a student.
     *
     * @param status Attendance status ('Present' or 'Absent').
     */
    public Attendance(String status) {
        requireNonNull(status);
        checkArgument(isValidAttendance(status), MESSAGE_CONSTRAINTS);
        value = status;
    }

    /**
     * Returns true if a given string is a valid attendance status.
     */
    public static boolean isValidAttendance(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        return value.equalsIgnoreCase(otherAttendance.value);
    }
}
