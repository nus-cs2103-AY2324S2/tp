package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.DateTime;

/**
 * Jackson-friendly version of {@link DateTime}.
 */
public class JsonAdaptedDateTime {
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedDateTime} with the given {@code dateTime}.
     */
    @JsonCreator
    public JsonAdaptedDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code DateTime} into this class for Jackson use.
     */
    public JsonAdaptedDateTime(DateTime source) {
        dateTime = source.value;
    }

    @JsonValue
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Converts this Jackson-friendly adapted dateTime object into the model's {@code dateTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted dateTime.
     */
    public DateTime toModelType() throws IllegalValueException {
        if (!DateTime.isValidDateTime(dateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return new DateTime(dateTime);
    }
}
