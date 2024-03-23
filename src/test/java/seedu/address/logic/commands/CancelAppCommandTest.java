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

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CancelAppCommand}.
 */
public class CancelAppCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithAppointments(), new UserPrefs());

    @Test
    public void execute_validInput_success() {
        CancelAppCommand cancelAppCommand = new CancelAppCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );

        // Expected message after successful cancellation
        String expectedMessage = String.format(CancelAppCommand.MESSAGE_CANCEL_APPOINTMENT_SUCCESS,
                Messages.format(ALICE_APPT));

        assertCommandSuccess(cancelAppCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_nricNotFound_throwsCommandException() {
        Nric missingNric = new Nric(MISSING_NRIC);
        CancelAppCommand cancelAppCommand = new CancelAppCommand(
                missingNric,
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );

        assertCommandFailure(cancelAppCommand, model, Messages.MESSAGE_PERSON_NRIC_NOT_FOUND);
    }

    @Test
    public void equals() {

        CancelAppCommand cancelFirstAppointment = new CancelAppCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );
        CancelAppCommand cancelSecondAppointment = new CancelAppCommand(
                ALICE_APPT_1.getNric(),
                ALICE_APPT_1.getDate(),
                ALICE_APPT_1.getTimePeriod()
        );

        // same object -> returns true
        assertTrue(cancelFirstAppointment.equals(cancelFirstAppointment));

        //same values --> returns true
        CancelAppCommand cancelFirstAppointmentCopy = new CancelAppCommand(
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
        CancelAppCommand cancelAppCommand = new CancelAppCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );

        String expected = CancelAppCommand.class.getCanonicalName()
                + "{nric=" + ALICE_APPT.getNric() + ", "
                + "date=" + ALICE_APPT.getDate() + ", "
                + "timePeriod=" + ALICE_APPT.getTimePeriod() + "}";
        assertEquals(expected, cancelAppCommand.toString());
    }
}
