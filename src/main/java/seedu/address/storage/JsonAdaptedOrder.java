package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.orders.Order;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {
    private final String items;
    private final String orderDateTime;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code items} and {@code orderDateTime}.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("items") String items, @JsonProperty("orderDateTime") String orderDateTime) {
        this.items = items;
        this.orderDateTime = orderDateTime;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        items = source.items;
        orderDateTime = source.orderDateTime.toString();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (!Order.isValidItems(items)) {
            throw new IllegalValueException(Order.MESSAGE_CONSTRAINTS);
        }

        try {
            LocalDateTime modelOrderDateTime = LocalDateTime.parse(orderDateTime);
            return new Order(items, modelOrderDateTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Order.MESSAGE_INVALID_DATETIME);
        }
    }
}
