package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an AttendanceDate in the student.
 */
public class AttendanceDate {

    public static final String MESSAGE_CONSTRAINTS = "Attendance date should be in dd-MM-yyyy";
    public static final String VALIDATION_REGEX = "(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[1,2])-(19|20)\\d{2}";


    public final String aDate;

    /**
     * Constructs a {@code aDate}.
     *
     * @param aDate A valid date.
     */
    public AttendanceDate(String aDate) {
        requireNonNull(aDate);
        checkArgument(isValidDate(aDate), MESSAGE_CONSTRAINTS);
        this.aDate = aDate;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return aDate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendanceDate)) {
            return false;
        }

        AttendanceDate otherName = (AttendanceDate) other;
        return aDate.equals(otherName.aDate);
    }

    @Override
    public int hashCode() {
        return aDate.hashCode();
    }

}
