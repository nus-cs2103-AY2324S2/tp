package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.schedule.Schedule;

/**
 * Jackson-friendly version of {@link Schedule}.
 */
public class JsonAdaptedSchedule {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";
    private static final String MESSAGE_INVALID_ScheduleNAME = "Schedule name must not contain invalid characters";

    private static final String MESSAGE_INVALID_ScheduleTime = "Schedule timing must fit constraints!";
    private final String moduleName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs a {@code JsonAdaptedSlot} with the given {@code pet},
     * {@code dateTime} and {@code duration}.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("moduleName") String name, @JsonProperty("startTime") String startTime,
                               @JsonProperty("endTime") String endTime) {
        this.moduleName = name;
        this.startTime = LocalDateTime.parse(startTime);
        this.endTime = LocalDateTime.parse(endTime);
    }

    /**
     * Converts a given {@code Slot} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule schedule) {
        moduleName = schedule.getSchedName();
        startTime = schedule.getStartTime();
        endTime = schedule.getEndTime();
    }

    /**
     * Converts this Jackson-friendly adapted schedule object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */

    public Schedule toModelType() throws IllegalValueException {
        if (moduleName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        return new Schedule(moduleName, startTime, endTime);
    }
}

