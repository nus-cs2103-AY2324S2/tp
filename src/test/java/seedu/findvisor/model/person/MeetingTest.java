package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.findvisor.logic.commands.CommandTestUtil.INVALID_MEETING_REMARK;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_MEETING_REMARK;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class MeetingTest {
    private static final LocalDateTime START = LocalDateTime.of(2024, 1, 1, 12, 0);
    private static final LocalDateTime END = LocalDateTime.of(2024, 1, 1, 13, 0);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Meeting(null, null, VALID_MEETING_REMARK));
        assertThrows(NullPointerException.class, () -> new Meeting(START, null, VALID_MEETING_REMARK));
        assertThrows(NullPointerException.class, () -> new Meeting(null, END, VALID_MEETING_REMARK));
        assertThrows(NullPointerException.class, () -> new Meeting(START, END, null));
    }

    @Test
    public void constructor_invalidMeetingDateTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Meeting(END, START, VALID_MEETING_REMARK));
    }

    @Test
    public void constructor_invalidMeetingRemark_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Meeting(START, END, INVALID_MEETING_REMARK));
    }

    @Test
    public void isValidMeeting() {
        // null meeting
        assertThrows(NullPointerException.class, () -> Meeting.isValidDateTime(null, null));
        assertThrows(NullPointerException.class, () -> Meeting.isValidDateTime(START, null));
        assertThrows(NullPointerException.class, () -> Meeting.isValidDateTime(null, END));

        // invalid meeting
        assertFalse(Meeting.isValidDateTime(END, START));

        // valid meeting
        assertTrue(Meeting.isValidDateTime(START, END));
    }

    @Test
    public void isValidMeetingRemark() {
        // null meeting remark
        assertThrows(NullPointerException.class, () -> Meeting.isValidRemark(null));

        // invalid meeting remark
        assertFalse(Meeting.isValidRemark(INVALID_MEETING_REMARK));

        // valid meeting remark
        assertTrue(Meeting.isValidRemark(VALID_MEETING_REMARK));
    }

    @Test
    public void equals() {
        Meeting meeting = new Meeting(START, END, VALID_MEETING_REMARK);

        // same values -> returns true
        assertTrue(meeting.equals(new Meeting(START, END, VALID_MEETING_REMARK)));

        // same object -> returns true
        assertTrue(meeting.equals(meeting));

        // null -> returns false
        assertFalse(meeting.equals(null));

        // different types -> returns false
        assertFalse(meeting.equals(LocalDateTime.now()));

        // different values -> returns false
        assertFalse(meeting.equals(new Meeting(LocalDateTime.now(),
                LocalDateTime.now().plusHours(1), VALID_MEETING_REMARK)));

        // different remark -> returns false
        assertFalse(meeting.equals(new Meeting(START, END, "Different remark")));
    }
}
