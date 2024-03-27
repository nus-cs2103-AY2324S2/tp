package seedu.address.model.reservation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reservation's pax in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPax(String)}
 */
public class Pax {

    public static final String MESSAGE_CONSTRAINTS = "Pax should only be a positive number";
    public static final String VALIDATION_REGEX = "[1-9]\\d*";
    public final String value;

    /**
     * Constructs a {@code Pax}.
     *
     * @param pax A valid pax.
     */
    public Pax(String pax) {
        requireNonNull(pax);
        checkArgument(isValidPax(pax), MESSAGE_CONSTRAINTS);
        this.value = pax;
    }

    /**
     * Returns true if a given string is a valid pax.
     */
    public static boolean isValidPax(String test) {
        return test.matches(VALIDATION_REGEX);
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

        // instanceof handles nulls
        if (!(other instanceof Pax)) {
            return false;
        }

        Pax otherPax = (Pax) other;
        return this.value.equals(otherPax.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
