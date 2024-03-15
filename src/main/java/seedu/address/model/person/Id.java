package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS =
            "ID should only contain alphanumeric characters, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[A-Z0-9]+$";

    public final String id;

    /**
     * Constructs a {@code Id}.
     *
     * @param value A valid Id.
     */
    public Id(String value) {
        requireNonNull(value);
        checkArgument(isValidId(value), MESSAGE_CONSTRAINTS);
        id = value;
    }
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.id;
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

        Id otherName = (Id) other;
        return this.id.equals(otherName.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
