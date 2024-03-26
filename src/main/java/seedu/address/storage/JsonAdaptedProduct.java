package seedu.address.storage;

import static seedu.address.model.order.Product.isValidProduct;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Product;

/**
 * Jackson-friendly version of {@link Product}.
 */
public class JsonAdaptedProduct {

    public static final String MISSING_NAME_FIELD_MESSAGE = "Product's name field is missing!";

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedProduct} with the given product details.
     * @param name name of the Product.
     */
    @JsonCreator
    public JsonAdaptedProduct(@JsonProperty("product") String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Product} into this class for Jackson use.
     */
    public JsonAdaptedProduct(Product source) {
        this.name = source.toString();
    }

    /**
     * Converts this Jackson-friendly adapted product object into the model's {@code Product}.
     * @return Model-friendly version of product object.
     * @throws IllegalValueException if there were anhy data constraints violated in the adapted product.
     */
    public Product toModelType() throws IllegalValueException {
        System.out.println(this.name);
        if (!isValidProduct(this.name)) {
            throw new IllegalValueException(MISSING_NAME_FIELD_MESSAGE);
        }

        return new Product(this.name);
    }
}
