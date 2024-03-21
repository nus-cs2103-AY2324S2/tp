package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Products;

/**
 * Jackson-friendly version of {@link Products}.
 */
public class JsonAdaptedProducts {
    private List<String> products = new ArrayList<>();

    public JsonAdaptedProducts() {
        this.products = new ArrayList<>();
    }

    /**
     * Constructs a {@code JsonAdaptedProducts} with the given {@code products}.
     */
    public JsonAdaptedProducts(Products products) {
        this.products = products.getProducts();
    }

    public JsonAdaptedProducts(List<String> products) {
        this.products = products;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public void addProducts(List<String> products) {
        this.products.addAll(products);
    }

    /**
     * Converts this Jackson-friendly adapted products object into the model's {@code Products} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted products.
     */
    public Products toModelType() throws IllegalValueException {
        if (!Products.isValidProducts(products)) {
            throw new IllegalValueException(Products.MESSAGE_CONSTRAINTS);
        }
        return new Products(products);
    }
}
