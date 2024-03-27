package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Meeting;

import java.time.format.DateTimeFormatter;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {

    private final String meetingDetail;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting detail.
     *
     * @param meetingDetail A valid meeting detail.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("meetingDetail") String meetingDetail) {
        this.meetingDetail = meetingDetail;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     *
     * @param source The source Meeting object to convert.
     */
    public JsonAdaptedMeeting(Meeting source) {
        // Here, you convert the LocalDateTime to a string in the desired format
        this.meetingDetail = source.getMeeting().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @return The converted Meeting object.
     * @throws IllegalValueException If there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        if (!Meeting.isValidMeeting(meetingDetail)) {
            throw new IllegalValueException(Meeting.MESSAGE_CONSTRAINTS);
        }
        return new Meeting(meetingDetail);
    }

    @JsonProperty("meetingDetail")
    public String getMeetingDetail() {
        return meetingDetail;
    }
}
