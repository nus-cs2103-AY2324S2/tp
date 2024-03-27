package seedu.teachstack.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.teachstack.logic.Messages;
import seedu.teachstack.logic.commands.exceptions.CommandException;
import seedu.teachstack.model.AddressBook;
import seedu.teachstack.model.Model;
import seedu.teachstack.model.ModelManager;
import seedu.teachstack.model.UserPrefs;
import seedu.teachstack.model.person.Grade;
import seedu.teachstack.model.person.Person;
import seedu.teachstack.testutil.EditPersonDescriptorBuilder;
import seedu.teachstack.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.teachstack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.teachstack.logic.commands.SetWeakThresholdCommand.MESSAGE_SET_THRESHOLD_SUCCESS;
import static seedu.teachstack.testutil.Assert.assertThrows;
import static seedu.teachstack.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.teachstack.testutil.TypicalStudentIds.ID_FIRST_PERSON;

public class SetWeakThresholdCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void set_threshold_command_success() {
        Grade testGrade = new Grade("D+");
        SetWeakThresholdCommand weakCommand = new SetWeakThresholdCommand(testGrade);
        assertTrue(weakCommand.retrieveGrade() == testGrade);
    }

    @Test
    public void execute_threshold_command_success() throws CommandException {
        Grade testGrade = new Grade("B");
        SetWeakThresholdCommand testCommand = new SetWeakThresholdCommand(testGrade);
        Person editedPerson = new PersonBuilder().withStudentId("A0128956X").withGrade("B-").build();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        //expectedModel.modifyThreshold(testGrade);

        // Execute the command and ensure that it returns success message
        CommandResult commandResult = testCommand.execute(model);
        assertEquals(MESSAGE_SET_THRESHOLD_SUCCESS, commandResult.getFeedbackToUser());

        // Ensure that the model after command execution is as expected
        assertEquals(expectedModel, model);
    }

}
