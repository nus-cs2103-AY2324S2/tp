package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class JsonAdaptedDateTime {
    private final String dateTime;

    @JsonCreator
    public JsonAdaptedDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public JsonAdaptedDateTime(DateTime source) {
        dateTime = source.value;
    }

    @JsonValue
    public String getDateTime() {
        return dateTime;
    }

    public DateTime toModelType() throws IllegalValueException {
        if (!DateTime.isValidDateTime(dateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return new DateTime(dateTime);
    }
}
