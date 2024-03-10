package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Relationship {
    public static final String MESSAGE_CONSTRAINTS = "Relationship should only be client or partner";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param relationship A valid relationship.
     */
    public Relationship(String relationship) {
        requireNonNull(relationship);
        checkArgument(isValidRelationshipType(relationship), MESSAGE_CONSTRAINTS);
        value = relationship;
    }

    /**
     * Returns true if a given string is a valid relationship.
     */
    public static boolean isValidRelationshipType(String test) {
        return test.equalsIgnoreCase("client") || test.equalsIgnoreCase("partner");
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
}
