package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MISSING_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT_1;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBookWithAppointments;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.date.Date;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CancelAppCommand}.
 */
public class DeleteApptCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithAppointments(), new UserPrefs());

    @Test
    public void execute_validInput_success() {
        DeleteApptCommand deleteApptCommand = new DeleteApptCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );

        // Expected message after successful cancellation
        String expectedMessage = String.format(DeleteApptCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                Messages.format(ALICE_APPT));

        assertCommandSuccess(deleteApptCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_appointmentNotFound_throwsCommandException() {
        DeleteApptCommand deleteApptCommand = new DeleteApptCommand(
                ALICE_APPT.getNric(),
                new Date("1900-02-02"),
                ALICE_APPT.getTimePeriod()
        );

        assertCommandFailure(deleteApptCommand, model, Messages.MESSAGE_APPOINTMENT_NOT_FOUND);
    }

    @Test
    public void execute_nricNotFound_throwsCommandException() {
        Nric missingNric = new Nric(MISSING_NRIC);
        DeleteApptCommand deleteApptCommand = new DeleteApptCommand(
                missingNric,
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );

        assertCommandFailure(deleteApptCommand, model, Messages.MESSAGE_PATIENT_NRIC_NOT_FOUND);
    }

    @Test
    public void equals() {

        DeleteApptCommand cancelFirstAppointment = new DeleteApptCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );
        DeleteApptCommand cancelSecondAppointment = new DeleteApptCommand(
                ALICE_APPT_1.getNric(),
                ALICE_APPT_1.getDate(),
                ALICE_APPT_1.getTimePeriod()
        );

        // same object -> returns true
        assertTrue(cancelFirstAppointment.equals(cancelFirstAppointment));

        //same values --> returns true
        DeleteApptCommand cancelFirstAppointmentCopy = new DeleteApptCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );
        assertTrue(cancelFirstAppointment.equals(cancelFirstAppointmentCopy));


        // different types -> returns false
        assertFalse(cancelFirstAppointment.equals(1));

        // null -> returns false
        assertFalse(cancelFirstAppointment.equals(null));

        // different date -> returns false
        assertFalse(cancelFirstAppointment.equals(cancelSecondAppointment));
    }

    @Test
    public void toStringMethod() {
        DeleteApptCommand deleteApptCommand = new DeleteApptCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );

        String expected = DeleteApptCommand.class.getCanonicalName()
                + "{nric=" + ALICE_APPT.getNric() + ", "
                + "date=" + ALICE_APPT.getDate() + ", "
                + "timePeriod=" + ALICE_APPT.getTimePeriod() + "}";
        assertEquals(expected, deleteApptCommand.toString());
    }
}
