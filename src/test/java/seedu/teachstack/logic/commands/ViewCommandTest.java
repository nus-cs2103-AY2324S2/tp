package seedu.teachstack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.teachstack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.teachstack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.teachstack.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.teachstack.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.teachstack.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.teachstack.testutil.TypicalStudentIds.ID_FIRST_PERSON;
import static seedu.teachstack.testutil.TypicalStudentIds.ID_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.teachstack.logic.Messages;
import seedu.teachstack.model.Model;
import seedu.teachstack.model.ModelManager;
import seedu.teachstack.model.UserPrefs;
import seedu.teachstack.model.person.Person;
import seedu.teachstack.model.person.StudentId;

class ViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToView = model.getPerson(ID_FIRST_PERSON);
        ViewCommand viewCommand = new ViewCommand(ID_FIRST_PERSON);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS,
                Messages.format(personToView));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        StudentId outOfBoundId = new StudentId("A9999999Z");
        ViewCommand viewCommand = new ViewCommand(outOfBoundId);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_DISPLAYED_STUDENT_ID);
    }

    @Test
    public void execute_invalidIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToView = model.getPerson(ID_SECOND_PERSON);
        StudentId outOfBoundId = personToView.getStudentId();

        // ensures that outOfBoundId is still in bounds of address book list
        assertTrue(model.getAddressBook().getPersonList().stream()
                .filter(person -> person.getStudentId().equals(outOfBoundId)).toArray().length == 1);

        ViewCommand viewCommand = new ViewCommand(outOfBoundId);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS,
                Messages.format(personToView));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(ID_FIRST_PERSON);
        ViewCommand viewSecondCommand = new ViewCommand(ID_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(ID_FIRST_PERSON);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void toStringMethod() {
        StudentId targetId = new StudentId("A0123456A");
        ViewCommand viewCommand = new ViewCommand(targetId);
        String expected = ViewCommand.class.getCanonicalName() + "{targetId=" + targetId + "}";
        assertEquals(expected, viewCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
