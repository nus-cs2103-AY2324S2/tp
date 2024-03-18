package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Booking's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Booking description cannot be blank.";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid name.
     *
     * Must not be empty or made of spaces.
     */
    public static boolean isValidName(String test) {
        return !test.isEmpty();
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Description)) {
            return false;
        }
        Description otherName = (Description) other;
        return description.equals(otherName.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
