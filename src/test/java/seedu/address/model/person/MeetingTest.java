package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;

public class MeetingTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Meeting(null));
    }

    @Test
    public void constructor_invalidMeeting_throwsDateTimeException() {
        String invalidMeeting = "";
        assertThrows(DateTimeException.class, () -> new Meeting(invalidMeeting));
    }

    @Test
    public void isValidMeeting() {
        // null meeting
        assertThrows(NullPointerException.class, () -> Meeting.isValidMeeting(null));

        // missing parts
        assertFalse(Meeting.isValidMeeting("2024")); // missing MM-DD
        assertFalse(Meeting.isValidMeeting("2024-01")); // missing DD
        assertFalse(Meeting.isValidMeeting("01-12")); // missing YYYY
    }

    @Test
    public void equals() {
        Meeting meeting = new Meeting("2024-01-01");

        // same values -> returns true
        assertTrue(meeting.equals(new Meeting("2024-01-01")));

        // same object -> returns true
        assertTrue(meeting.equals(meeting));

        // null -> returns false
        assertFalse(meeting.equals(null));

        // different types -> returns false
        assertFalse(meeting.equals(5.0f));

        // different values -> returns false
        assertFalse(meeting.equals(new Meeting("2024-01-02")));
    }
}
