package seedu.address.model.person;

import java.util.List;
import java.util.stream.Collectors;

public class Products {
    private final List<String> products;

    public Products(List<String> products) {
        this.products = products;
    }

    public List<String> getProducts() {
        return products;
    }

    public void addProduct(String product) {
        this.products.add(product);
    }

    public void addProducts(List<String> products) {
        this.products.addAll(products);
    }

    public void removeProduct(String product) {
        products.remove(product);
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
