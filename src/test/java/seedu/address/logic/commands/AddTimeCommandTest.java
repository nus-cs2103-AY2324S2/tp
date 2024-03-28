package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ADD_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ADD_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FREE_TIME_TAG_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddTimeCommand.EditPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddPersonFreeTimeDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddTimeCommand.
 */
public class AddTimeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new AddPersonFreeTimeDescriptorBuilder()
                .withFreeTimeTags(VALID_FREE_TIME_TAG_BOB).build();
        AddTimeCommand addTimeCommand = new AddTimeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(addTimeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        AddTimeCommand addTimeCommand = new AddTimeCommand(outOfBoundIndex,
                new AddPersonFreeTimeDescriptorBuilder().withFreeTimeTags(VALID_FREE_TIME_TAG_BOB).build());

        assertCommandFailure(addTimeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where no free time was specified
     */
    @Test
    public void execute_emptyFreeTime_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_FIRST_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddTimeCommand addTimeCommand = new AddTimeCommand(outOfBoundIndex,
                new AddPersonFreeTimeDescriptorBuilder().build());

        assertCommandFailure(addTimeCommand, model, Messages.MESSAGE_NO_FREETIME_SPECIFIED);
    }

    /**
     * Edit filtered list where valid free time is specified
     */
    @Test
    public void execute_newFreeTime_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_FIRST_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddTimeCommand addTimeCommand = new AddTimeCommand(outOfBoundIndex,
                new AddPersonFreeTimeDescriptorBuilder().withFreeTimeTags(VALID_FREE_TIME_TAG_BOB).build());


        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(AddTimeCommand.MESSAGE_ADD_FREETIME_SUCCESS,
                Messages.format(editedPerson));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(addTimeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final AddTimeCommand standardCommand = new AddTimeCommand(INDEX_FIRST_PERSON, DESC_ADD_TIME_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_ADD_TIME_AMY);
        AddTimeCommand commandWithSameValues = new AddTimeCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddTimeCommand(INDEX_SECOND_PERSON, DESC_ADD_TIME_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddTimeCommand(INDEX_FIRST_PERSON, DESC_ADD_TIME_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        AddTimeCommand addTimeCommand = new AddTimeCommand(index, editPersonDescriptor);
        String expected = AddTimeCommand.class.getCanonicalName() + "{index=" + index + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, addTimeCommand.toString());
    }

}
