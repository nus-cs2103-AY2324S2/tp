package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.AttendanceStatus;
import seedu.address.model.tag.Attendance;

/**
 * Jackson-friendly version of {@link Attendance}.
 */
class JsonAdaptedAttendance {

    private final String attendanceDate;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty("date") String attendanceDate, @JsonProperty("status") String status) {
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        attendanceDate = source.attendanceName.getDate();
        status = source.attendanceName.getStatus();
    }

    @JsonProperty("date")
    public String getAttendanceDate() {
        return attendanceDate;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
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
        if (!Attendance.isValidStatus(status)) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        return new Attendance(new AttendanceStatus(attendanceDate, status));
    }

}
