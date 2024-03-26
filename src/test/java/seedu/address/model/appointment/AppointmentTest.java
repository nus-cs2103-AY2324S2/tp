package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    @Test
    public void getters() {
        AppointmentTime appointmentTime = new AppointmentTime("10/02/2024 11am-2pm");
        Appointment appointment = new Appointment(ALICE.getId(), appointmentTime);

        // same UUID
        assertTrue(appointment.getId() instanceof UUID);

        // same person
        assertTrue(appointment.getPersonId().compareTo(ALICE.getId()) == 0);

        // same time
        assertTrue(appointment.getAppointmentTime().equals(appointmentTime));

        // same time, different object
        assertTrue(appointment.getAppointmentTime().equals(new AppointmentTime("10/02/2024 11am - 2pm")));
    }

    @Test
    public void isSameAppointment() {
        AppointmentTime appointmentTime = new AppointmentTime("10/02/2024 11am-2pm");
        AppointmentTime otherAppointmentTime = new AppointmentTime("11/02/2024 11am-2pm");

        Appointment appointment = new Appointment(ALICE.getId(), appointmentTime);
        Appointment otherPersonAppointment = new Appointment(BOB.getId(), appointmentTime);
        Appointment otherTimeAppointment = new Appointment(ALICE.getId(), otherAppointmentTime);
        // same object -> returns true
        assertTrue(appointment.equals(appointment));

        // different people -> returns false
        assertFalse(appointment.equals(otherPersonAppointment));

        // different time -> returns false
        assertFalse(appointment.equals(otherTimeAppointment));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // not an Appointment object -> returns false
        assertFalse(appointment.equals("not an appointment"));
    }
}
