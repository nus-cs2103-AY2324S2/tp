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
import static seedu.address.testutil.TypicalPersons.JAMAL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.ModelManager;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

import java.time.LocalDateTime;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private static final Meeting testMeeting = new Meeting("test meeting", LocalDateTime.now(), JAMAL);

    @Test
    public void execute_validClientIndexValidMeetingIndex() {
        int clientIndex = model.getAddressBook().getPersonList().indexOf(JAMAL);
        JAMAL.getMeetings().add(testMeeting);

        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(clientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(Index.fromZeroBased(JAMAL.getMeetings().size() - 1).getOneBased());
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(testClientIndex,
                testMeetingIndex);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_PERSON_SUCCESS, testMeetingIndex);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteSpecificMeetingForClient(testClientIndex, testMeetingIndex);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidClientIndexValidMeetingIndex_throwsCommandException() {
        int invalidClientIndex = model.getAddressBook().getPersonList().size();
        JAMAL.getMeetings().add(testMeeting);

        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(invalidClientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(Index.fromZeroBased(JAMAL.getMeetings().size() - 1).getOneBased());

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(testClientIndex, testMeetingIndex);

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(deleteMeetingCommand, model, expectedMessage);
    }

    @Test
    public void execute_validClientIndexInvalidMeetingIndex_throwsCommandException() {
        // Do not add any meeting to JAMAL to ensure the meeting index is invalid
        int clientIndex = model.getAddressBook().getPersonList().indexOf(JAMAL);
    
        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(clientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(Index.fromZeroBased(JAMAL.getMeetings().size()).getOneBased());
    
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(testClientIndex, testMeetingIndex);

        String expectedMessage = Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
        assertCommandFailure(deleteMeetingCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidClientIndexInvalidMeetingIndex_throwsCommandException() {
        // No meeting added to ensure meeting index is also invalid
        int invalidClientIndex = model.getAddressBook().getPersonList().size();
    
        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(invalidClientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(Index.fromZeroBased(JAMAL.getMeetings().size()).getOneBased());
    
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(testClientIndex, testMeetingIndex);

        // invalid client index should be caught before invalid meeting index
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(deleteMeetingCommand, model, expectedMessage);
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
        String expected = DeleteMeetingCommand.class.getCanonicalName() + "{targetClientIndex=" + targetClientIndex + "}";
        assertEquals(expected, deleteMeetingCommand.toString());
    }
}
