package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.schedule.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public class JsonAdaptedSchedule {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";
    private static final String MESSAGE_INVALID_ScheduleNAME = "Schedule name must not contain invalid characters";

    private static final String MESSAGE_INVALID_ScheduleTime = "Schedule timing must fit constraints!";
    private final String name;
        private final LocalDateTime startTime;
        private final LocalDateTime endTime;

        /**
         * Constructs a {@code JsonAdaptedSlot} with the given {@code pet},
         * {@code dateTime} and {@code duration}.
         */
        @JsonCreator
        public JsonAdaptedSchedule(@JsonProperty("name") String name, @JsonProperty("startTime") String startTime,
                                   @JsonProperty("endTime") String endTime) {
            this.name = name;
            this.startTime = LocalDateTime.parse(startTime);
            this.endTime = LocalDateTime.parse(endTime);
        }

        /**
         * Converts a given {@code Slot} into this class for Jackson use.
         */
        public JsonAdaptedSchedule(Schedule schedule) {
            name = schedule.getSchedName();
            startTime = schedule.getStartTime();
            endTime = schedule.getEndTime();
        }

        public Schedule toModelType() throws IllegalValueException {
            if (name == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
            }
            if (!Schedule.isValidSchedName(name)) {
                throw new IllegalValueException(MESSAGE_INVALID_ScheduleNAME);
            }
            if (!Schedule.isValidTiming(startTime, endTime)) {
                throw new IllegalValueException(MESSAGE_INVALID_ScheduleTime);
            }

            return new Schedule(name, startTime, endTime);
        }
    }

