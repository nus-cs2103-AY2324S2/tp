package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;

import java.util.Map;

public class JsonAdaptedOrder {
    private final Map<Product, Quantity> order;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code order}.
     */
    @JsonCreator
    public JsonAdaptedOrder(Map<Product, Quantity> order) {
        this.order = order;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order order) {
        this.order = order.getProductMap();
    }

    @JsonValue
    public String getOrder() {
        return this.order.toString();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Order toModelType() throws IllegalValueException {
        return new Order(this.order);
    }
}
