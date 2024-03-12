package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Date;
import seedu.address.model.order.Order;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    private final String arrivalDate;
    private final String description;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code order}.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("arrivalDate") String arrivalDate,
                            @JsonProperty("description") String description,
                            @JsonProperty("status") String status) {
        this.arrivalDate = arrivalDate;
        this.description = description;
        this.status = status;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        this.arrivalDate = source.getDate().toString();
        this.description = source.getDescription();
        this.status = source.getStatus();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        // TODO: Add validation of arrivalDate and description
        // if (!Order.isValidTagName(tagName)) {
        //     throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        // }

        final Date modelDate = new Date(arrivalDate);

        return new Order(modelDate, description);
    }
}
