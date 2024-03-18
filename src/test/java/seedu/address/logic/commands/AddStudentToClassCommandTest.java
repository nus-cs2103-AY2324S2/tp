package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.addstudenttoclasscommands.AddStudentToClassByEmailCommand;
import seedu.address.logic.commands.addstudenttoclasscommands.AddStudentToClassByIdCommand;
import seedu.address.logic.commands.addstudenttoclasscommands.AddStudentToClassByIndexCommand;
import seedu.address.logic.commands.deletestudentcommands.DeleteStudentByIndexCommand;
import seedu.address.logic.commands.deletestudentcommands.DeleteStudentCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialClass;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class AddStudentToClassCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStudentCommand deleteCommand = new DeleteStudentByIndexCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStudentCommand deleteCommand = new DeleteStudentByIndexCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {

        // Test index based delete command
        AddStudentToClassByIndexCommand addStudentToClassByIndexFirstCommand = new AddStudentToClassByIndexCommand(
                INDEX_FIRST_PERSON, new ModuleCode(VALID_MODULE_AMY), new TutorialClass(VALID_TUTORIAL_AMY));
        AddStudentToClassByIndexCommand addStudentToClassByIndexSecondCommand = new AddStudentToClassByIndexCommand(
                INDEX_SECOND_PERSON, new ModuleCode(VALID_MODULE_BOB), new TutorialClass(VALID_TUTORIAL_BOB));
        // same object -> returns true
        assertTrue(addStudentToClassByIndexFirstCommand.equals(addStudentToClassByIndexFirstCommand));
        // different types -> returns false
        assertFalse(addStudentToClassByIndexFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addStudentToClassByIndexFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(addStudentToClassByIndexFirstCommand.equals(addStudentToClassByIndexSecondCommand));

        // Test email based delete command
        AddStudentToClassByEmailCommand addStudentToClassByEmailFirstCommand = new AddStudentToClassByEmailCommand(
                new Email(VALID_EMAIL_AMY), new ModuleCode(VALID_MODULE_AMY), new TutorialClass(VALID_TUTORIAL_AMY));
        AddStudentToClassByEmailCommand addStudentToClassByEmailSecondCommand = new AddStudentToClassByEmailCommand(
                new Email(VALID_EMAIL_BOB), new ModuleCode(VALID_MODULE_BOB), new TutorialClass(VALID_TUTORIAL_BOB));

        // same object -> returns true
        assertTrue(addStudentToClassByEmailFirstCommand.equals(addStudentToClassByEmailFirstCommand));
        // different types -> returns false
        assertFalse(addStudentToClassByEmailFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addStudentToClassByEmailFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(addStudentToClassByEmailFirstCommand.equals(addStudentToClassByEmailSecondCommand));

        // Test student id based delete command
        AddStudentToClassByIdCommand addStudentToClassByIdFirstCommand = new AddStudentToClassByIdCommand(
                new StudentId(VALID_STUDENT_ID_AMY), new ModuleCode(VALID_MODULE_AMY),
                new TutorialClass(VALID_TUTORIAL_AMY));
        AddStudentToClassByIdCommand addStudentToClassByIdSecondCommand = new AddStudentToClassByIdCommand(
                new StudentId(VALID_STUDENT_ID_BOB), new ModuleCode(VALID_MODULE_BOB),
                new TutorialClass(VALID_TUTORIAL_BOB));

        // same object -> returns true
        assertTrue(addStudentToClassByIdFirstCommand.equals(addStudentToClassByIdFirstCommand));
        // different types -> returns false
        assertFalse(addStudentToClassByIdFirstCommand.equals(1));
        // null -> returns false
        assertFalse(addStudentToClassByIdFirstCommand.equals(null));
        // different person -> returns false
        assertFalse(addStudentToClassByIdFirstCommand.equals(addStudentToClassByIdSecondCommand));

    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
