package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Order's discount in the order book.
 * The amount which is discounted from original price.
 * Guarantees: immutable; is valid as declared in {@link #isValidDiscount(String)}
 */
public class Discount {

    /**
     * The discount must be positive.
     */
    public static final String MESSAGE_CONSTRAINTS = "Discount should be a positive number";

    /**
     * The discount must be a positive number with at most 2 decimal places.
     */
    public static final String VALIDATION_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";

    public final int discount;

    /**
     * Constructs a {@code Discount}.
     *
     * @param discount A valid discount.
     */
    public Discount(String discount) {
        requireNonNull(discount);
        checkArgument(isValidDiscount(discount), MESSAGE_CONSTRAINTS);
        this.discount = parseDiscount(discount);
    }

    /**
     * Returns true if a given string is a valid discount.
     */
    public static boolean isValidDiscount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private int parseDiscount(String discount) {
        return Integer.parseInt(discount);
    }

    @Override
    public String toString() {
        return Integer.toString(discount);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Discount)) {
            return false;
        }

        Discount otherDiscount = (Discount) other;
        return discount == otherDiscount.discount;

    }

    @Override
    public int hashCode() {
        return discount;
    }
}
