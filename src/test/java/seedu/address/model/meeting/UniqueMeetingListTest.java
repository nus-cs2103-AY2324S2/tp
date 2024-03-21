package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.TypicalMeetings.ALICE_WITH_MEETING;
import static seedu.address.testutil.TypicalMeetings.ALICE_WITH_MEETING;
import static seedu.address.testutil.TypicalMeetings.BENSON_WITH_MEETING;
import static seedu.address.testutil.TypicalMeetings.CARL_WITH_MEETING;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;

public class UniqueMeetingListTest {

    private final UniqueMeetingList uniqueMeetingList = new UniqueMeetingList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        assertFalse(uniqueMeetingList.contains(ALICE_WITH_MEETING.getMeetings().get(0)));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        uniqueMeetingList.add(ALICE_WITH_MEETING.getMeetings().get(0));
        assertTrue(uniqueMeetingList.contains(ALICE_WITH_MEETING.getMeetings().get(0)));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(ALICE_WITH_MEETING.getMeetings().get(0));
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList
                .add(ALICE_WITH_MEETING.getMeetings().get(0)));
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(null,
                ALICE_WITH_MEETING.getMeetings().get(0)));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(ALICE_WITH_MEETING
                .getMeetings().get(0), null));
    }

    @Test
    public void setMeeting_targetMeetingNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.setMeeting(ALICE_WITH_MEETING
                        .getMeetings().get(0),
                        ALICE_WITH_MEETING.getMeetings().get(0)));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        uniqueMeetingList.add(ALICE_WITH_MEETING.getMeetings().get(0));
        uniqueMeetingList.setMeeting(ALICE_WITH_MEETING.getMeetings().get(0), ALICE_WITH_MEETING.getMeetings().get(0));
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(ALICE_WITH_MEETING.getMeetings().get(0));
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasSameIdentity_success() {
        uniqueMeetingList.add(ALICE_WITH_MEETING.getMeetings().get(0));
        Person editedAlice = new MeetingBuilder(ALICE_WITH_MEETING.getMeetings().get(0))
                .withDescription("Updated Description")
                .build();
        uniqueMeetingList.setMeeting(ALICE_WITH_MEETING.getMeetings().get(0), editedAlice.getMeetings().get(0));
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedAlice.getMeetings().get(0));
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasDifferentIdentity_success() {
        uniqueMeetingList.add(ALICE_WITH_MEETING.getMeetings().get(0));
        uniqueMeetingList.setMeeting(ALICE_WITH_MEETING.getMeetings().get(0), BENSON_WITH_MEETING.getMeetings().get(0));
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(BENSON_WITH_MEETING.getMeetings().get(0));
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasNonUniqueIdentity_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(ALICE_WITH_MEETING.getMeetings().get(0));
        uniqueMeetingList.add(BENSON_WITH_MEETING.getMeetings().get(0));
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeeting(
                ALICE_WITH_MEETING.getMeetings().get(0),
                BENSON_WITH_MEETING.getMeetings().get(0)));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.remove(
                ALICE_WITH_MEETING.getMeetings().get(0)));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        uniqueMeetingList.add(ALICE_WITH_MEETING.getMeetings().get(0));
        uniqueMeetingList.remove(ALICE_WITH_MEETING.getMeetings().get(0));
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullUniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_uniqueMeetingList_replacesOwnListWithProvidedUniqueMeetingList() {
        uniqueMeetingList.add(ALICE_WITH_MEETING.getMeetings().get(0));
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(BENSON_WITH_MEETING.getMeetings().get(0));
        uniqueMeetingList.setMeetings(expectedUniqueMeetingList);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        uniqueMeetingList.add(ALICE_WITH_MEETING.getMeetings().get(0));
        List<Meeting> meetingList = Arrays.asList(BENSON_WITH_MEETING.getMeetings().get(0),
                CARL_WITH_MEETING.getMeetings().get(0));
        uniqueMeetingList.setMeetings(meetingList);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(BENSON_WITH_MEETING.getMeetings().get(0));
        expectedUniqueMeetingList.add(CARL_WITH_MEETING.getMeetings().get(0));
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_listWithDuplicateMeetings_throwsDuplicateMeetingException() {
        List<Meeting> listWithDuplicateMeetings = Arrays.asList(ALICE_WITH_MEETING.getMeetings().get(0),
                ALICE_WITH_MEETING.getMeetings().get(0));
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeetings(listWithDuplicateMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueMeetingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueMeetingList.asUnmodifiableObservableList().toString(), uniqueMeetingList.toString());
    }
}
