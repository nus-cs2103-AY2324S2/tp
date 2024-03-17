package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's NRIC in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)}
 */
public class Nric {
    public static final String MESSAGE_CONSTRAINTS =
            "NRIC number should contain a prefix of S or T, followed by 7 digits, and end with a letter. "
                    + "There should not be blank.";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[ST]\\\\d{7}[A-Z]$";

    public final String nric;

    /**
     * Constructs a {@code NRIC}.
     *
     * @param name A valid NRIC.
     */
    public Nric(String name) {
        requireNonNull(name);
        checkArgument(isValidNric(name), MESSAGE_CONSTRAINTS);
        this.nric = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.nric;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Nric)) {
            return false;
        }

        Nric otherNric = (Nric) other;
        return nric.equals(otherNric.nric);
    }

    @Override
    public int hashCode() {
        return nric.hashCode();
    }
}
