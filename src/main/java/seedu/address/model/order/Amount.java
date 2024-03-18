package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the amount of items for the order in the order book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {

    public static final String MESSAGE_CONSTRAINTS =
            "Amount must be a positive number";
    public final int value;

    /**
     * Constructs a {@code Amount}.
     *
     * @param value A valid amount.
     */
    public Amount(String value) {
        requireNonNull(value);
        checkArgument(isValidAmount(value), MESSAGE_CONSTRAINTS);
        this.value = Integer.parseInt(value);
    }

    /**
     * Returns true if a given double is a valid amount.
     *
     * @param test the amount to be tested
     * @return true if the amount is valid
     */
    public static boolean isValidAmount(String test) {
        return Integer.parseInt(test) > 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Amount)) {
            return false;
        }

        Amount otherAmount = (Amount) other;
        return value == otherAmount.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
