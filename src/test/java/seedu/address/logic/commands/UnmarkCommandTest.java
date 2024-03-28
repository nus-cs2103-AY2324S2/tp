package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT_1_TRUE;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT_TRUE;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBookWithAppointments;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UnmarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithAppointments(), new UserPrefs());

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkCommand(null, null, null));
    }

    @Test
    public void execute_ummark_success() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(
                ALICE_APPT_TRUE.getNric(),
                ALICE_APPT_TRUE.getDate(),
                ALICE_APPT_TRUE.getTimePeriod()
        );

        // Expected message after successful unmark
        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_PERSON_SUCCESS,
                Messages.format(ALICE_APPT_TRUE));

        assertCommandSuccess(unmarkCommand, model, expectedMessage, model);
    }

    @Test
    public void equals() {

        UnmarkCommand unmarkFirstAppointment = new UnmarkCommand(
                ALICE_APPT_TRUE.getNric(),
                ALICE_APPT_TRUE.getDate(),
                ALICE_APPT_TRUE.getTimePeriod()
        );
        UnmarkCommand unmarkSecondAppointment = new UnmarkCommand(
                ALICE_APPT_1_TRUE.getNric(),
                ALICE_APPT_1_TRUE.getDate(),
                ALICE_APPT_1_TRUE.getTimePeriod()
        );

        // same object -> returns true
        assertTrue(unmarkFirstAppointment.equals(unmarkFirstAppointment));

        //same values --> returns true
        UnmarkCommand unmarkFirstAppointmentCopy = new UnmarkCommand(
                ALICE_APPT_TRUE.getNric(),
                ALICE_APPT_TRUE.getDate(),
                ALICE_APPT_TRUE.getTimePeriod()
        );
        assertTrue(unmarkFirstAppointment.equals(unmarkFirstAppointmentCopy));


        // different types -> returns false
        assertFalse(unmarkFirstAppointment.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstAppointment.equals(null));

        // different date -> returns false
        assertFalse(unmarkFirstAppointment.equals(unmarkSecondAppointment));
    }

    @Test
    public void toStringMethod() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(
                ALICE_APPT_TRUE.getNric(),
                ALICE_APPT_TRUE.getDate(),
                ALICE_APPT_TRUE.getTimePeriod()
        );

        String expected = UnmarkCommand.class.getCanonicalName()
                + "{nric=" + ALICE_APPT_TRUE.getNric() + ", "
                + "date=" + ALICE_APPT_TRUE.getDate() + ", "
                + "timePeriod=" + ALICE_APPT_TRUE.getTimePeriod() + "}";
        assertEquals(expected, unmarkCommand.toString());
    }

}
