package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents an attendance entry in the address book.
 * Guarantees: immutable;
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS = "Attendance entry should follow the format YYYY-MM-DD";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public final LocalDate attendanceDate;

    /**
     * Constructs a {@code Attendance}.
     *
     * @param attendanceDate A valid attendance date.
     */
    public Attendance(LocalDate attendanceDate) {
        requireNonNull(attendanceDate);
        this.attendanceDate = attendanceDate;
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
        return attendanceDate.equals(otherAttendance.attendanceDate);
    }

    /**
     * Returns true if a given string is a valid attendance date.
     */
    public static boolean isValidAttendanceDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return attendanceDate.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + attendanceDate.toString() + ']';
    }

}
