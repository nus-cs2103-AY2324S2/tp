package seedu.address.model.appointment;

import seedu.address.model.appointment.Appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code studentId} matches the index given.
 */
public class AppointmentIdContainsIndexPredicate implements Predicate<Appointment> {
    private final int appointmentId;

    public AppointmentIdContainsIndexPredicate(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointmentId == appointment.getAppointmentId();
    }
}
