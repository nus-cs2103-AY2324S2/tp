package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ClearTaskCommand}.
 */
public class ClearTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new TaskList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToUnassign = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Person editedPerson = new Person(personToUnassign.getName(), personToUnassign.getPhone(),
                personToUnassign.getEmail(), personToUnassign.getAddress(), new HashSet<>());

        ClearTaskCommand clearTaskCommand = new ClearTaskCommand(INDEX_FIRST);

        String expectedMessage = String.format(ClearTaskCommand.MESSAGE_SUCCESS, personToUnassign.getName());

        ModelManager expectedModel = new ModelManager(
                model.getAddressBook(), new TaskList(model.getTaskList()), new UserPrefs());
        expectedModel.setPerson(personToUnassign, editedPerson);

        assertCommandSuccess(clearTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ClearTaskCommand clearTaskCommand = new ClearTaskCommand(outOfBoundIndex);

        assertCommandFailure(clearTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Person personToUnassign = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Person editedPerson = new Person(personToUnassign.getName(), personToUnassign.getPhone(),
                personToUnassign.getEmail(), personToUnassign.getAddress(), new HashSet<>());

        ClearTaskCommand clearTaskCommand = new ClearTaskCommand(INDEX_FIRST);

        String expectedMessage = String.format(ClearTaskCommand.MESSAGE_SUCCESS, personToUnassign.getName());

        Model expectedModel = new ModelManager(
                model.getAddressBook(), new TaskList(model.getTaskList()), new UserPrefs());
        expectedModel.setPerson(personToUnassign, editedPerson);

        assertCommandSuccess(clearTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ClearTaskCommand clearTaskCommand = new ClearTaskCommand(outOfBoundIndex);

        assertCommandFailure(clearTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ClearTaskCommand clearTaskFirstCommand = new ClearTaskCommand(INDEX_FIRST);
        ClearTaskCommand clearTaskSecondCommand = new ClearTaskCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(clearTaskFirstCommand, clearTaskFirstCommand);

        // same values -> returns true
        ClearTaskCommand clearTaskFirstCommandCopy = new ClearTaskCommand(INDEX_FIRST);
        assertEquals(clearTaskFirstCommand, clearTaskFirstCommandCopy);

        // null -> returns false
        assertNotEquals(clearTaskFirstCommand, null);

        // different person -> returns false
        assertNotEquals(clearTaskFirstCommand, clearTaskSecondCommand);
    }
}
