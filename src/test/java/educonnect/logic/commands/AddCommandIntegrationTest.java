package educonnect.logic.commands;

import static educonnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static educonnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static educonnect.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import educonnect.logic.Messages;
import educonnect.model.Model;
import educonnect.model.ModelManager;
import educonnect.model.UserPrefs;
import educonnect.model.student.Student;
import educonnect.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private static final String VALID_NAME = "Barbara Choo";
    private static final String VALID_STUDENT_ID = "A7777777B";
    private static final String VALID_EMAIL = "barbarachoo@gmail.com";
    private static final String VALID_TELEGRAM_HANDLE = "@babchoo";

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddCommand(validStudent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validStudent)),
                expectedModel);
    }

    @Test
    public void execute_duplicateStudentId_throwsCommandException() {
        Student studentInList = model.getAddressBook().getStudentList().get(0);
        Student duplicateStudentIdStudent = new StudentBuilder(studentInList)
                .withName(VALID_NAME)
                .withEmail(VALID_EMAIL)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE)
                .build();

        assertCommandFailure(new AddCommand(duplicateStudentIdStudent), model,
                AddCommand.MESSAGE_DUPLICATE_STUDENT_ID);
    }

    @Test
    public void execute_duplicateEmail_throwsCommandException() {
        Student studentInList = model.getAddressBook().getStudentList().get(0);
        Student duplicateEmailStudent = new StudentBuilder(studentInList)
                .withName(VALID_NAME)
                .withStudentId(VALID_STUDENT_ID)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE)
                .build();

        assertCommandFailure(new AddCommand(duplicateEmailStudent), model,
                AddCommand.MESSAGE_DUPLICATE_EMAIL);
    }

    @Test
    public void execute_duplicateTelegramHandle_throwsCommandException() {
        Student studentInList = model.getAddressBook().getStudentList().get(0);
        Student DuplicateTelegramHandleStudent = new StudentBuilder(studentInList)
                .withName(VALID_NAME)
                .withEmail(VALID_EMAIL)
                .withStudentId(VALID_STUDENT_ID)
                .build();

        assertCommandFailure(new AddCommand(DuplicateTelegramHandleStudent), model,
                AddCommand.MESSAGE_DUPLICATE_TELEGRAM_HANDLE);
    }
}
