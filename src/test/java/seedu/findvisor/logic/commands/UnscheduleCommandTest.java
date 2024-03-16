package seedu.findvisor.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.findvisor.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.findvisor.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.findvisor.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.findvisor.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.logic.Messages;
import seedu.findvisor.model.Model;
import seedu.findvisor.model.ModelManager;
import seedu.findvisor.model.UserPrefs;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnscheduleCommand}.
 */
public class UnscheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToUnschedule = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        UnscheduleCommand unscheduleCommand = new UnscheduleCommand(INDEX_THIRD_PERSON);
        PersonBuilder personBuilder = new PersonBuilder(personToUnschedule).withMeeting(Optional.empty());
        Person editedPerson = personBuilder.build();

        String expectedMessage = String.format(UnscheduleCommand.MESSAGE_UNSCHEDULE_SUCCESS,
                personToUnschedule.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToUnschedule, editedPerson);

        assertCommandSuccess(unscheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnscheduleCommand unscheduleCommand = new UnscheduleCommand(outOfBoundIndex);

        assertCommandFailure(unscheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_THIRD_PERSON);

        Person personToUnschedule = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnscheduleCommand unscheduleCommand = new UnscheduleCommand(INDEX_FIRST_PERSON);
        PersonBuilder personBuilder = new PersonBuilder(personToUnschedule).withMeeting(Optional.empty());
        Person editedPerson = personBuilder.build();

        String expectedMessage = String.format(UnscheduleCommand.MESSAGE_UNSCHEDULE_SUCCESS,
                personToUnschedule.getName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToUnschedule, editedPerson);

        assertCommandSuccess(unscheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnscheduleCommand unscheduleCommand = new UnscheduleCommand(outOfBoundIndex);

        assertCommandFailure(unscheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noMeetingToUnschedule_throwsCommandException() {
        UnscheduleCommand unscheduleCommand = new UnscheduleCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(unscheduleCommand, model, UnscheduleCommand.MESSAGE_NO_MEETING_TO_UNSCHEDULE);
    }

    @Test
    public void equals() {
        UnscheduleCommand unscheduleFirstCommand = new UnscheduleCommand(INDEX_FIRST_PERSON);
        UnscheduleCommand unscheduleSecondCommand = new UnscheduleCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(unscheduleFirstCommand.equals(unscheduleFirstCommand));

        // same values -> returns true
        UnscheduleCommand unscheduleFirstCommandCopy = new UnscheduleCommand(INDEX_FIRST_PERSON);
        assertTrue(unscheduleFirstCommand.equals(unscheduleFirstCommandCopy));

        // different types -> returns false
        assertFalse(unscheduleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unscheduleFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unscheduleFirstCommand.equals(unscheduleSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        UnscheduleCommand unscheduleCommand = new UnscheduleCommand(targetIndex);
        String expected = UnscheduleCommand.class.getCanonicalName() + "{toUnschedule=" + targetIndex + "}";
        assertEquals(expected, unscheduleCommand.toString());
    }

}
