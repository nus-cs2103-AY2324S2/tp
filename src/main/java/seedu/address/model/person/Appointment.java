package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Arrays;


/**
 * Represents an Appointment attached to a Person in AddressBook
 * Guarantees: immutable
 */
public class Appointment implements Comparable<Appointment> {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointment constraints";

    private static int idTracker = 1;
    public final int appointmentId;
    public final LocalDateTime appointmentDate;
    public final int studentId;
    public final String appointmentDescription;

    /**
     * Constructs a {@code Appointment}.
     *
     * @param appointmentId          unique id of the appointment.
     * @param appointmentDate        date and time of the appointment.
     * @param studentId              unique id of the student.
     * @param appointmentDescription description of the appointment.
     */
    public Appointment(int appointmentId, LocalDateTime appointmentDate, int studentId, String appointmentDescription) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        // Student ID is the same as the person ID
        this.studentId = studentId;
        idTracker = appointmentId + 1;

        this.appointmentDescription = appointmentDescription;
        requireAllNonNull(appointmentDate, appointmentDescription);
    }

    /**
     * Constructs a {@code Appointment} with automatically generated id.
     *
     * @param appointmentDate        date and time of the appointment.
     * @param studentId              unique id of the student.
     * @param appointmentDescription description of the appointment.
     */
    public Appointment(LocalDateTime appointmentDate, int studentId, String appointmentDescription) {
        this(idTracker, appointmentDate, studentId, appointmentDescription);
    }

    @Override
    public String toString() {
        return String.format("Appointment ID: %d\n"
                        + "Appointment Date: %s \n"
                        + "Student ID: %d \n"
                        + "Appointment Description: %s\n",
                appointmentId, appointmentDate.toString(),
                studentId, appointmentDescription);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.appointmentId == this.appointmentId
                && otherAppointment.appointmentDate.equals(this.appointmentDate)
                && otherAppointment.studentId == this.studentId
                && otherAppointment.appointmentDescription.equals(this.appointmentDescription);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[]{appointmentId, studentId});
    }


    /**
     * Compares the appointment id of this appointment with another appointment.
     *
     * @param other the other appointment to compare with.
     * @return a negative integer, zero, or a positive integer as this appointment id is less than, equal to,
     *      or greater than the other appointment id.
     */
    @Override
    public int compareTo(Appointment other) {
        return this.appointmentId - other.appointmentId;
    }
}
