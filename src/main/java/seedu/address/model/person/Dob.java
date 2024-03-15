package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

/**
 * Represents a patient's date of birth in the address book.
 */
public class Dob {
    public static final String MESSAGE_CONSTRAINTS = "Dates of birth takes in a date";
    public static final String VALIDATION_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$";

    public final String dob;

    /**
     * Constructs a {@code Dob}.
     *
     * @param dob A valid date of birth.
     */
    public Dob(String dob) {
        requireNonNull(dob);
        checkArgument(isValidDob(dob), MESSAGE_CONSTRAINTS);
        this.dob = dob;
    }

    public static boolean isValidDob(String dob) {
        return dob.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return dob;
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
        return dob.equals(otherDob.dob);
    }

    @Override
    public int hashCode() {
        return dob.hashCode();
    }

    // Todo: isValidDob

}
