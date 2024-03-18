package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Event;


/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {
    private final String name;
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given {@code eventName}, {@code dateTime}
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name,
                            @JsonProperty("dateTime") String dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        this.name = source.name;
        this.dateTime = convertToExpectedDateTimeFormat(source.date, source.startTime, source.endTime);
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Event.
     */
    public Event toModelType() throws IllegalValueException {
        if (!Event.isValidEvent(this.dateTime)) {
            throw new IllegalValueException(Event.MESSAGE_CONSTRAINTS);
        }
        return new Event(this.name, this.dateTime);
    }

    private String convertToExpectedDateTimeFormat(String date, String startTime, String endTime) {
        if (startTime == null) {
            return date;
        }

        return String.format("%s, %s - %s", date, startTime, endTime);
    }
}
