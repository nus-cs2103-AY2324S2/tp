package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Availability;

public class JsonAdaptedAvailability {
    private final String availability;

    /**
     * Constructs a {@code JsonAdaptedAvailability} with the given availability details.
     */
    @JsonCreator
    public JsonAdaptedAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * Converts a given {@code Availability} into this class for Jackson use.
     */
    public JsonAdaptedAvailability(Availability source) {
        availability = source.toString();
    }

    @JsonValue
    public String getAvailability() {
        return availability;
    }

    /**
     * Converts this Jackson-friendly adapted availability object into the model's {@code Availability} object.
     */
    public Availability toModelType() throws IllegalValueException {
        if (!Availability.isValidAvailability(availability)) {
            throw new IllegalValueException(Availability.MESSAGE_CONSTRAINTS);
        }
        return new Availability(availability);
    }
}
