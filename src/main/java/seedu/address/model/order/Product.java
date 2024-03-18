package seedu.address.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the Product in the Order of the Customer.
 */
public class Product {

    public static final String MESSAGE_CONSTRAINTS =
            "Product names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the product name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private String name;

    /**
     * Constructs a {@code Product} with {@code name}.
     * @param name Name of the Product.
     */
    public Product(@JsonProperty("order") String name) {
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidProduct(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Product)) {
            return false;
        }

        Product otherProduct = (Product) other;
        return this.name.equals(otherProduct.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Gets the name of the Product.
     * @return string value of the name of the product.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the product.
     * @param newName new name of the Product.
     */
    public void rename(String newName) {
        this.name = newName;
    }
}
