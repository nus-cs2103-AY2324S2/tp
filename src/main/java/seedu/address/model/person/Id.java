package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS = "Id is invalid. An id can include alphabets, numbers,"
            + " and certain special characters, but cannot be blank.";

    /**
     * The first character of the id must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Id}.
     *
     * @param id A valid Id.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidId(String test) {
        if (test.trim().contains(" ") || !test.matches(VALIDATION_REGEX)) {
            return false;
        }
        return true;
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
        if (!(other instanceof Id)) {
            return false;
        }

        Id otherId = (Id) other;
        return value.trim().equalsIgnoreCase(otherId.value.trim());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
