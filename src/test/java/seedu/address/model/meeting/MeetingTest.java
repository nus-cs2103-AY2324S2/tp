package seedu.address.model.meeting;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalMeetings;
import seedu.address.testutil.MeetingBuilder;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MeetingTest {

    @Test
    public void isSameMeeting() {
        // same object -> returns true
        assertTrue(TypicalMeetings.ALICE_WITH_MEETING.hasExistingMeeting(TypicalMeetings
                .ALICE_WITH_MEETING.getMeetings().get(0)));

        // null -> returns false
        assertFalse(TypicalMeetings.ALICE_WITH_MEETING.hasExistingMeeting(null));

        // description has trailing spaces, all other attributes same -> returns false
        Meeting editedMeeting = new MeetingBuilder(TypicalMeetings.ALICE_WITH_MEETING.getMeetings().get(0))
                .withDescription("Financial Aid Application Review ").build().getMeetings().get(1);
        TypicalMeetings.ALICE_WITH_MEETING.getMeetings().remove(1);
        assertFalse(TypicalMeetings.ALICE_WITH_MEETING.hasExistingMeeting(editedMeeting));

        // dateTime has trailing spaces, all other attributes same -> throws DateTimeParseException
        assertThrows(DateTimeParseException.class,
                () -> new MeetingBuilder(TypicalMeetings.ALICE_WITH_MEETING.getMeetings().get(0))
                .withDateTime("2030-01-01T12:00 ").build().getMeetings().get(0));

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
