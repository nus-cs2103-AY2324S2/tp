package seedu.address.model.appointment.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class InvalidAppointmentException extends RuntimeException {

    public InvalidAppointmentException() {
        super("This appointment is invalid due to invalid inputs.");
    }
}
