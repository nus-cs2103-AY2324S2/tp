package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Quantity;

/**
 * Jackson-friendly version of {@link Quantity}.
 */
public class JsonAdaptedQuantity {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Quantity's Integer field cannot be negative";

    private final Integer quantityNumber;

    /**
     * Constructs a {@code JsonAdaptedQuantity} with the given {@code quantityNumber}.
     * @param quantityNumber Quantity number that the Quantity should have.
     */
    @JsonCreator
    public JsonAdaptedQuantity(@JsonProperty("quantity") Integer quantityNumber) {
        this.quantityNumber = quantityNumber;
    }

    /**
     * Convert a {@code Quantity} into this class for Jackson use.
     * @param source Quantity object to be converted to {@code JsonAdaptedQuantity}.
     */
    public JsonAdaptedQuantity(Quantity source) {
        this.quantityNumber = source.getValue();
    }

    /**
     * converts this Jackson-friendly adapted Quantity object ino the model's {@code Quantity}.
     * @return a Model-friendly version of Quantity.
     * @throws IllegalValueException if the quantityNumber field is negative.
     */
    public Quantity toModelType() throws IllegalValueException {
        if (this.quantityNumber < 0) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }
        return new Quantity(this.quantityNumber);
    }
}
