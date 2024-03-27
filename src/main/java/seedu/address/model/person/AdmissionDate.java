package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a patient's admission date in the address book.
 */
public class AdmissionDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Admission dates can take any date, and it should be in DD/MM/YYYY."
            + "Admission date should not be later than date of recording";
    public static final String VALIDATION_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$";

    public final String value;

    /**
     * Constructs a {@code AdmissionDate}.
     *
     * @param value A valid admission date.
     */
    public AdmissionDate(String value) {
        requireNonNull(value);
        checkArgument(isValidAdmissionDate(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid admission date.
     */
    public static boolean isValidAdmissionDate(String admissionDate) {
        if (admissionDate.matches(VALIDATION_REGEX)) {
            LocalDate today = LocalDate.now();
            // Define the date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Parse the string to LocalDate
            LocalDate date = LocalDate.parse(admissionDate, formatter);
            return date.isEqual(today) || date.isBefore(today);
        }
        return false;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AdmissionDate)) {
            return false;
        }

        AdmissionDate otherAdmissionDate = (AdmissionDate) other;
        return value.equals(otherAdmissionDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
