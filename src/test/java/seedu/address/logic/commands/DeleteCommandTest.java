package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStartupAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STARTUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STARTUP;
import static seedu.address.testutil.TypicalStartups.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.startup.Startup;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Startup startupToDelete = model.getFilteredStartupList().get(INDEX_FIRST_STARTUP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_STARTUP);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STARTUP_SUCCESS,
                Messages.format(startupToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStartup(startupToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStartupList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_STARTUP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStartupAtIndex(model, INDEX_FIRST_STARTUP);

        Startup startupToDelete = model.getFilteredStartupList().get(INDEX_FIRST_STARTUP.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_STARTUP);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STARTUP_SUCCESS,
                Messages.format(startupToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStartup(startupToDelete);
        showNoStartup(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStartupAtIndex(model, INDEX_FIRST_STARTUP);

        Index outOfBoundIndex = INDEX_SECOND_STARTUP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStartupList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_STARTUP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_STARTUP);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_STARTUP);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_STARTUP);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different startup -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStartup(Model model) {
        model.updateFilteredStartupList(p -> false);

        assertTrue(model.getFilteredStartupList().isEmpty());
    }
}
