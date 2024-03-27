package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MISSING_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.AMY_APPT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePatientCommand}.
 */
public class DeletePatientCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNric_success() {
        Patient patientToDelete = model.getPatientWithNric(new Nric(VALID_NRIC_AMY));
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(patientToDelete.getNric());

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(patientToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNricNoAppointments_success() {
        Patient patientToDelete = model.getPatientWithNric(new Nric(VALID_NRIC_AMY));
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(patientToDelete.getNric());

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(patientToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNricWithAppointments_success() {
        Patient patientToDelete = model.getPatientWithNric(new Nric(VALID_NRIC_AMY));
        model.addAppointment(AMY_APPT);
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(patientToDelete.getNric());

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(patientToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePatientWithNric(patientToDelete.getNric());

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredAppointmentViewList().isEmpty());
    }

    @Test
    public void execute_missingNric_throwsCommandException() {
        Nric missingNric = new Nric(MISSING_NRIC);
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(missingNric);

        assertCommandFailure(deletePatientCommand, model, Messages.MESSAGE_PATIENT_NRIC_NOT_FOUND);
    }

    @Test
    public void equals() {
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        DeletePatientCommand deleteFirstCommand = new DeletePatientCommand(patientToDelete.getNric());
        Patient patientToDelete2 = model.getFilteredPatientList().get(INDEX_SECOND_PATIENT.getZeroBased());
        DeletePatientCommand deleteSecondCommand = new DeletePatientCommand(patientToDelete2.getNric());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        Patient patientToDeleteCopy = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        DeletePatientCommand deleteFirstCommandCopy = new DeletePatientCommand(patientToDeleteCopy.getNric());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Patient patientToDelete = model.getFilteredPatientList().get(1);
        Nric targetNric = patientToDelete.getNric();
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(targetNric);
        String expected = DeletePatientCommand.class.getCanonicalName() + "{targetNric=" + targetNric + "}";
        assertEquals(expected, deletePatientCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPatient(Model model) {
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
