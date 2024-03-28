package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.CHAD;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.JAMAL;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final Person ALICE_WITH_MEETING = new MeetingBuilder()
        .withClient(ALICE)
        .withDescription("Financial Aid Application Review")
        .withDateTime("01-01-2030 12:00").build();
    public static final Person BENSON_WITH_MEETING = new MeetingBuilder()
        .withClient(BENSON)
        .withDescription("Financial Aid Eligibility Check")
        .withDateTime("01-11-2030 11:00").build();
    public static final Person CARL_WITH_MEETING = new MeetingBuilder()
        .withClient(CARL)
        .withDescription("Financial Aid Document Submission")
        .withDateTime("05-02-2030 13:00").build();
    public static final Person DANIEL_WITH_MEETING = new MeetingBuilder()
        .withClient(DANIEL)
        .withDescription("Financial Aid Interview")
        .withDateTime("05-02-2030 15:00").build();
    public static final Person ELLE_WITH_MEETING = new MeetingBuilder()
        .withClient(ELLE)
        .withDescription("Financial Aid Consultation")
        .withDateTime("05-02-2029 12:00").build();
    public static final Person FIONA_WITH_MEETING = new MeetingBuilder()
        .withClient(FIONA)
        .withDescription("Financial Aid Application Review")
        .withDateTime("05-02-2028 11:00").build();
    public static final Person GEORGE_WITH_MEETING = new MeetingBuilder()
        .withClient(GEORGE)
        .withDescription("Financial Aid Document Submission")
        .withDateTime("05-02-2029 17:00").build();

    public static final Person JAMAL_WITH_MEETING = new MeetingBuilder()
        .withClient(JAMAL)
        .withDescription("Financial Support")
        .withDateTime("05-02-2028 11:00").build();

    public static final Person CHAD_WITH_MEETING = new MeetingBuilder()
        .withClient(CHAD)
        .withDescription("Financial Aid Application Review")
        .withDateTime("05-02-2028 11:00").build();

    private TypicalMeetings() {} // prevents instantiation

    public static ArrayList<Person> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(ALICE_WITH_MEETING, BENSON_WITH_MEETING, CARL_WITH_MEETING,
            DANIEL_WITH_MEETING, ELLE_WITH_MEETING, FIONA_WITH_MEETING, GEORGE_WITH_MEETING,
            JAMAL_WITH_MEETING, CHAD_WITH_MEETING));
    }
}
