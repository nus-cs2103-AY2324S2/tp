package seedu.address.model.person;

import java.time.LocalDate;

/**
 * Represents a Person's date of admission in the address book.
 * Guarantees: immutable;
 */
public class DateOfBirth {
    public static final String MESSAGE_CONSTRAINTS =
            "Date of birth should be in the format of DD/MM/YYYY, and it should not be blank.";

    public static final String VALIDATION_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

    public final LocalDate dateOfBirth;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth.
     */
    public DateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.dateOfBirth.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateOfBirth)) {
            return false;
        }

        DateOfBirth otherDateOfBirth = (DateOfBirth) other;
        return dateOfBirth.equals(otherDateOfBirth.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return dateOfBirth.hashCode();
    }
}
