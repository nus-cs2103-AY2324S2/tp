package staffconnect.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import staffconnect.commons.exceptions.IllegalValueException;
import staffconnect.model.availability.Availability;

/**
 * Jackson-friendly version of {@link Availability}.
 */
class JsonAdaptedAvailability {

    private final String value;

    /**
     * Constructs a {@code JsonAdaptedAvailability} with the given {@code value}.
     */
    @JsonCreator
    public JsonAdaptedAvailability(String value) {
        this.value = value;
    }

    /**
     * Converts a given {@code Availability} into this class for Jackson use.
     */
    public JsonAdaptedAvailability(Availability source) {
        value = source.value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Converts this Jackson-friendly adapted availability object into the model's {@code Availability} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted availability.
     */
    public Availability toModelType() throws IllegalValueException {
        if (!Availability.isValidAvailability(value)) {
            throw new IllegalValueException(Availability.MESSAGE_CONSTRAINTS);
        }
        return new Availability(value);
    }

}
