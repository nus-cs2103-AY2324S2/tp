package educonnect.logic.commands;

import static educonnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static educonnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static educonnect.logic.commands.LinkCommand.createEditedStudent;
import static educonnect.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static educonnect.testutil.TypicalStudents.JOHHNY;
import static educonnect.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import educonnect.logic.Messages;
import educonnect.model.AddressBook;
import educonnect.model.Model;
import educonnect.model.ModelManager;
import educonnect.model.UserPrefs;
import educonnect.model.student.Email;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;

public class LinkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void parse_validStudentId_success() {
        Student studentToLink = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentId studentId = studentToLink.getStudentId();

        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();
        linkStudentDescriptor.setStudentId(studentId);
        LinkCommand linkCommand = new LinkCommand(linkStudentDescriptor);
        Student newStudent = createEditedStudent(studentToLink, linkStudentDescriptor);

        String expectedMessage = String.format(LinkCommand.MESSAGE_LINK_STUDENT_SUCCESS,
                Messages.format(newStudent));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(studentToLink, newStudent);

        assertCommandSuccess(linkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void parse_validEmail_success() {
        Student studentToLink = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Email email = studentToLink.getEmail();

        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();
        linkStudentDescriptor.setEmail(email);
        LinkCommand linkCommand = new LinkCommand(linkStudentDescriptor);
        Student newStudent = createEditedStudent(studentToLink, linkStudentDescriptor);

        String expectedMessage = String.format(LinkCommand.MESSAGE_LINK_STUDENT_SUCCESS,
                Messages.format(newStudent));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(studentToLink, newStudent);

        assertCommandSuccess(linkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void parse_validTelegramHandle_success() {
        Student studentToLink = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        TelegramHandle handle = studentToLink.getTelegramHandle();

        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();
        linkStudentDescriptor.setTelegramHandle(handle);
        LinkCommand linkCommand = new LinkCommand(linkStudentDescriptor);
        Student newStudent = createEditedStudent(studentToLink, linkStudentDescriptor);

        String expectedMessage = String.format(LinkCommand.MESSAGE_LINK_STUDENT_SUCCESS,
                Messages.format(newStudent));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(studentToLink, newStudent);

        assertCommandSuccess(linkCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_noStudentIdFound_throwsCommandException() {
        StudentId notInList = JOHHNY.getStudentId();
        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();
        linkStudentDescriptor.setStudentId(notInList);
        LinkCommand linkCommand = new LinkCommand(linkStudentDescriptor);
        assertCommandFailure(linkCommand, model, Messages.MESSAGE_NO_STUDENT_FOUND);

    }
}
