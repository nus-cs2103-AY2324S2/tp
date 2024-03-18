package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.TypicalMeetings;

public class MeetingTest {

    @Test
    public void isSameMeeting() {
        // same object -> returns true
        assertTrue(TypicalMeetings.MEETING_WITH_ALICE.isSameMeeting(TypicalMeetings.MEETING_WITH_ALICE));

        // different meeting -> returns false
        assertFalse(TypicalMeetings.MEETING_WITH_ALICE.isSameMeeting(TypicalMeetings.MEETING_WITH_BENSON));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Meeting meetingWithAliceCopy = new MeetingBuilder(TypicalMeetings.MEETING_WITH_ALICE).build();
        assertTrue(TypicalMeetings.MEETING_WITH_ALICE.equals(meetingWithAliceCopy));

        // same object -> returns true
        assertTrue(TypicalMeetings.MEETING_WITH_ALICE.equals(TypicalMeetings.MEETING_WITH_ALICE));

        // null -> returns false
        assertFalse(TypicalMeetings.MEETING_WITH_ALICE.equals(null));

        // different type -> returns false
        assertFalse(TypicalMeetings.MEETING_WITH_ALICE.equals(5));

        // different meeting -> returns false
        assertFalse(TypicalMeetings.MEETING_WITH_ALICE.equals(TypicalMeetings.MEETING_WITH_BENSON));
    }

    @Test
    public void toStringMethod() {
        String expected = Meeting.class.getCanonicalName()
                + "{description=Financial Aid Application Review, dateTime=2024-01-01T09:00, "
                + "client=Alice Pauline}";
        assertEquals(expected, TypicalMeetings.MEETING_WITH_ALICE.toString());
    }
}
