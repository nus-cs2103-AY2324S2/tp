package seedu.address.model.appointment.exceptions;

/**
 * Signals that the operation will result in duplicate Appointments
 */
public class DuplicateAppointmentsException extends RuntimeException {
    public DuplicateAppointmentsException() {
        super("Operation would result in duplicate appointments");
    }
}
