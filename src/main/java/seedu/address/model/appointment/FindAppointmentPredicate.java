package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code studentId} matches the index given.
 */
public class FindAppointmentPredicate implements Predicate<Appointment> {
    private final int studentId;
    private final int appointmentId;

    /**
     * Finds appointment based on student and appointment id.
     * @param studentId         target student id.
     * @param appointmentId     target appointment id.
     */
    public FindAppointmentPredicate(int studentId, int appointmentId) {
        this.studentId = studentId;
        this.appointmentId = appointmentId;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointmentId == appointment.getAppointmentId() && studentId == appointment.getStudentId();
    }
}
