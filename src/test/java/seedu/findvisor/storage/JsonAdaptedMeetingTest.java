package seedu.findvisor.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.findvisor.testutil.Assert.assertThrows;

import java.util.Optional;

import static seedu.findvisor.commons.util.DateTimeUtil.parseDateTimeString;

import org.junit.jupiter.api.Test;

import seedu.findvisor.commons.exceptions.IllegalValueException;
import seedu.findvisor.model.person.Meeting;

public class JsonAdaptedMeetingTest {
    private static final String VALID_START = "12-02-2024T12:00";
    private static final String VALID_END = "12-02-2024T13:00";
    private static final String INVALID_START = "INVALID_START";
    private static final String INVALID_END = "INVALID_END";

    @Test
    public void toModelType_validMeeting_returnsOptionalMeeting() throws Exception {
        JsonAdaptedMeeting jsonMeeting = new JsonAdaptedMeeting(VALID_START, VALID_END);
        Meeting meeting = new Meeting(parseDateTimeString(VALID_START), parseDateTimeString(VALID_END));
        assertEquals(meeting, jsonMeeting.toModelType().get());
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(INVALID_START, INVALID_END);
        String expectedMessage = Meeting.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);

        meeting = new JsonAdaptedMeeting(VALID_START, INVALID_END);
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);

        meeting = new JsonAdaptedMeeting(INVALID_START, VALID_END);
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_emptyString_returnsEmptyOptionalMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting("", "");
        assertEquals(meeting.toModelType(), Optional.empty());

        meeting = new JsonAdaptedMeeting(VALID_START, "");
        assertEquals(meeting.toModelType(), Optional.empty());

        meeting = new JsonAdaptedMeeting("", VALID_END);
        assertEquals(meeting.toModelType(), Optional.empty());
    }

}
