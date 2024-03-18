package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code studentId} matches the index given.
 */
public class StudentIdContainsIndexPredicate implements Predicate<Appointment> {
    private final int studentId;

    public StudentIdContainsIndexPredicate(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean test(Appointment appointment) {
        return studentId == appointment.getStudentId();
    }
}
