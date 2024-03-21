package seedu.address.model.person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a Client's products in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Products {

    public static final String MESSAGE_CONSTRAINTS = "Products should only contain alphanumeric characters and spaces";

    private final List<String> products;

    /**
     * Represents the products of a client.
     */
    public Products() {
        this.products = List.of();
    }

    /**
     * Constructs a {@code Products}.
     *
     * @param products A valid list of products.
     */
    public Products(List<String> products) {
        this.products = products;
    }

    /**
     * Constructs a {@code Products} from a string of products.
     *
     * @param products A string of products separated by spaces.
     */
    public Products(String products) {
        this.products = List.of(products.split(" "));
    }

    /**
     * Checks if a list of products is valid.
     *
     * @param test The list of products to be checked.
     * @return {@code true} if all products are alphanumeric, {@code false} otherwise.
     */
    public static boolean isValidProducts(List<String> test) {
        return test.stream().allMatch(product -> product.matches("[\\p{Alnum} ]+"));
    }

    /**
     * Returns the list of products.
     *
     * @return The list of products.
     */
    public List<String> getProducts() {
        return products;
    }

    /**
     * Adds a list of products to the existing products.
     *
     * @param products The list of products to be added.
     */
    public void addProducts(List<String> products) {
        this.products.addAll(products);
    }

    /**
     * Removes a product from the existing products.
     *
     * @param product The product to be removed.
     */
    public void removeProduct(String product) {
        products.remove(product);
    }

    /**
     * Checks if the list of products is empty.
     *
     * @return {@code true} if the list of products is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return products.isEmpty();
    }

    /**
     * Returns a string representation of the products.
     *
     * @return A string representation of the products.
     */
    @Override
    public String toString() {
        return products.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    /**
     * Checks if this {@code Products} object is equal to another object.
     *
     * @param other The object to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Products)) {
            return false;
        }

        Products otherProducts = (Products) other;
        return otherProducts.getProducts().equals(getProducts());
    }

    /**
     * Returns the hash code of the products.
     *
     * @return The hash code of the products.
     */
    @Override
    public int hashCode() {
        return products.hashCode();
    }
}
