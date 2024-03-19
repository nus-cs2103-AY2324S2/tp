package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDate(String)} (String)}
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS = "Attendance date should be in dd-MM-yyyy";
    public static final String VALIDATION_REGEX = "(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[1,2])-(19|20)\\d{2}";


    public final String attendanceName;

    /**
     * Constructs a {@code aDate}.
     *
     * @param attendanceName A valid date.
     */
    public Attendance(String attendanceName) {
        requireNonNull(attendanceName);
        checkArgument(isValidDate(attendanceName), MESSAGE_CONSTRAINTS);
        this.attendanceName = attendanceName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
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
        return attendanceName.equals(otherAttendance.attendanceName);
    }

    @Override
    public int hashCode() {
        return attendanceName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + attendanceName + ']';
    }

}
