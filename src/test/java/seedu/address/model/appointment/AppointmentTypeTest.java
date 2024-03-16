package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentType(null));
    }

    @Test
    public void constructor_invalidAppointmentTypeName_throwsIllegalArgumentException() {
        String invalidTypeName = "";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentType(invalidTypeName));
    }

    @Test
    public void isValidAppointmentType() {

        // null appointment type
        assertThrows(NullPointerException.class, () -> AppointmentType.isValidAppointmentType(null));

        // invalid appointment type
        assertFalse(AppointmentType.isValidAppointmentType("")); // empty string
        // assertFalse(AppointmentType.isValidAppointmentType("  ")); // blank spaces

        // valid appointment type
        assertTrue(AppointmentType.isValidAppointmentType("X-ray"));
        assertTrue(AppointmentType.isValidAppointmentType("Health Check Up"));
    }

    @Test
    public void equals() {
        AppointmentType appointmentType = new AppointmentType("valid type");

        // same values -> returns true
        assertTrue(appointmentType.equals(new AppointmentType("valid type")));

        // same object -> returns true
        assertTrue(appointmentType.equals(appointmentType));

        // null -> returns false
        assertFalse(appointmentType.equals(null));

        // different types -> returns false
        assertFalse(appointmentType.equals(5.0f));

        // different values -> returns false
        assertFalse(appointmentType.equals(new AppointmentType("another valid type")));
    }

}
