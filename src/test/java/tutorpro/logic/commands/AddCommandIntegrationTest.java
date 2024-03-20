package tutorpro.logic.commands;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tutorpro.logic.Messages;
import tutorpro.model.Model;
import tutorpro.model.ModelManager;
import tutorpro.model.UserPrefs;
import tutorpro.model.person.Person;
import tutorpro.model.person.student.Student;
import tutorpro.testutil.StudentBuilder;
import tutorpro.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Student validPerson = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        CommandTestUtil.assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        if (!(personInList instanceof Student)) {
            fail();
        }
        Student studentInList = (Student) personInList;
        CommandTestUtil.assertCommandFailure(new AddCommand(studentInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
