package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Attendance;

/**
 * Jackson-friendly version of {@link Attendance}.
 */
class JsonAdaptedAttendance {

    private final String attendanceDate;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        attendanceDate = source.attendanceName;
    }

    @JsonValue
    public String getAttendanceDate() {
        return attendanceDate;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Attendance toModelType() throws IllegalValueException {
        if (!Attendance.isValidDate(attendanceDate)) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        return new Attendance(attendanceDate);
    }

}
