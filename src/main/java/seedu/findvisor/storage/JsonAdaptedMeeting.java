package seedu.findvisor.storage;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.findvisor.commons.exceptions.IllegalValueException;
import seedu.findvisor.commons.util.DateTimeUtil;
import seedu.findvisor.model.person.Meeting;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedMeeting {
    private final String start;
    private final String end;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given {@code meeting}.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("start") String start, @JsonProperty("end") String end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Optional<Meeting> source) {
        start = source.map(meeting -> DateTimeUtil.dateTimeToInputString(meeting.start)).orElse("");
        end = source.map(meeting -> DateTimeUtil.dateTimeToInputString(meeting.end)).orElse("");
        // end = DateTimeUtil.dateTimeToString(source.end);
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
        LocalDateTime start = DateTimeUtil.parseDateTimeString(this.start);
        LocalDateTime end = DateTimeUtil.parseDateTimeString(this.end);
        if (!Meeting.isValidDateTime(start, end)) {
            throw new IllegalValueException(Meeting.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Meeting(start, end));
    }

}

