package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedAttendance {

    private final String attendanceDate;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given {@code attendanceDate}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        attendanceDate = source.attendanceDate.toString();
    }

    @JsonValue
    public String getAttendanceDate() {
        return attendanceDate;
    }

    /**
     * Converts this Jackson-friendly adapted attendance object into the model's {@code Attendance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Attendance toModelType() throws IllegalValueException {
        if (!Attendance.isValidAttendanceDate(attendanceDate)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Attendance(LocalDate.parse(attendanceDate));
    }

}
