package staffconnect.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class MeetingTest {
    private static final Description testDescription = new Description("Valid description");
    private static final MeetDateTime testDate = new MeetDateTime("12/04/2023 12:00");
    private static final Description otherDescription = new Description("Another valid description");
    private static final MeetDateTime otherDate = new MeetDateTime("13/04/2023 13:00");

    @Test
    public void equals() {

        Meeting testMeeting = new Meeting(testDescription, testDate);
        Meeting diffMeetingDescription = new Meeting(otherDescription, testDate);
        Meeting diffMeetingTime = new Meeting(testDescription, otherDate);

        // same values -> returns true
        assertEquals(testMeeting, new Meeting(new Description("Valid description"),
                                              new MeetDateTime("12/04/2023 12:00")));

        // same object -> returns true
        assertEquals(testMeeting, testMeeting);

        // null -> returns false
        assertNotEquals(null, testMeeting);

        // different type -> returns false
        assertNotEquals(5, testMeeting);

        // different description -> returns false
        assertNotEquals(testMeeting, diffMeetingDescription);

        // different time -> returns false
        assertNotEquals(testMeeting, diffMeetingTime);
    }

    @Test
    public void asSymmetricHashcode() {
        Meeting first = new Meeting(new Description("test"), new MeetDateTime("12/04/2023 12:00"));
        Meeting second = new Meeting(new Description("test"), new MeetDateTime("12/04/2023 12:00"));
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void toStringMethod() {
        Meeting testMeeting = new Meeting(testDescription, testDate);
        String expected = testDate + ":" + testDescription;
        assertEquals(expected, testMeeting.toString());
    }


}
