package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MISSING_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePatientCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNric_success() {
        Person personToDelete = model.getPersonWithNric(ALICE.getNric());
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(personToDelete.getNric());

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNricNoAppointments_success() {
        Person personToDelete = model.getPersonWithNric(ALICE.getNric());
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(personToDelete.getNric());

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNricWithAppointments_success() {
        Person patientToDelete = ALICE;
        model.addAppointment(ALICE_APPT);
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(patientToDelete.getNric());

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(patientToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePersonWithNric(patientToDelete.getNric());

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredAppointmentList().isEmpty());
    }

    @Test
    public void execute_missingNric_throwsCommandException() {
        Nric missingNric = new Nric(MISSING_NRIC);
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(missingNric);

        assertCommandFailure(deletePatientCommand, model, Messages.MESSAGE_PATIENT_NRIC_NOT_FOUND);
    }

    @Test
    public void equals() {
        Person patientToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePatientCommand deleteFirstCommand = new DeletePatientCommand(patientToDelete.getNric());
        Person patientToDelete2 = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        DeletePatientCommand deleteSecondCommand = new DeletePatientCommand(patientToDelete2.getNric());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        Person patientToDeleteCopy = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePatientCommand deleteFirstCommandCopy = new DeletePatientCommand(patientToDeleteCopy.getNric());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Person patientToDelete = model.getFilteredPersonList().get(1);
        Nric targetNric = patientToDelete.getNric();
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(targetNric);
        String expected = DeletePatientCommand.class.getCanonicalName() + "{targetNric=" + targetNric + "}";
        assertEquals(expected, deletePatientCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
