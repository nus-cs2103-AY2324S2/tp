package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.weeknumber.WeekNumber;

/**
 * Jackson-friendly version of {@link WeekNumber}.
 */
class JsonAdaptedWeekNumber {

    private final String weekNumber;

    /**
     * Constructs a {@code JsonAdaptedWeekNumber} with the given {@code weekNumber}.
     */
    @JsonCreator
    public JsonAdaptedWeekNumber(String weekNumber) {
        this.weekNumber = weekNumber;
    }

    /**
     * Converts a given {@code WeekNumber} into this class for Jackson use.
     */
    public JsonAdaptedWeekNumber(WeekNumber source) {
        // Assuming toString() method returns the string representation of the week number
        weekNumber = source.toString();
    }
    @JsonValue
    public String getWeekNumber() {
        return weekNumber;
    }

    /**
     * Converts this Jackson-friendly adapted week number object into the model's {@code WeekNumber} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted week number.
     */
    public WeekNumber toModelType() throws IllegalValueException {
        if (!WeekNumber.isValidWeekNumber(weekNumber)) {
            throw new IllegalValueException(WeekNumber.MESSAGE_CONSTRAINTS);
        }
        return new WeekNumber(weekNumber);
    }
}
