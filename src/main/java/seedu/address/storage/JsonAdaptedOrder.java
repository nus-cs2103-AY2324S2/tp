package seedu.address.storage;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;

/**
 * Jackson friendly version of {@link Order}.
 */
public class JsonAdaptedOrder {
    private final Order order;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code order}.
     */
    @JsonCreator
    public JsonAdaptedOrder(Map<Product, Quantity> order) {
        this.order = new Order(order);
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order order) {
        this.order = order;
    }

    @JsonValue
    public Map<Product, Quantity> getOrder() {
        return this.order.getProductMap();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Order toModelType() throws IllegalValueException {
        return new Order(this.order.getProductMap());
    }
}
