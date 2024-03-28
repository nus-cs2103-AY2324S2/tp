package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Maitainer's commission in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCommission(String)}
 */
public class Commission {
    public static final String MESSAGE_CONSTRAINTS =
            "Commision should in this format of ${amount}/hr";
    public static final String VALIDATION_REGEX = "^\\$\\d+/hr$";
    public final String value;

    /**
     * Constructs an {@code Commission}.
     *
     * @param value A valid commission.
     */
    public Commission(String value) {
        requireNonNull(value);
        checkArgument(isValidCommission(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid commission.
     */
    public static boolean isValidCommission(String test) {
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
        if (!(other instanceof Commission)) {
            return false;
        }

        Commission otherCommission = (Commission) other;
        return value.equals(otherCommission.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
