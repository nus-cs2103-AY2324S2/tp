package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's membership tier in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMembership(String)}
 */
public class Membership {

    public static final String MESSAGE_CONSTRAINTS =
            "Membership tier should be one of the following: T1, T2, T3";

    // This regex matches only 'T1', 'T2', or 'T3'.
    public static final String VALIDATION_REGEX = "T[123]";

    public final String value;

    /**
     * Constructs a {@code Membership}.
     *
     * @param membership A valid membership tier.
     */
    public Membership(String membership) {
        requireNonNull(membership);
        checkArgument(isValidMembership(membership), MESSAGE_CONSTRAINTS);
        value = membership;
    }

    /**
     * Returns true if a given string is a valid membership tier.
     */
    public static boolean isValidMembership(String test) {
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

        if (!(other instanceof Membership)) { // instanceof handles nulls
            return false;
        }

        Membership otherMembership = (Membership) other;
        return value.equals(otherMembership.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
