package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the price of items for the order in the order book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Price must be a positive number";
    public final int value;

    /**
     * Constructs a {@code Price}.
     *
     * @param value A valid price.
     */
    public Price(String value) {
        requireNonNull(value);
        checkArgument(isValidPrice(value), MESSAGE_CONSTRAINTS);
        this.value = Integer.parseInt(value);
    }

    /**
     * Returns true if a given double is a valid price.
     *
     * @param test the price to be tested
     * @return true if the price is valid
     */
    public static boolean isValidPrice(String test) {
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

        if (!(other instanceof Price)) {
            return false;
        }

        Price otherPrice = (Price) other;
        return value == otherPrice.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
