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
    public final String commission;

    /**
     * Constructs an {@code Commission}.
     *
     * @param commission A valid commission.
     */
    public Commission(String commission) {
        requireNonNull(commission);
        checkArgument(isValidCommission(commission), MESSAGE_CONSTRAINTS);
        this.commission = commission;
    }

    /**
     * Returns true if a given string is a valid commission.
     */
    public static boolean isValidCommission(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return commission;
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
        return commission.equals(otherCommission.commission);
    }

    @Override
    public int hashCode() {
        return commission.hashCode();
    }
}
