package seedu.findvisor.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.findvisor.commons.exceptions.IllegalValueException;
import seedu.findvisor.commons.util.DateTimeUtil;
import seedu.findvisor.model.person.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {
    private final String start;
    private final String end;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given {@code start} and {@code end}.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("start") String start,
            @JsonProperty("end") String end, @JsonProperty("remark") String remark) {
        this.start = start;
        this.end = end;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Optional<Meeting>} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Optional<Meeting> source) {
        start = source.map(meeting -> DateTimeUtil.dateTimeToInputString(meeting.start)).orElse("");
        end = source.map(meeting -> DateTimeUtil.dateTimeToInputString(meeting.end)).orElse("");
        remark = source.map(meeting -> meeting.remark).orElse("");
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *      meeting.
     */
    public Optional<Meeting> toModelType() throws IllegalValueException {
        if (start == "" || end == "") {
            return Optional.empty();
        }
        try {
            LocalDateTime start = DateTimeUtil.parseDateTimeString(this.start);
            LocalDateTime end = DateTimeUtil.parseDateTimeString(this.end);
            if (!Meeting.isValidDateTime(start, end)) {
                throw new IllegalValueException(Meeting.MESSAGE_DATETIME_CONSTRAINTS);
            }
            if (!Meeting.isValidRemark(remark)) {
                throw new IllegalValueException(Meeting.MESSAGE_REMARK_CONSTRAINTS);
            }
            return Optional.of(new Meeting(start, end, remark));
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Meeting.MESSAGE_DATETIME_CONSTRAINTS);
        }
    }

}

