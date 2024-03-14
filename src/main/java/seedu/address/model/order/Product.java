package seedu.address.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    public static final String MESSAGE_CONSTRAINTS =
            "Product names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the product name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private String name;

    public Product(@JsonProperty("order") String name) {
        this.name = name;
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

        if (!(other instanceof Product)){
            return false;
        }

        Product otherProduct = (Product) other;
        return this.name.equals(otherProduct.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public String getName() {
        return this.name;
    }

    public void rename(String newName) {
        this.name = newName;
    }
}
