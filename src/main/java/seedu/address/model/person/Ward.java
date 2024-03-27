package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a patient's ward address in the address book.
 */
public class Ward {
    public static final String MESSAGE_CONSTRAINTS =
            "Ward addresses should only contain alphanumeric characters and no spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]+$";

    public final String value;

    /**
    * Constructs a {@code Ward}.
    *
    * @param value A valid ward address.
    */
    public Ward(String value) {
        requireNonNull(value);
        checkArgument(isValidWard(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns if a given string is a valid ward address.
     */
    public static boolean isValidWard(String test) {
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

        if (!(other instanceof Ward)) {
            return false;
        }

        Ward otherWard = (Ward) other;
        return value.equals(otherWard.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
