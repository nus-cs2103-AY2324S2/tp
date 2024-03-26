package seedu.address.model.reservation.exceptions;

/**
 * Signals that the operation will result in duplicate Reservations (Reservations are considered duplicates
 * if they are made by the same person and have the same date and time).
 */
public class DuplicateReservationException extends RuntimeException {
    public DuplicateReservationException() {
        super("Operation would result in duplicate reservations");
    }
}
