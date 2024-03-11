package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's nric in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class NRIC {

    public static final String MESSAGE_CONSTRAINTS =
            "NRIC should only contain alphanumeric characters of length 9 and match the correct format.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[STFGMstfgm][0-9]{7}[A-Za-z]$";

    public final String nric;

    /**
     * Constructs a {@code NRIC}.
     *
     * @param nric A valid nric.
     */
    public NRIC(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNRIC(nric), MESSAGE_CONSTRAINTS);
        this.nric = nric;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidNRIC(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return nric;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        NRIC otherNRIC = (NRIC) other;
        return nric.equals(otherNRIC.nric);
    }

    @Override
    public int hashCode() {
        return nric.hashCode();
    }

}
