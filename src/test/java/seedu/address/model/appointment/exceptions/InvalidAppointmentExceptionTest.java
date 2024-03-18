package seedu.address.model.appointment.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidAppointmentExceptionTest {
    @Test
    public void invalidAppointmentException_createNewException_errorMessageIsAccurate() {
        InvalidAppointmentException e = new InvalidAppointmentException();

        assertEquals("This appointment is invalid due to invalid inputs.", e.getMessage());
    }
}
