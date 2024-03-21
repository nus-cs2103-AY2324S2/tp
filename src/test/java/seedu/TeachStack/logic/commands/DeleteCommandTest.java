package seedu.TeachStack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.TeachStack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.TeachStack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.TeachStack.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.TeachStack.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.TeachStack.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.TeachStack.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.TeachStack.testutil.TypicalStudentIds.ID_FIRST_PERSON;
import static seedu.TeachStack.testutil.TypicalStudentIds.ID_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.TeachStack.logic.Messages;
import seedu.TeachStack.model.Model;
import seedu.TeachStack.model.ModelManager;
import seedu.TeachStack.model.UserPrefs;
import seedu.TeachStack.model.person.Person;
import seedu.TeachStack.model.person.StudentId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        StudentId id = personToDelete.getStudentId();
        DeleteCommand deleteCommand = new DeleteCommand(id);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        StudentId outOfBoundId = new StudentId("A9999999Z");
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundId);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_DISPLAYED_STUDENT_ID);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        StudentId id = personToDelete.getStudentId();

        DeleteCommand deleteCommand = new DeleteCommand(id);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        StudentId outOfBoundId = personToDelete.getStudentId();

        // ensures that outOfBoundId is still in bounds of address book list
        assertTrue(model.getAddressBook().getPersonList().stream()
                .filter(person -> person.getStudentId().equals(outOfBoundId)).toArray().length == 1);

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundId);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(ID_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(ID_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(ID_FIRST_PERSON);
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
        StudentId targetId = new StudentId("A0123456A");
        DeleteCommand deleteCommand = new DeleteCommand(targetId);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetId=" + targetId + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
