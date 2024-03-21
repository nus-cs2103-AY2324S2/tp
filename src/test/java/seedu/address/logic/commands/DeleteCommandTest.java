package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtId;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalIds.ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalIds.ID_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalNetConnect;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private final Model model = new ModelManager(getTypicalNetConnect(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        Person personToDelete = model.getPersonById(ID_FIRST_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(ID_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getNetConnect(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        Id outOfBoundId = Id.generateNextId();
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundId);

        assertCommandFailure(deleteCommand, model,
                String.format(Messages.MESSAGE_INVALID_PERSON_ID, outOfBoundId.value));
    }

    @Test
    public void execute_validIdFilteredListPresent_success() {
        showPersonAtId(model, ID_FIRST_PERSON);

        Person personToDelete = model.getPersonById(ID_FIRST_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(ID_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getNetConnect(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIdFilteredListAbsent_success() {
        showPersonAtId(model, ID_SECOND_PERSON);

        Person personToDelete = model.getPersonById(ID_FIRST_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(ID_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getNetConnect(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showAllPersons(expectedModel);

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
        Index targetIndex = Index.fromOneBased(1);
        Id targetId = ID_FIRST_PERSON;
        DeleteCommand deleteCommand = new DeleteCommand(targetId);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetId=" + targetId + "}";
        assertTrue(expected.equals(deleteCommand.toString()));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showAllPersons(Model model) {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
}
