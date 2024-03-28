package seedu.address.model.house;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a House's street.
 * Guarantees: immutable; is valid as declared in {@link #isValidStreet(String)}
 */
public class Street {

    public static final String MESSAGE_CONSTRAINTS =
            "Street should only contain alphanumeric characters and hyphens.";
    public static final String VALIDATION_REGEX = "^(?=.*[a-zA-Z0-9])[a-zA-Z0-9\\s-]+$";
    public final String value;

    /**
     * Constructs a {@code Street}.
     *
     * @param street A valid street.
     */
    public Street(String street) {
        requireNonNull(street);
        checkArgument(isValidStreet(street), MESSAGE_CONSTRAINTS);
        value = street;
    }

    /**
     * Returns true if a given string is a valid street.
     */
    public static boolean isValidStreet(String test) {
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
        if (!(other instanceof Street)) {
            return false;
        }

        Street otherStreet = (Street) other;
        return value.equals(otherStreet.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
