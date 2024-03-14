package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's availability in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAvailability(String)}
 */
public class Availability {
    public static final String MESSAGE_CONSTRAINTS = "Availabilities can take any values, and it should not be blank";

    /**
     * The first character of the availability must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param availability A valid date.
     */
    public Availability(String availability) {
        requireNonNull(availability);
        checkArgument(isValidAvailability(availability), MESSAGE_CONSTRAINTS);
        value = availability;
    }

    /**
     * Returns true if a given string is a valid availability.
     */
    public static boolean isValidAvailability(String test) {
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
        if (!(other instanceof Availability)) {
            return false;
        }

        Availability otherAvailability = (Availability) other;
        return value.equals(otherAvailability.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
