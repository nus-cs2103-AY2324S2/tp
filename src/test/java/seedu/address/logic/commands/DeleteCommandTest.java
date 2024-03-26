package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        List<Index> indices = new ArrayList<>();
        indices.add(outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(indices);

        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        // Create a model with an empty person list
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        CommandHistory history = new CommandHistory();
        Index outOfBoundIndex = Index.fromZeroBased(0);
        List<Index> indices = new ArrayList<>();
        indices.add(outOfBoundIndex);

        // Create a DeleteCommand with a list of indices
        DeleteCommand deleteCommand = new DeleteCommand(indices);

        // Execute command and assert CommandException is thrown
        assertThrows(CommandException.class, () -> deleteCommand.execute(model, history));
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        List<Index> indices = new ArrayList<>();
        indices.add(outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(indices);

        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        List<Index> indices1 = new ArrayList<>();
        indices1.add(INDEX_FIRST_PERSON);
        List<Index> indices2 = new ArrayList<>();
        indices2.add(INDEX_SECOND_PERSON);

        DeleteCommand deleteFirstCommand = new DeleteCommand(indices1);
        DeleteCommand deleteSecondCommand = new DeleteCommand(indices2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        List<Index> indices1Copy = new ArrayList<>(indices1);
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(indices1Copy);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different indices -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        List<Index> indices = new ArrayList<>();
        indices.add(INDEX_FIRST_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(indices);
        String expected = DeleteCommand.class.getSimpleName() + "{targetIndices=" + indices + "}";
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
