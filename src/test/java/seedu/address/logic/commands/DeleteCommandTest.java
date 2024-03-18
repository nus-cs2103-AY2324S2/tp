package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonWithName;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.messages.DeleteMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = ALICE;
        DeleteCommand deleteCommand = new DeleteCommand(ALICE.getName());

        String expectedMessage = String.format(DeleteMessages.MESSAGE_DELETE_PERSON_SUCCESS,
                DeleteMessages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNameFilteredList_success() {
        showPersonWithName(model, ALICE.getName());

        Person personToDelete = model.findByName(ALICE.getName());
        DeleteCommand deleteCommand = new DeleteCommand(ALICE.getName());

        String expectedMessage = String.format(DeleteMessages.MESSAGE_DELETE_PERSON_SUCCESS,
                DeleteMessages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameFilteredList_throwsCommandException() {
        showPersonWithName(model, ALICE.getName());

        Name invalidName = BENSON.getName();

        // ensures that the invalid name is not equal to "Alice Pauline"
        assertTrue(!invalidName.equals(ALICE.getName()));

        DeleteCommand deleteCommand = new DeleteCommand(invalidName);

        assertCommandFailure(deleteCommand, model, DeleteMessages.MESSAGE_DELETE_NAME_NOT_FOUND);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(ALICE.getName());
        DeleteCommand deleteSecondCommand = new DeleteCommand(BENSON.getName());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(ALICE.getName());
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
        Name targetName = ALICE.getName();
        DeleteCommand deleteCommand = new DeleteCommand(targetName);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetName=" + targetName + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    @Test
    public void deleteFormat() {
        // Normal Person
        String testNormalString = DeleteMessages.format(ALICE);
        String expectedNormalString = "Other Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = DeleteMessages.format(ALICESTAFF);
        String expectedStaffString = "Pooch Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = DeleteMessages.format(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintenance Crew Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = DeleteMessages.format(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
