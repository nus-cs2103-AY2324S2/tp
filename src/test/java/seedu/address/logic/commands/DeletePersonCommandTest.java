package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletePersonCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeletePersonCommand(Index.fromZeroBased(0)).execute(null));
    }

    @Test
    public void deleteFromGlobal_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_GLOBAL_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void deleteFromGlobal_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(outOfBoundIndex);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void deleteFromGlobal_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_GLOBAL_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void deleteFromGlobal_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(outOfBoundIndex);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void deleteFromEvent_validIndex_success() throws Exception {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setEventBook(getTypicalEventBook());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(Index.fromZeroBased(0));

        Event event = model.getEventBook().getEventList().get(0);
        Person personToDelete = getTypicalAddressBook().getPersonList().get(0);
        // Select an event first
        model.selectEvent(event);
        // Add person
        event.addPerson(personToDelete);
        model.selectEvent(event);

        deletePersonCommand.execute(model);

        assertFalse(model.isPersonInSelectedEvent(personToDelete));
    }

    @Test
    public void deleteFromEvent_invalidIndex_throwsCommandException() {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setEventBook(getTypicalEventBook());
        Event event = model.getEventBook().getEventList().get(0);
        model.selectEvent(event);

        assertThrows(CommandException.class, () ->
                new DeletePersonCommand(Index.fromZeroBased(100)).execute(model));
    }

    @Test
    public void deleteFromEvent_noEventSelected_throwsCommandException() {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setEventBook(getTypicalEventBook());

        assertThrows(CommandException.class, () ->
                new DeletePersonCommand(Index.fromZeroBased(100)).deleteFromEvent(model));
    }

    @Test
    public void equals() {
        DeletePersonCommand deleteFirstPersonCommand = new DeletePersonCommand(INDEX_FIRST_PERSON);
        DeletePersonCommand deleteSecondPersonCommand = new DeletePersonCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstPersonCommand.equals(deleteFirstPersonCommand));

        // same values -> returns true
        DeletePersonCommand deleteFirstPersonCommandCopy = new DeletePersonCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstPersonCommandCopy.equals(deleteFirstPersonCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstPersonCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstPersonCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstPersonCommand.equals(deleteSecondPersonCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(targetIndex);
        String expected = DeletePersonCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deletePersonCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
