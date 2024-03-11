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
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getAddressBook().getStudentList().get(0);
        assertCommandFailure(new AddCommand(studentInList), model,
                AddCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
