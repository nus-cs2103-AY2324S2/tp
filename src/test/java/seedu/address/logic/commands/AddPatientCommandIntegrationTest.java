package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalPatientList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddPatientCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPatientList(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Patient validPatient = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getPatientList(), new UserPrefs());
        expectedModel.addPerson(validPatient);

        assertCommandSuccess(new AddCommand(validPatient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPatient)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Patient patientInList = model.getPatientList().getPersonList().get(0);
        assertCommandFailure(new AddCommand(patientInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
