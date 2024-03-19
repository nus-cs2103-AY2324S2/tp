package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalMeetings.MEETING_WITH_ALICE;
import static seedu.address.testutil.TypicalMeetings.MEETING_WITH_BENSON;
import static seedu.address.testutil.TypicalMeetings.MEETING_WITH_CARL;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.testutil.MeetingBuilder;

public class UniqueMeetingListTest {

    private final UniqueMeetingList uniqueMeetingList = new UniqueMeetingList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        assertFalse(uniqueMeetingList.contains(MEETING_WITH_ALICE));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        uniqueMeetingList.add(MEETING_WITH_ALICE);
        assertTrue(uniqueMeetingList.contains(MEETING_WITH_ALICE));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(MEETING_WITH_ALICE);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.add(MEETING_WITH_ALICE));
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(null, MEETING_WITH_ALICE));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(MEETING_WITH_ALICE, null));
    }

    @Test
    public void setMeeting_targetMeetingNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.setMeeting(MEETING_WITH_ALICE,
                MEETING_WITH_ALICE));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        uniqueMeetingList.add(MEETING_WITH_ALICE);
        uniqueMeetingList.setMeeting(MEETING_WITH_ALICE, MEETING_WITH_ALICE);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEETING_WITH_ALICE);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasSameIdentity_success() {
        uniqueMeetingList.add(MEETING_WITH_ALICE);
        Meeting editedAlice = new MeetingBuilder(MEETING_WITH_ALICE)
                .withDescription("Updated Description")
                .build();
        uniqueMeetingList.setMeeting(MEETING_WITH_ALICE, editedAlice);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedAlice);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasDifferentIdentity_success() {
        uniqueMeetingList.add(MEETING_WITH_ALICE);
        uniqueMeetingList.setMeeting(MEETING_WITH_ALICE, MEETING_WITH_BENSON);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEETING_WITH_BENSON);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasNonUniqueIdentity_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(MEETING_WITH_ALICE);
        uniqueMeetingList.add(MEETING_WITH_BENSON);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeeting(MEETING_WITH_ALICE,
                MEETING_WITH_BENSON));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.remove(MEETING_WITH_ALICE));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        uniqueMeetingList.add(MEETING_WITH_ALICE);
        uniqueMeetingList.remove(MEETING_WITH_ALICE);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullUniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_uniqueMeetingList_replacesOwnListWithProvidedUniqueMeetingList() {
        uniqueMeetingList.add(MEETING_WITH_ALICE);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEETING_WITH_BENSON);
        uniqueMeetingList.setMeetings(expectedUniqueMeetingList);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        uniqueMeetingList.add(MEETING_WITH_ALICE);
        List<Meeting> meetingList = Arrays.asList(MEETING_WITH_BENSON, MEETING_WITH_CARL);
        uniqueMeetingList.setMeetings(meetingList);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEETING_WITH_BENSON);
        expectedUniqueMeetingList.add(MEETING_WITH_CARL);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_listWithDuplicateMeetings_throwsDuplicateMeetingException() {
        List<Meeting> listWithDuplicateMeetings = Arrays.asList(MEETING_WITH_ALICE, MEETING_WITH_ALICE);
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
