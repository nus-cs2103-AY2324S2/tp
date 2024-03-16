package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNusIdUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        NusId nusId = new NusId("E9846510"); // This is the supposed NusId of the first person in the address list
        DeleteCommand deleteCommand = new DeleteCommand(nusId);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNusIdUnfilteredList_throwsCommandException() {
        NusId testNusId = new NusId("E1234567");
        DeleteCommand deleteCommand = new DeleteCommand(testNusId);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NON_EXISTENT_PERSON);
    }

    @Test
    public void execute_validNusIdFilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        NusId nusId = new NusId("E9846510");
        DeleteCommand deleteCommand = new DeleteCommand(nusId);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNusIdFilteredList_throwsCommandException() {
        NusId testNusId = new NusId("E1234567");
        DeleteCommand deleteCommand = new DeleteCommand(testNusId);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NON_EXISTENT_PERSON);
    }

    @Test
    public void equals() {
        NusId nusIdForFirstDeleteCommand = new NusId("E1234567");
        NusId nusIdForSecondDeleteCommand = new NusId("E2345678");
        DeleteCommand deleteFirstCommand = new DeleteCommand(nusIdForFirstDeleteCommand);
        DeleteCommand deleteSecondCommand = new DeleteCommand(nusIdForSecondDeleteCommand);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(new NusId("E1234567"));
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
        //Index targetIndex = Index.fromOneBased(1);
        //DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        NusId testNusId = new NusId("E1234567");
        DeleteCommand deleteCommand = new DeleteCommand(testNusId);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetnusId=" + testNusId.toString() + "}";
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
