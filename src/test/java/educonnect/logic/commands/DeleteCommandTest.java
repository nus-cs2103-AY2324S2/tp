package educonnect.logic.commands;

import static educonnect.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static educonnect.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static educonnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static educonnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static educonnect.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static educonnect.testutil.TypicalStudents.JOHHNY;
import static educonnect.testutil.TypicalStudents.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import educonnect.commons.core.index.Index;
import educonnect.logic.Messages;
import educonnect.model.Model;
import educonnect.model.ModelManager;
import educonnect.model.UserPrefs;
import educonnect.model.student.Email;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validStudentIdUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentId studentId = studentToDelete.getStudentId();
        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor = new DeleteCommand.DeleteStudentDescriptor();

        deleteStudentDescriptor.setStudentId(studentId);
        DeleteCommand deleteCommand = new DeleteCommand(deleteStudentDescriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS,
                Messages.format(studentToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validEmaildUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Email email = studentToDelete.getEmail();
        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor = new DeleteCommand.DeleteStudentDescriptor();

        deleteStudentDescriptor.setEmail(email);
        DeleteCommand deleteCommand = new DeleteCommand(deleteStudentDescriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS,
                Messages.format(studentToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTelegramHandleUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        TelegramHandle telegramHandle = studentToDelete.getTelegramHandle();
        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor = new DeleteCommand.DeleteStudentDescriptor();

        deleteStudentDescriptor.setTelegramHandle(telegramHandle);
        DeleteCommand deleteCommand = new DeleteCommand(deleteStudentDescriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS,
                Messages.format(studentToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAllUniqueIdentifiersUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        TelegramHandle telegramHandle = studentToDelete.getTelegramHandle();
        Email email = studentToDelete.getEmail();
        StudentId studentId = studentToDelete.getStudentId();

        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor = new DeleteCommand.DeleteStudentDescriptor();

        deleteStudentDescriptor.setTelegramHandle(telegramHandle);
        deleteStudentDescriptor.setEmail(email);
        deleteStudentDescriptor.setStudentId(studentId);

        DeleteCommand deleteCommand = new DeleteCommand(deleteStudentDescriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS,
                Messages.format(studentToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noStudentIdFound_throwsCommandException() {
        StudentId notInList = JOHHNY.getStudentId();

        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor = new DeleteCommand.DeleteStudentDescriptor();

        deleteStudentDescriptor.setStudentId(notInList);

        DeleteCommand deleteCommand = new DeleteCommand(deleteStudentDescriptor);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NO_STUDENT_FOUND);
    }

    @Test
    public void execute_noEmailFound_throwsCommandException() {
        Email notInList = JOHHNY.getEmail();

        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor = new DeleteCommand.DeleteStudentDescriptor();

        deleteStudentDescriptor.setEmail(notInList);

        DeleteCommand deleteCommand = new DeleteCommand(deleteStudentDescriptor);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NO_STUDENT_FOUND);
    }

    @Test
    public void execute_noTelegramHandleFound_throwsCommandException() {
        TelegramHandle notInList = JOHHNY.getTelegramHandle();

        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor = new DeleteCommand.DeleteStudentDescriptor();

        deleteStudentDescriptor.setTelegramHandle(notInList);

        DeleteCommand deleteCommand = new DeleteCommand(deleteStudentDescriptor);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NO_STUDENT_FOUND);
    }


    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void equals() {
        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor1 = new DeleteCommand.DeleteStudentDescriptor();
        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor2 = new DeleteCommand.DeleteStudentDescriptor();

        deleteStudentDescriptor1.setStudentId(new StudentId(VALID_STUDENT_ID_AMY));
        deleteStudentDescriptor2.setStudentId(new StudentId(VALID_STUDENT_ID_BOB));

        DeleteCommand deleteFirstCommand = new DeleteCommand(deleteStudentDescriptor1);
        DeleteCommand deleteSecondCommand = new DeleteCommand(deleteStudentDescriptor2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(deleteStudentDescriptor1);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor = new DeleteCommand.DeleteStudentDescriptor();
        deleteStudentDescriptor.setStudentId(new StudentId(VALID_STUDENT_ID_AMY));

        DeleteCommand deleteCommand = new DeleteCommand(deleteStudentDescriptor);

        String expected = DeleteCommand.class.getCanonicalName() + "{deleteStudentDescriptor="
                + deleteStudentDescriptor + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
