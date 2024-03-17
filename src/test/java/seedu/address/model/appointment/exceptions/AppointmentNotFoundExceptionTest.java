package seedu.address.model.appointment.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AppointmentNotFoundExceptionTest {
    @Test
    public void appointmentNotFoundException_createNewException_errorMessageIsAccurate() {
        AppointmentNotFoundException e = new AppointmentNotFoundException();

        assertEquals("Unable to locate appointment", e.getMessage());
    }
}
