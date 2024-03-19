package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a patient's date of birth in the address book.
 */
public class Dob {
    public static final String MESSAGE_CONSTRAINTS = "Dates of birth takes in a date"
            + "Date of birth should not be later than date of recording";
    public static final String VALIDATION_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$";

    public final String value;

    /**
     * Constructs a {@code Dob}.
     *
     * @param value A valid date of birth.
     */
    public Dob(String value) {
        requireNonNull(value);
        checkArgument(isValidDob(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDob(String dob) {
        if (dob.matches(VALIDATION_REGEX)) {
            LocalDate today = LocalDate.now();
            // Define the date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Parse the string to LocalDate
            LocalDate date = LocalDate.parse(dob, formatter);
            return date.isEqual(today) || date.isBefore(today);
        }
        return false;
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

        if (!(other instanceof Dob)) {
            return false;
        }

        Dob otherDob = (Dob) other;
        return value.equals(otherDob.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    // Todo: isValidDob

}
