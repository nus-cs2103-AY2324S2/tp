package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_invalidUuidUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(null);
        assertThrows(NullPointerException.class, () -> deleteCommand.execute(model));
    }

    @Test
    public void execute_validUuid_success() {
        Person personToDelete = model.getFilteredPersonList().get(0);
        String uuidToDelete = personToDelete.getUuid().toString().substring(36 - 4);
        DeleteCommand deleteCommand = new DeleteCommand(uuidToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);

        // Now, the person has been deleted from the expectedModel.
        // So, trying to delete the same person again should throw a NullPointerException.
        assertThrows(CommandException.class, () -> deleteCommand.execute(expectedModel));
    }

    @Test
    public void execute_nonRealUuidFilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand("12345");

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Person firstPerson = model.getFilteredPersonList().get(0);
        Person secondPerson = model.getFilteredPersonList().get(1);
        DeleteCommand deleteFirstCommand = new DeleteCommand(firstPerson.getUuid().toString().substring(36 - 4));
        DeleteCommand deleteSecondCommand = new DeleteCommand(secondPerson.getUuid().toString().substring(36 - 4));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(firstPerson.getUuid().toString().substring(36 - 4));
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
        Person targetPerson = model.getFilteredPersonList().get(0);
        DeleteCommand deleteCommand = new DeleteCommand(targetPerson.getUuid().toString());
        String expected = DeleteCommand.class.getCanonicalName() + "{target=" + targetPerson.getUuid() + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    @Test
    public void execute_validUuidUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(0);
        String uuidToDelete = personToDelete.getUuid().toString().substring(36 - 4);
        DeleteCommand deleteCommand = new DeleteCommand(uuidToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }
}
