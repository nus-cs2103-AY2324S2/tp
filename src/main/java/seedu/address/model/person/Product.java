package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Supplier's product in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidProduct(String)}
 */
public class Product {
    public static final String MESSAGE_CONSTRAINTS = "Product can take any values, and it should not be blank";

    /*
     * The first character of the product must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs an {@code Product}.
     *
     * @param value A valid product.
     */
    public Product(String value) {
        requireNonNull(value);
        checkArgument(isValidProduct(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid product.
     */
    public static boolean isValidProduct(String test) {
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
        if (!(other instanceof Product)) {
            return false;
        }

        Product otherProduct = (Product) other;
        return value.equals(otherProduct.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
