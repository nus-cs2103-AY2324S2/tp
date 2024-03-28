package scrolls.elder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static scrolls.elder.logic.commands.CommandTestUtil.assertCommandFailure;
import static scrolls.elder.logic.commands.CommandTestUtil.assertCommandSuccess;
import static scrolls.elder.logic.commands.CommandTestUtil.showPersonAtIndex;
import static scrolls.elder.logic.commands.CommandTestUtil.showVolunteerAtIndex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrolls.elder.commons.core.index.Index;
import scrolls.elder.logic.Messages;
import scrolls.elder.model.Datastore;
import scrolls.elder.model.Model;
import scrolls.elder.model.ModelManager;
import scrolls.elder.model.PersonStore;
import scrolls.elder.model.UserPrefs;
import scrolls.elder.model.person.Person;
import scrolls.elder.model.person.Role;
import scrolls.elder.testutil.TypicalDatastore;
import scrolls.elder.testutil.TypicalIndexes;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private static final String ROLE_STRING_VOLUNTEER = "volunteer";
    private static final String ROLE_STRING_BEFRIENDEE = "befriendee";
    private static final Role ROLE_VOLUNTEER = new Role(ROLE_STRING_VOLUNTEER);
    private static final Role ROLE_BEFRIENDEE = new Role(ROLE_STRING_BEFRIENDEE);
    private Model model;
    private PersonStore personStore;
    private Model expectedModel;
    private PersonStore expectedPersonStore;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalDatastore.getTypicalDatastore(), new UserPrefs());
        personStore = model.getMutableDatastore().getMutablePersonStore();
        expectedModel = new ModelManager(new Datastore(model.getDatastore()), new UserPrefs());
        expectedPersonStore = expectedModel.getMutableDatastore().getMutablePersonStore();
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete =
            personStore.getFilteredVolunteerList().get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_SECOND_PERSON, ROLE_VOLUNTEER);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
            Messages.format(personToDelete));

        expectedPersonStore.removePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(personStore.getFilteredVolunteerList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, ROLE_VOLUNTEER);
        String expectedMessage =
            DeleteCommand.MESSAGE_DELETE_PERSON_ERROR + Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showVolunteerAtIndex(personStore, TypicalIndexes.INDEX_SECOND_PERSON);

        Person personToDelete =
            personStore.getFilteredVolunteerList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON, ROLE_VOLUNTEER);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
            Messages.format(personToDelete));

        expectedPersonStore.removePerson(personToDelete);
        showNoPerson(expectedPersonStore);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(personStore, TypicalIndexes.INDEX_FIRST_PERSON);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatastore().getPersonStore().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, ROLE_VOLUNTEER);
        String expectedMessage =
            DeleteCommand.MESSAGE_DELETE_PERSON_ERROR + Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_personPaired_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON, ROLE_VOLUNTEER);

        String expectedMessage =
            DeleteCommand.MESSAGE_DELETE_PERSON_ERROR + Messages.MESSAGE_CONTACT_PAIRED_BEFORE_DELETE;

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_personNotPaired_success() {
        Person personToDelete =
            personStore.getFilteredBefriendeeList().get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TypicalIndexes.INDEX_SECOND_PERSON, ROLE_BEFRIENDEE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
            Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getDatastore(), new UserPrefs());
        expectedModel.getMutableDatastore().getMutablePersonStore().removePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON, ROLE_VOLUNTEER);
        DeleteCommand deleteSecondCommand = new DeleteCommand(TypicalIndexes.INDEX_SECOND_PERSON, ROLE_VOLUNTEER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON, ROLE_VOLUNTEER);
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
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex, ROLE_VOLUNTEER);
        String expected =
            String.format("%s{targetIndex=%s, role=%s}", DeleteCommand.class.getCanonicalName(), targetIndex,
                ROLE_STRING_VOLUNTEER);
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(PersonStore ps) {
        ps.updateFilteredPersonList(p -> false);

        assert ps.getFilteredPersonList().isEmpty();
    }
}
