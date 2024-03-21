package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeetings.JAMAL_WITH_MEETING;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteMeetingCommandTest {

    static {
        System.out.println(JAMAL_WITH_MEETING.getMeetings().size());
    }
    private static final Meeting testMeeting = JAMAL_WITH_MEETING.getMeetings().get(0);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validClientIndexValidMeetingIndex() {
        System.out.println(JAMAL_WITH_MEETING.getMeetings());
        JAMAL_WITH_MEETING.getMeetings().add(testMeeting);
        System.out.println(JAMAL_WITH_MEETING.getMeetings().size());
        model.addPerson(JAMAL_WITH_MEETING);
        int clientIndex = model.getAddressBook().getPersonList().indexOf(JAMAL_WITH_MEETING);

        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(clientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(Index.fromZeroBased(
                JAMAL_WITH_MEETING.getMeetings().size() - 1).getOneBased());
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(testClientIndex,
                testMeetingIndex);

        String expectedMessage = "Meeting 2 deleted successfully ";

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidClientIndexValidMeetingIndex_throwsCommandException() {
        JAMAL_WITH_MEETING.getMeetings().add(testMeeting);
        model.addPerson(JAMAL_WITH_MEETING);
        int invalidClientIndex = model.getAddressBook().getPersonList().size();

        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(invalidClientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(Index.fromZeroBased(
                JAMAL_WITH_MEETING.getMeetings().size() - 1).getOneBased());

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(testClientIndex, testMeetingIndex);

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(deleteMeetingCommand, model, expectedMessage);
        model.deletePerson(JAMAL_WITH_MEETING);
    }

    @Test
    public void execute_validClientIndexInvalidMeetingIndex_throwsCommandException() {
        model.addPerson(JAMAL_WITH_MEETING);
        // Do not add any meeting to JAMAL_WITH_MEETING to ensure the meeting index is invalid
        int clientIndex = model.getAddressBook().getPersonList().indexOf(JAMAL_WITH_MEETING);
        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(clientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(Index.fromZeroBased(
                JAMAL_WITH_MEETING.getMeetings().size()).getOneBased());
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(testClientIndex, testMeetingIndex);
        System.out.println(JAMAL_WITH_MEETING.getMeetings());
        String expectedMessage = "Error: Meeting 3 not found";
        assertCommandFailure(deleteMeetingCommand, model, expectedMessage);
        model.deletePerson(JAMAL_WITH_MEETING);
    }

    @Test
    public void execute_invalidClientIndexInvalidMeetingIndex_throwsCommandException() {
        model.addPerson(JAMAL_WITH_MEETING);
        // No meeting added to ensure meeting index is also invalid
        int invalidClientIndex = model.getAddressBook().getPersonList().size();

        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(invalidClientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(Index.fromZeroBased(
                JAMAL_WITH_MEETING.getMeetings().size()).getOneBased());

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(testClientIndex, testMeetingIndex);

        // invalid client index should be caught before invalid meeting index
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(deleteMeetingCommand, model, expectedMessage);
        model.deletePerson(JAMAL_WITH_MEETING);
    }


    @Test
    public void equals() {
        DeleteMeetingCommand deleteFirstCommand = new DeleteMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING);
        DeleteMeetingCommand deleteSecondCommand = new DeleteMeetingCommand(INDEX_SECOND_PERSON, INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteMeetingCommand deleteFirstCommandCopy = new DeleteMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetClientIndex = Index.fromOneBased(1);
        Index targetMeetingIndex = Index.fromOneBased(1);
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(targetClientIndex, targetMeetingIndex);
        String expected = DeleteMeetingCommand.class.getCanonicalName()
            + "{clientIndex=" + targetClientIndex + ", meetingIndex=" + targetMeetingIndex + "}";
        assertEquals(expected, deleteMeetingCommand.toString());
    }
}
