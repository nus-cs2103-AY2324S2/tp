package seedu.findvisor.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.findvisor.commons.util.DateTimeUtil.parseDateTimeString;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_MEETING_REMARK;
import static seedu.findvisor.logic.commands.CommandTestUtil.INVALID_MEETING_REMARK;
import static seedu.findvisor.testutil.Assert.assertThrows;

import java.util.Optional;

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
        JsonAdaptedMeeting jsonMeeting = new JsonAdaptedMeeting(VALID_START, VALID_END, VALID_MEETING_REMARK);
        Meeting meeting = new Meeting(parseDateTimeString(VALID_START),
                parseDateTimeString(VALID_END), VALID_MEETING_REMARK);
        assertEquals(meeting, jsonMeeting.toModelType().get());
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(INVALID_START, INVALID_END, VALID_MEETING_REMARK);
        String expectedMessage = Meeting.MESSAGE_DATETIME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);

        meeting = new JsonAdaptedMeeting(VALID_START, INVALID_END, VALID_MEETING_REMARK);
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);

        meeting = new JsonAdaptedMeeting(INVALID_START, VALID_END, VALID_MEETING_REMARK);
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_emptyString_returnsEmptyOptionalMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting("", "", VALID_MEETING_REMARK);
        assertEquals(meeting.toModelType(), Optional.empty());

        meeting = new JsonAdaptedMeeting(VALID_START, "", VALID_MEETING_REMARK);
        assertEquals(meeting.toModelType(), Optional.empty());

        meeting = new JsonAdaptedMeeting("", VALID_END, VALID_MEETING_REMARK);
        assertEquals(meeting.toModelType(), Optional.empty());
    }

    @Test
    public void toModelType_validMeetingEmptyRemark_returnsOptionalMeeting() throws Exception {
        JsonAdaptedMeeting jsonMeeting = new JsonAdaptedMeeting(VALID_START, VALID_END, "");
        Meeting meeting = new Meeting(parseDateTimeString(VALID_START),
                parseDateTimeString(VALID_END), "");
        assertEquals(meeting, jsonMeeting.toModelType().get());
    }

    @Test
    public void toModelType_invalidRemark_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_START, VALID_END, INVALID_MEETING_REMARK);
        String expectedMessage = Meeting.MESSAGE_REMARK_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedMeeting((Optional<Meeting>) null));
    }

    @Test
    public void constructor_emptyMeeting() throws Exception {
        JsonAdaptedMeeting jsonMeeting = new JsonAdaptedMeeting(Optional.empty());
        JsonAdaptedMeeting expectedJsonMeeting = new JsonAdaptedMeeting("", "", "");
        assertEquals(jsonMeeting, expectedJsonMeeting);
    }

    @Test
    public void constructor_validMeeting() throws Exception {
        Meeting meeting = new Meeting(parseDateTimeString(VALID_START),
                parseDateTimeString(VALID_END), VALID_MEETING_REMARK);
        JsonAdaptedMeeting jsonMeeting = new JsonAdaptedMeeting(Optional.of(meeting));
        JsonAdaptedMeeting expectedJsonMeeting = new JsonAdaptedMeeting(VALID_START, VALID_END, VALID_MEETING_REMARK);
        assertEquals(jsonMeeting, expectedJsonMeeting);
    }

}
