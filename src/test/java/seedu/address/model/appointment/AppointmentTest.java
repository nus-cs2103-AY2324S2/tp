package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentTest {


    private static final int TEST_APPOINTMENT_ID = 1;
    private static final int TEST_LATER_APPOINTMENT_ID = 2;

    @Test
    public void equals() {
        LocalDateTime appointmentDate = LocalDateTime.now();
        Appointment appointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate,
                1, "Test", false);

        // same values -> returns true
        Appointment appointmentCopy = new Appointment(TEST_APPOINTMENT_ID, appointmentDate,
                1, "Test", false);
        assertEquals(appointment, appointmentCopy);

        // same object -> returns true
        assertEquals(appointment, appointment);

        // null -> returns false
        assertNotEquals(appointment, null);

        // different types -> returns false
        assertNotEquals(5, appointment);

        // different appointmentId -> returns false
        Appointment differentAppointment = new Appointment(TEST_LATER_APPOINTMENT_ID, appointmentDate,
                1, "Test", false);
        assertNotEquals(appointment, differentAppointment);

        // different appointmentDate -> returns false
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate.plusDays(1),
                1, "Test", false);
        assertNotEquals(appointment, differentAppointment);

        // different studentId -> returns false
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate,
                2, "Test", false);
        assertNotEquals(appointment, differentAppointment);

        // different appointmentDescription -> returns false
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate,
                1, "Different", false);
        assertNotEquals(appointment, differentAppointment);

        // different hasAttended -> returns false
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate,
                1, "Test", true);
        assertNotEquals(appointment, differentAppointment);
    }

    @Test
    public void compareTo() {
        LocalDateTime appointmentDate = LocalDateTime.now();


        Appointment appointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate, 1, "Test", false);

        // same values -> returns 0
        Appointment appointmentCopy = new Appointment(TEST_APPOINTMENT_ID, appointmentDate, 1, "Test", false);
        assertEquals(0, appointment.compareTo(appointmentCopy));

        // another Appointment has larger appointmentId -> returns less than 0;
        Appointment differentAppointment = new Appointment(TEST_LATER_APPOINTMENT_ID, appointmentDate.plusDays(1),
                1, "Test", false);
        assertTrue(appointment.compareTo(differentAppointment) < 0);

        // another Appointment has smaller appointmentId -> returns more than 0;
        differentAppointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate, 2, "Test", false);
        appointment = new Appointment(TEST_LATER_APPOINTMENT_ID, appointmentDate.plusDays(1), 1, "Test", false);
        assertTrue(appointment.compareTo(differentAppointment) > 0);
    }

    @Test
    void getAttendedStatus() {
        LocalDateTime appointmentDate = LocalDateTime.now();

        Appointment appointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate, 1, "Test", false);
        assertFalse(appointment.getAttendedStatus());

        appointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate, 1, "Test", true);
        assertTrue(appointment.getAttendedStatus());
    }

    @Test
    void setAttendedStatus() {
        LocalDateTime appointmentDate = LocalDateTime.now();

        Appointment appointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate, 1, "Test", false);
        appointment.setAttendedStatus(true);
        assertTrue(appointment.getAttendedStatus());

        appointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate, 1, "Test", true);
        appointment.setAttendedStatus(true);
        assertTrue(appointment.getAttendedStatus());

        appointment = new Appointment(TEST_APPOINTMENT_ID, appointmentDate, 1, "Test", true);
        appointment.setAttendedStatus(false);
        assertFalse(appointment.getAttendedStatus());

        // default status
        appointment = new Appointment(appointmentDate, 1, "Test");
        assertFalse(appointment.getAttendedStatus());

        appointment = new Appointment(appointmentDate, 1, "Test", true);
        assertTrue(appointment.getAttendedStatus());
    }

    @Test
    void constructorTest() {
        LocalDateTime appointmentDate = LocalDateTime.now();

        //increasing id
        Appointment appointment = new Appointment(appointmentDate, 1, "Test");
        Appointment otherAppointment = new Appointment(appointmentDate, 1, "Test");
        assertTrue(appointment.compareTo(otherAppointment) < 0);
        assertEquals(appointment.appointmentId + 1, otherAppointment.appointmentId);
    }
}
