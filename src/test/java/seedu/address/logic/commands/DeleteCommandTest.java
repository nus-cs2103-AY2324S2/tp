package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.logic.commands.CommandTestUtil.NON_EXISTENT_NRIC;
import static seedu.address.testutil.TypicalPersons.*;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNricUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().filtered(p -> p.getNric().equals(ALICE.getNric())).get(0);
        DeleteCommand deleteCommand = new DeleteCommand(ALICE.getNric());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getImmuniMate(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNricUnfilteredList_throwsCommandException() {
        Nric nonExistingNric = new Nric(NON_EXISTENT_NRIC);
        DeleteCommand deleteCommand = new DeleteCommand(nonExistingNric);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NRIC_NOT_FOUND);
    }

    //TODO: figure out why the og version has filtered adn unfilered separated and edit accordingly
    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonWithNric(model, BOB.getNric());
        Person personToDelete = model.getFilteredPersonList().filtered(p -> p.getNric().equals(BOB.getNric())).get(0);
        DeleteCommand deleteCommand = new DeleteCommand(BOB.getNric());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getImmuniMate(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(Nric.isValidNric(NON_EXISTENT_NRIC));

        DeleteCommand deleteCommand = new DeleteCommand(new Nric(NON_EXISTENT_NRIC));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NRIC_NOT_FOUND);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(BOB.getNric());
        DeleteCommand deleteSecondCommand = new DeleteCommand(new Nric(NON_EXISTENT_NRIC));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(BOB.getNric());
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
        DeleteCommand deleteCommand = new DeleteCommand(BOB.getNric());
        String expected = DeleteCommand.class.getCanonicalName() + "{targetNric=" + BOB.getNric() + "}";
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
