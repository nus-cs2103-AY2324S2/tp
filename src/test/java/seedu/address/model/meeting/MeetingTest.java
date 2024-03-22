package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalMeetings;

public class MeetingTest {

    @Test
    public void isSameMeeting() {
        // same object -> returns true
        assertTrue(TypicalMeetings.ALICE_WITH_MEETING.hasExistingMeeting(TypicalMeetings
                .ALICE_WITH_MEETING.getMeetings().get(0)));

        // different meeting -> returns false
        assertFalse(TypicalMeetings.ALICE_WITH_MEETING.hasExistingMeeting(TypicalMeetings
                .BENSON_WITH_MEETING.getMeetings().get(0)));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Meeting meetingWithAliceCopy = TypicalMeetings.ALICE_WITH_MEETING.getMeetings().get(0);

        assertTrue(TypicalMeetings.ALICE_WITH_MEETING.getMeetings().get(0).equals(meetingWithAliceCopy));

        // same object -> returns true
        assertTrue(TypicalMeetings.ALICE_WITH_MEETING.getMeetings().get(0)
                .equals(TypicalMeetings.ALICE_WITH_MEETING.getMeetings().get(0)));

        // null -> returns false
        assertFalse(TypicalMeetings.ALICE_WITH_MEETING.getMeetings().get(0).equals(null));

        // different type -> returns false
        assertFalse(TypicalMeetings.ALICE_WITH_MEETING.getMeetings().get(0).equals(5));

        // different meeting -> returns false
        assertFalse(TypicalMeetings.ALICE_WITH_MEETING.getMeetings().get(0)
                .equals(TypicalMeetings.BENSON_WITH_MEETING));
    }

    @Test
    public void toStringMethod() {
        String expected = Meeting.class.getCanonicalName()
                + "{description=Financial Aid Application Review, dateTime=2030-01-01T12:00, "
                + "client=Alice Pauline}";
        assertEquals(expected, TypicalMeetings.ALICE_WITH_MEETING.getMeetings().get(0).toString());
    }
}
