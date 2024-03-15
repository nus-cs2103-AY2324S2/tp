package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.orders.Order;

public class JsonAdaptedOrder {
    private final String items;
    private final String orderDateTime;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code items} and {@code orderDateTime}.
     */
    @JsonCreator
    public JsonAdaptedOrder(String items, LocalDateTime orderDateTime) {
        this.items = items;
        this.orderDateTime = orderDateTime.toString();
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        items = source.items;
        orderDateTime = source.orderDateTime.toString();
    }

    @JsonValue
    public String getItems() {
        return items;
    }

    @JsonValue
    public String getOrderDateTime() {
        return orderDateTime;
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
            throw new IllegalValueException("Order DateTime is invalid");
        }
    }
}
