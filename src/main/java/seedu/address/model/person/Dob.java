package seedu.address.model.person;

import java.time.LocalDate;

/**
 * Represents a patient's date of birth in the address book.
 */
public class Dob {
    public static final String MESSAGE_CONSTRAINTS = "Dates of birth takes in a date";

    public final LocalDate dob;

    /**
     * Constructs a {@code Dob}.
     *
     * @param dob A valid date of birth.
     */
    public Dob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return dob.toString();
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
