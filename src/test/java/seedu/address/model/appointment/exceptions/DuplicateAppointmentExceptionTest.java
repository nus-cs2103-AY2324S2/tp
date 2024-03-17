package seedu.address.model.appointment.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DuplicateAppointmentExceptionTest {
    @Test
    public void duplicateAppointmentException_createNewException_errorMessageIsAccurate() {
        DuplicateAppointmentException e = new DuplicateAppointmentException();

        assertEquals("Operation would result in duplicate appointments", e.getMessage());
    }
}
