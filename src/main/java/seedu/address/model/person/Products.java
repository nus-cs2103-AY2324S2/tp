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
     * Constructs a {@code Products}.
     *
     * @param products A valid list of products.
     */
    public Products(List<String> products) {
        this.products = products;
    }

    public static boolean isValidProducts(List<String> test) {
        return test.stream().allMatch(product -> product.matches("\\p{Alnum}+"));
    }

    public List<String> getProducts() {
        return products;
    }

    public void addProducts(List<String> products) {
        this.products.addAll(products);
    }

    public void removeProduct(String product) {
        products.remove(product);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    @Override
    public String toString() {
        return products.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

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

    @Override
    public int hashCode() {
        return products.hashCode();
    }
}
