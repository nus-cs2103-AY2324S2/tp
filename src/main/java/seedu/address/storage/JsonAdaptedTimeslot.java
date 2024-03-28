package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.timeslots.Timeslots;

/**
 * Jackson-friendly version of {@link Timeslots}.
 */
class JsonAdaptedTimeslot {

    private final String timeslot;

    /**
     * Constructs a {@code JsonAdaptedTimeslot} with the given {@code timeslotName}.
     */
    @JsonCreator
    public JsonAdaptedTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * Converts a given {@code Timeslot} into this class for Jackson use.
     */
    public JsonAdaptedTimeslot(Timeslots source) {
        timeslot = source.timeslot;
    }

    @JsonValue
    public String getTimeslot() {
        return timeslot;
    }

    /**
     * Converts this Jackson-friendly adapted timeslot object into the model's {@code Timeslot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted timeslot.
     */
    public Timeslots toModelType() throws IllegalValueException {
        if (!Timeslots.isValidTimeslot(timeslot)) {
            throw new IllegalValueException(Timeslots.MESSAGE_CONSTRAINTS);
        }
        return new Timeslots(timeslot);
    }

}
