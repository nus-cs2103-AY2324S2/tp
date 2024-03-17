package staffconnect.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import staffconnect.commons.exceptions.IllegalValueException;
import staffconnect.model.meeting.Description;
import staffconnect.model.meeting.MeetDateTime;
import staffconnect.model.meeting.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    private final String description;

    private final String date;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given {@code Meeting}.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("description") String description, @JsonProperty("date") String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        description = source.getDescription().toString();
        date = source.getStartDate().toString();

    }

//    @JsonValue
//    public String getDescription() {
//        return description;
//    }
//    @JsonValue
//    public String getDate() {
//        return date;
//    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        if (!MeetDateTime.isValidMeetDateTime(date)) {
            throw new IllegalValueException(MeetDateTime.MESSAGE_CONSTRAINTS);
        }
        return new Meeting(new Description(description),new MeetDateTime(date));
    }

}
