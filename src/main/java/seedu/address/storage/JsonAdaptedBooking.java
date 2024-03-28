package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Description;
import seedu.address.model.booking.EndTime;
import seedu.address.model.booking.StartTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;


/**
 * Jackson-friendly version of {@link Booking}.
 */
class JsonAdaptedBooking {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private final String description;
    private final String start;
    private final String end;

    /**
     * Constructs a {@code JsonAdaptedBooking} with the given Booking details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("description") String description, @JsonProperty("start") String start,
                             @JsonProperty("end") String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Converts a given {@code Booking} into this class for Jackson use.
     */
    public JsonAdaptedBooking(Booking source) {
        description = source.getDescription().description;
        start = source.getStart().startTimeString;
        end = source.getEnd().endTimeString;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Booking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Booking toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Booking.class.getSimpleName()));
        }
        final Description modelDescription = new Description(description);

        if (start == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                          StartTime.class.getSimpleName()));
        }
        if (!StartTime.isValidStartTime(start)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final StartTime modelStart = new StartTime(start);

        if (end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EndTime.class.getSimpleName()));
        }
        if (!EndTime.isValidEndTime(end)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final EndTime modelEnd = new EndTime(end);

        return new Booking(modelDescription, modelStart, modelEnd);
    }
}
