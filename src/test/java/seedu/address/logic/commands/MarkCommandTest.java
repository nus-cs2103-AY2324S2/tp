package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT_1;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBookWithAppointments;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class MarkCommandTest {
    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkCommand(null, null, null));
    }

    private Model model = new ModelManager(getTypicalAddressBookWithAppointments(), new UserPrefs());

    @Test
    public void execute_mark_success() {
        MarkCommand markCommand = new MarkCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );

        // Expected message after successful mark
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PERSON_SUCCESS,
                Messages.format(ALICE_APPT));

        assertCommandSuccess(markCommand, model, expectedMessage, model);
    }

    @Test
    public void equals() {

        MarkCommand markFirstAppointment = new MarkCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );
        MarkCommand markSecondAppointment = new MarkCommand(
                ALICE_APPT_1.getNric(),
                ALICE_APPT_1.getDate(),
                ALICE_APPT_1.getTimePeriod()
        );

        // same object -> returns true
        assertTrue(markFirstAppointment.equals(markFirstAppointment));

        //same values --> returns true
        MarkCommand markFirstAppointmentCopy = new MarkCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );
        assertTrue(markFirstAppointment.equals(markFirstAppointmentCopy));


        // different types -> returns false
        assertFalse(markFirstAppointment.equals(1));

        // null -> returns false
        assertFalse(markFirstAppointment.equals(null));

        // different date -> returns false
        assertFalse(markFirstAppointment.equals(markSecondAppointment));
    }

    @Test
    public void toStringMethod() {
        MarkCommand markCommand = new MarkCommand(
                ALICE_APPT.getNric(),
                ALICE_APPT.getDate(),
                ALICE_APPT.getTimePeriod()
        );

        String expected = MarkCommand.class.getCanonicalName()
                + "{nric=" + ALICE_APPT.getNric() + ", "
                + "date=" + ALICE_APPT.getDate() + ", "
                + "timePeriod=" + ALICE_APPT.getTimePeriod() + "}";
        assertEquals(expected, markCommand.toString());
    }
    

}
