package seedu.address.model.person;

import java.time.LocalDate;

/**
 * Represents a Person's date of admission in the address book.
 * Guarantees: immutable;
 */
public class DateOfAdmission {
    public static final String MESSAGE_CONSTRAINTS =
            "Date of admission should be in the format of YYYY-MM-DD, and it should not be blank.";

    public static final String VALIDATION_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

    private final LocalDate dateOfAdmission;

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

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return dateOfAdmission == null ? alt : dateOfAdmission.toString();
    }

    @Override
    public String toString() {
        return this.dateOfAdmission.toString();
    }

    @Override
    public int hashCode() {
        return dateOfAdmission.hashCode();
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
}
