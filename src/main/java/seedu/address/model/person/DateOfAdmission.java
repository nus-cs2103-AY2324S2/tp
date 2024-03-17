package seedu.address.model.person;

import java.time.LocalDate;

public class DateOfAdmission {
    public static final String MESSAGE_CONSTRAINTS =
            "Date of admission should be in the format of DD/MM/YYYY, and it should not be blank.";

    public static final String VALIDATION_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

    public final LocalDate dateOfAdmission;

    /**
     * Constructs a {@code DateOfAdmission}.
     *
     * @param dateOfAdmission A valid date of admission.
     */
    public DateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission = LocalDate.parse(dateOfAdmission);
    }

    /**
     * Returns true if a given string is a valid date of admission.
     */
    public static boolean isValidDateOfAdmission(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.dateOfAdmission.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateOfAdmission)) {
            return false;
        }

        DateOfAdmission otherDateOfAdmission = (DateOfAdmission) other;
        return dateOfAdmission.equals(otherDateOfAdmission.dateOfAdmission);
    }

    @Override
    public int hashCode() {
        return dateOfAdmission.hashCode();
    }
}
