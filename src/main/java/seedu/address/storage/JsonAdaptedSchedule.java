package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.schedule.Schedule;

import java.util.List;

public class JsonAdaptedSchedule {
        public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";
    private static final String MESSAGE_INVALID_ScheduleNAME = "Schedule name must not contain invalid characters";

    private final String name;
        private final String startTime;
        private final String endTime;

        /**
         * Constructs a {@code JsonAdaptedSlot} with the given {@code pet},
         * {@code dateTime} and {@code duration}.
         */
        @JsonCreator
        public JsonAdaptedSchedule(@JsonProperty("name") String name, @JsonProperty("startTIme") String startTime,
                                   @JsonProperty("duration") String endTime) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        /**
         * Converts a given {@code Slot} into this class for Jackson use.
         */
        public JsonAdaptedSchedule(Schedule schedule) {
            name = schedule.getSchedName();
            startTime = schedule.getStartTime().toString();
            endTime = schedule.getEndTime().toString();
        }

    }

