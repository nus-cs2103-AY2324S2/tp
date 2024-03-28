package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.deletestudentcommands.DeleteStudentByEmailCommand;
import seedu.address.logic.commands.deletestudentcommands.DeleteStudentByIdCommand;
import seedu.address.logic.commands.deletestudentcommands.DeleteStudentByIndexCommand;
import seedu.address.logic.commands.deletestudentcommands.DeleteStudentCommand;
import seedu.address.logic.messages.PersonMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code DeleteStudentCommand}.
 */
public class DeleteStudentCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_deleteStudentById_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // First add student to the list
        Person validPerson = new PersonBuilder().build();
        expectedModel.addPerson(validPerson);

        // Attempt to delete the student
        assertCommandSuccess(new DeleteStudentByIdCommand(validPerson.getStudentId()), expectedModel,
                String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_deleteStudentbyIndex_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Get first student from the list
        Person person = expectedModel.getFilteredPersonList().get(0);

        // Attempt to delete the student
        assertCommandSuccess(new DeleteStudentByIndexCommand(INDEX_FIRST_PERSON), expectedModel,
                String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(person)),
                expectedModel);
    }

    @Test
    public void execute_deleteStudentByEmail_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // First add student to the list
        Person validPerson = new PersonBuilder().build();
        expectedModel.addPerson(validPerson);

        // Attempt to delete the student
        assertCommandSuccess(new DeleteStudentByEmailCommand(validPerson.getEmail()), expectedModel,
                String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_deleteStudentWithInvalidId_throwsCommandException() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // First add student to the list
        Person validPerson = new PersonBuilder().build();
        expectedModel.addPerson(validPerson);

        StudentId invalidId = new StudentId("A0123456X");

        // Attempt to delete the student
        assertCommandFailure(new DeleteStudentByIdCommand(invalidId), expectedModel,
                String.format(PersonMessages.MESSAGE_PERSON_STUDENT_ID_NOT_FOUND, "A0123456X"));
    }

    @Test
    public void execute_deleteStudentWithInvalidEmail_throwsCommandException() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Email invalidEmail = new Email("invalidEmail@example.com");

        // Attempt to delete the student
        assertCommandFailure(new DeleteStudentByEmailCommand(invalidEmail), expectedModel,
                String.format(PersonMessages.MESSAGE_PERSON_EMAIL_NOT_FOUND, "invalidEmail@example.com"));
    }

    @Test
    public void execute_deleteStudentwithInvalidIndex_throwsCommandException() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Index invalidIndex = Index.fromOneBased(1000);

        // Attempt to delete the student
        assertCommandFailure(new DeleteStudentByIndexCommand(invalidIndex), expectedModel,
                String.format(PersonMessages.MESSAGE_PERSON_INDEX_NOT_FOUND, 1000));
    }

}
