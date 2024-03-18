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
    public final String product;

    /**
     * Constructs an {@code Product}.
     *
     * @param product A valid product.
     */
    public Product(String product) {
        requireNonNull(product);
        checkArgument(isValidProduct(product), MESSAGE_CONSTRAINTS);
        this.product = product;
    }

    /**
     * Returns true if a given string is a valid product.
     */
    public static boolean isValidProduct(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return product;
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
        return product.equals(otherProduct.product);
    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }
}
