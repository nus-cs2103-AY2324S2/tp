package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMeetingCommand.
 */
public class EditMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /*
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedMeeting = new MeetingBuilder().build();
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_PERSON, descriptor,
                INDEX_SECOND_MEETING);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }
    */
    /*
    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMeeting = Index.fromOneBased(model.getFilteredMeetingList().size());
        Meeting lastMeeting = model.getFilteredMeetingList().get(indexLastMeeting.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedMeeting = meetingInList.withDescription(VALID_NAME_BOB).withDateTime(VALID_DATETIME_BOB)
                .withClient(JAMAL).build();

        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withDescription(VALID_NAME_BOB)
                .withDateTime(VALID_DATETIME_BOB)
                .withClient(JAMAL).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(indexLastMeeting, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    */
    /*
    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_PERSON,
                new EditMeetingCommand.EditMeetingDescriptor(), INDEX_FIRST_MEETING);
        Meeting editedMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }
    */

    /*
    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    */
    /*
    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(firstMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_SECOND_PERSON,
                descriptor, INDEX_SECOND_MEETING);

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }
    */
    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
    /*
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
    */

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
