package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class MeetingTest {
    private static final LocalDateTime START = LocalDateTime.of(2024, 1, 1, 12, 0);
    private static final LocalDateTime END = LocalDateTime.of(2024, 1, 1, 13, 0);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Meeting(null, null));
        assertThrows(NullPointerException.class, () -> new Meeting(START, null));
        assertThrows(NullPointerException.class, () -> new Meeting(null, END));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Meeting(END, START));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Meeting.isValidDateTime(null, null));
        assertThrows(NullPointerException.class, () -> Meeting.isValidDateTime(START, null));
        assertThrows(NullPointerException.class, () -> Meeting.isValidDateTime(null, END));

        // invalid phone numbers
        assertFalse(Meeting.isValidDateTime(END, START));

        // valid phone numbers
        assertTrue(Meeting.isValidDateTime(START, END));
    }

    @Test
    public void equals() {
        Meeting meeting = new Meeting(START, END);

        // same values -> returns true
        assertTrue(meeting.equals(new Meeting(START, END)));

        // same object -> returns true
        assertTrue(meeting.equals(meeting));

        // null -> returns false
        assertFalse(meeting.equals(null));

        // different types -> returns false
        assertFalse(meeting.equals(LocalDateTime.now()));

        // different values -> returns false
        assertFalse(meeting.equals(new Meeting(LocalDateTime.now(), LocalDateTime.now().plusHours(1))));
    }
}
