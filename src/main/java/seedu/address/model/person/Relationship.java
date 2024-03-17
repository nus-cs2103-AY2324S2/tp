package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Relationship in the address book.
 * Guarantees: immutable; value is valid as declared in {@link #isValidRelationship(String)}
 */
public class Relationship {

    public static final String MESSAGE_CONSTRAINTS = "Relationship should only be client or partner";

    public final String value;

    /**
     * Constructs a {@code Relationship}.
     *
     * @param value A valid relationship value.
     */
    public Relationship(String value) {
        requireNonNull(value);
        checkArgument(isValidRelationship(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid relationship value.
     */
    public static boolean isValidRelationship(String test) {
        return test.equalsIgnoreCase("client") || test.equalsIgnoreCase("partner");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Relationship)) {
            return false;
        }

        Relationship otherRelationship = (Relationship) other;
        return value.equals(otherRelationship.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + value + ']';
    }

}
