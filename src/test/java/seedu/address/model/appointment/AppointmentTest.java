package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NOTE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT_1;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT_TRUE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null, null, null, null, null));
    }

    @Test
    public void isSameAppointment() {
        // same object -> returns true
        assertTrue(ALICE_APPT.isSameAppointment(ALICE_APPT));

        // null -> returns false
        assertFalse(ALICE_APPT.isSameAppointment(null));

        // same nric, date and time, all other attributes different -> returns true
        Appointment editedAliceAppt = new AppointmentBuilder(ALICE_APPT)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_BOB).withNote(VALID_APPOINTMENT_NOTE_BOB)
                .build();
        assertTrue(ALICE_APPT.isSameAppointment(editedAliceAppt));

        // different nric, all other attributes same -> returns false
        editedAliceAppt = new AppointmentBuilder(ALICE_APPT).withNric(VALID_NRIC_BOB).build();
        assertFalse(ALICE_APPT.isSameAppointment(editedAliceAppt));

        // different date, all other attributes same -> returns false
        editedAliceAppt = new AppointmentBuilder(ALICE_APPT).withDate(VALID_APPOINTMENT_DATE_BOB).build();
        assertFalse(ALICE_APPT.isSameAppointment(editedAliceAppt));

        // different time period, all other attributes same -> returns false
        editedAliceAppt = new AppointmentBuilder(ALICE_APPT)
                .withStartTime(VALID_APPOINTMENT_START_TIME_BOB)
                .withEndTime(VALID_APPOINTMENT_END_TIME_BOB)
                .build();
        assertFalse(ALICE_APPT.isSameAppointment(editedAliceAppt));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment aliceApptCopy = new AppointmentBuilder(ALICE_APPT).build();
        assertTrue(ALICE_APPT.equals(aliceApptCopy));

        // same object -> returns true
        assertTrue(ALICE_APPT.equals(ALICE_APPT));

        // null -> returns false
        assertFalse(ALICE_APPT.equals(null));

        // different type -> returns false
        assertFalse(ALICE_APPT.equals(5));

        // different nric -> returns false
        Appointment editedAliceAppt = new AppointmentBuilder(ALICE_APPT)
                .withNric(VALID_NRIC_BOB).build();
        assertFalse(ALICE_APPT.equals(editedAliceAppt));

        // different date -> returns false
        editedAliceAppt = new AppointmentBuilder(ALICE_APPT)
                .withDate(VALID_APPOINTMENT_DATE_BOB).build();
        assertFalse(ALICE_APPT.equals(editedAliceAppt));

        // different time period -> returns false
        editedAliceAppt = new AppointmentBuilder(ALICE_APPT)
                .withStartTime(VALID_APPOINTMENT_START_TIME_BOB)
                .withEndTime(VALID_APPOINTMENT_END_TIME_BOB)
                .build();
        assertFalse(ALICE_APPT.equals(editedAliceAppt));

        // different appointment type -> returns false
        editedAliceAppt = new AppointmentBuilder(ALICE_APPT)
                .withAppointmentType(VALID_APPOINTMENT_TYPE_BOB).build();
        assertFalse(ALICE_APPT.equals(editedAliceAppt));

        // different note -> returns false
        editedAliceAppt = new AppointmentBuilder(ALICE_APPT)
                .withNote(VALID_APPOINTMENT_NOTE_BOB).build();
        assertFalse(ALICE_APPT.equals(editedAliceAppt));

        // different mark -> returns false
        assertFalse(ALICE_APPT.equals(ALICE_APPT_TRUE));
    }

    @Test
    public void toStringMethod() {
        String expected = Appointment.class.getCanonicalName()
                + "{nric=" + ALICE_APPT_1.getNric()
                + ", date=" + ALICE_APPT_1.getDate()
                + ", timePeriod=" + ALICE_APPT_1.getTimePeriod()
                + ", appointmentType=" + ALICE_APPT_1.getAppointmentType()
                + ", note=" + ALICE_APPT_1.getNote()
                + ", mark=" + ALICE_APPT_1.getMark() + "}";
        assertEquals(expected, ALICE_APPT_1.toString());
    }
}
