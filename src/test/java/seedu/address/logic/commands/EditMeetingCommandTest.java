package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeetings.JAMAL_WITH_MEETING;
import static seedu.address.testutil.TypicalPersons.JAMAL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMeetingCommand.
 */
public class EditMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validClientIndexAndMeetingIndex_success() {
        Meeting editedMeeting = new MeetingBuilder().withClient(JAMAL).withDateTime(VALID_DATETIME)
                .withDescription(VALID_DESCRIPTION).buildMeeting();
        model.addPerson(JAMAL_WITH_MEETING);
        model.addMeeting(JAMAL_WITH_MEETING.getMeetings().get(0));
        int clientIndex = model.getAddressBook().getPersonList().indexOf(JAMAL_WITH_MEETING);
        Index targetClientIndex = Index.fromZeroBased(clientIndex);
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(targetClientIndex, descriptor,
                INDEX_FIRST_MEETING);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someMeetingFieldsSpecified_success() {
        Meeting editedMeeting = new MeetingBuilder().withClient(JAMAL).buildMeeting();
        model.addPerson(JAMAL_WITH_MEETING);
        model.addMeeting(JAMAL_WITH_MEETING.getMeetings().get(0));
        int clientIndex = model.getAddressBook().getPersonList().indexOf(JAMAL_WITH_MEETING);
        Index targetClientIndex = Index.fromZeroBased(clientIndex);
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(targetClientIndex, descriptor,
                INDEX_FIRST_MEETING);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMeetingFieldSpecified_success() {
        Meeting targetMeeting = new MeetingBuilder().withClient(JAMAL).buildMeeting();
        JAMAL_WITH_MEETING.getMeetings().add(targetMeeting);
        model.addPerson(JAMAL_WITH_MEETING);
        model.addMeeting(JAMAL_WITH_MEETING.getMeetings().get(0));
        Meeting editedMeeting = new MeetingBuilder().withClient(JAMAL).withDescription("test").buildMeeting();
        int clientIndex = model.getAddressBook().getPersonList().indexOf(JAMAL_WITH_MEETING);
        Index targetClientIndex = Index.fromZeroBased(clientIndex);
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(targetClientIndex, descriptor,
                INDEX_FIRST_MEETING);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidClientIndexValidMeetingIndex_throwsCommandException() {
        Meeting testMeeting = new MeetingBuilder().withClient(JAMAL).buildMeeting();
        JAMAL_WITH_MEETING.getMeetings().add(testMeeting);
        model.addPerson(JAMAL_WITH_MEETING);
        int invalidClientIndex = model.getAddressBook().getPersonList().size();

        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(invalidClientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(INDEX_FIRST_MEETING.getOneBased());
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(testMeeting).build();

        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(testClientIndex, descriptor, testMeetingIndex);

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(editMeetingCommand, model, expectedMessage);
        model.deletePerson(JAMAL_WITH_MEETING);
    }

    @Test
    public void execute_validClientIndexInvalidMeetingIndex_throwsCommandException() {
        Meeting testMeeting = new MeetingBuilder().withClient(JAMAL).buildMeeting();
        JAMAL_WITH_MEETING.getMeetings().add(testMeeting);
        model.addPerson(JAMAL_WITH_MEETING);
        int validClientIndex = model.getAddressBook().getPersonList().size() - 1;
        int invalidMeetingIndex = JAMAL_WITH_MEETING.getMeetings().size();

        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(validClientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(Index.fromZeroBased(invalidMeetingIndex).getOneBased());
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(testMeeting).build();

        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(testClientIndex, descriptor, testMeetingIndex);

        String expectedMessage = Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
        assertCommandFailure(editMeetingCommand, model, expectedMessage);
        model.deletePerson(JAMAL_WITH_MEETING);
    }

    @Test
    public void execute_invalidClientIndexInvalidMeetingIndex_throwsCommandException() {
        Meeting testMeeting = new MeetingBuilder().withClient(JAMAL).buildMeeting();
        JAMAL_WITH_MEETING.getMeetings().add(testMeeting);
        model.addPerson(JAMAL_WITH_MEETING);
        int validClientIndex = model.getAddressBook().getPersonList().size();
        int invalidMeetingIndex = JAMAL_WITH_MEETING.getMeetings().size();

        Index testClientIndex = Index.fromOneBased(Index.fromZeroBased(validClientIndex).getOneBased());
        Index testMeetingIndex = Index.fromOneBased(Index.fromZeroBased(invalidMeetingIndex).getOneBased());
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(testMeeting).build();

        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(testClientIndex, descriptor, testMeetingIndex);

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(editMeetingCommand, model, expectedMessage);
        model.deletePerson(JAMAL_WITH_MEETING);
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        Meeting testMeeting = new MeetingBuilder().withClient(JAMAL).buildMeeting();
        JAMAL_WITH_MEETING.getMeetings().add(testMeeting);
        model.addPerson(JAMAL_WITH_MEETING);
        model.addMeeting(testMeeting);
        int clientIndex = model.getAddressBook().getPersonList().indexOf(JAMAL_WITH_MEETING);
        int meetingIndex = JAMAL_WITH_MEETING.getMeetings().indexOf(testMeeting);
        Index targetClientIndex = Index.fromZeroBased(clientIndex);
        Index targetMeetingIndex = Index.fromZeroBased(meetingIndex);
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(testMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(targetClientIndex, descriptor,
                targetMeetingIndex);

        System.out.println(JAMAL_WITH_MEETING.getMeetings());
        System.out.println(meetingIndex);
        System.out.println(testMeeting);

        String expectedMessage = EditMeetingCommand.MESSAGE_DUPLICATE_MEETING;

        assertCommandFailure(editMeetingCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final EditMeetingCommand standardCommand = new EditMeetingCommand(INDEX_FIRST_PERSON,
                new EditMeetingCommand.EditMeetingDescriptor(), INDEX_FIRST_MEETING);

        // same values -> returns true
        EditMeetingCommand.EditMeetingDescriptor copyDescriptor = new EditMeetingCommand.EditMeetingDescriptor();
        EditMeetingCommand commandWithSameValues = new EditMeetingCommand(INDEX_FIRST_PERSON,
                copyDescriptor, INDEX_FIRST_MEETING);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_SECOND_PERSON,
                new EditMeetingCommand.EditMeetingDescriptor(), INDEX_FIRST_MEETING)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_FIRST_PERSON,
                new EditMeetingCommand.EditMeetingDescriptor(), INDEX_SECOND_MEETING)));
    }

    @Test
    public void toStringMethod() {
        Index clientIndex = Index.fromOneBased(1);
        Index meetingIndex = Index.fromOneBased(2);
        EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor = new EditMeetingCommand.EditMeetingDescriptor();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(clientIndex,
                editMeetingDescriptor, meetingIndex);
        String expected = EditMeetingCommand.class.getCanonicalName() + "{clientIndex="
                + clientIndex + ", editMeetingDescriptor="
                + editMeetingDescriptor + ", meetingIndex="
                + meetingIndex + "}";
        assertEquals(expected, editMeetingCommand.toString());
    }

}
