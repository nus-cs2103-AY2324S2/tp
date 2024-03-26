package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;


public class AppointmentTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentTime(null));
    }

    @Test
    public void getters() {
        AppointmentTime appointmentTime = new AppointmentTime("12/01/2024 2pm-4pm");

        // same date
        assertTrue(appointmentTime.getAppointmentDate().equals(LocalDate.of(2024, 1, 12)));

        // same start time
        assertTrue(appointmentTime.getStartTime().equals(LocalTime.of(14, 0)));

        // same end time
        assertTrue(appointmentTime.getEndTime().equals(LocalTime.of(16, 0)));
    }

    @Test
    public void equals() {
        AppointmentTime appointmentTime = new AppointmentTime("12/01/2024 2pm-4pm");

        // same values -> returns true
        assertTrue(appointmentTime.equals(new AppointmentTime("12/01/2024 2pm-4pm")));

        // same object -> returns true
        assertTrue(appointmentTime.equals(appointmentTime));

        // null -> returns false
        assertFalse(appointmentTime.equals(null));

        // different types -> returns false
        assertFalse(appointmentTime.equals(5.0f));

        // different typing -> returns true
        assertTrue(appointmentTime.equals(new AppointmentTime("12/01/2024 2PM - 4PM")));

        // different values -> returns false
        assertFalse(appointmentTime.equals(new AppointmentTime("12/01/2024 2pm-5pm")));
    }
}
