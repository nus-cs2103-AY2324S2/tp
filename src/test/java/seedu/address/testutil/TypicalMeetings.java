package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.JAMAL;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final Meeting MEETING_WITH_ALICE = new MeetingBuilder()
        .withClient(ALICE)
        .withDescription("Financial Aid Application Review")
        .withDateTime("01-01-2024 09:00").build();
    public static final Meeting MEETING_WITH_BENSON = new MeetingBuilder()
        .withClient(BENSON)
        .withDescription("Financial Aid Eligibility Check")
        .withDateTime("01-11-2023 11:00").build();
    public static final Meeting MEETING_WITH_CARL = new MeetingBuilder()
        .withClient(CARL)
        .withDescription("Financial Aid Document Submission")
        .withDateTime("05-02-2024 13:00").build();
    public static final Meeting MEETING_WITH_DANIEL = new MeetingBuilder()
        .withClient(DANIEL)
        .withDescription("Financial Aid Interview")
        .withDateTime("05-02-2024 15:00").build();
    public static final Meeting MEETING_WITH_ELLE = new MeetingBuilder()
        .withClient(ELLE)
        .withDescription("Financial Aid Consultation")
        .withDateTime("05-02-2024 12:00").build();
    public static final Meeting MEETING_WITH_FIONA = new MeetingBuilder()
        .withClient(FIONA)
        .withDescription("Financial Aid Application Review")
        .withDateTime("05-02-2024 11:00").build();
    public static final Meeting MEETING_WITH_GEORGE = new MeetingBuilder()
        .withClient(GEORGE)
        .withDescription("Financial Aid Document Submission")
        .withDateTime("05-02-2024 17:00").build();

    public static final Meeting MEETING_WITH_JAMAL = new MeetingBuilder()
            .withClient(JAMAL)
            .withDescription("Financial Aid Document Submission")
            .withDateTime("05-02-2024 17:00").build();
    private TypicalMeetings() {} // prevents instantiation

    public static ArrayList<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(MEETING_WITH_ALICE, MEETING_WITH_BENSON, MEETING_WITH_CARL,
            MEETING_WITH_DANIEL, MEETING_WITH_ELLE, MEETING_WITH_FIONA, MEETING_WITH_GEORGE));
    }
}
