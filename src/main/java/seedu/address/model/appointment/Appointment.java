package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Appointment attached to a Person in AddressBook
 * TODO: check if need to enforcing immutable
 */
public class Appointment implements Comparable<Appointment> {
    //TODO: is there any specific constraints to check in appointment?
    public static final String MESSAGE_CONSTRAINTS =
            "Appointment constraints";
    private static final boolean DEFAULT_ATTENDED_STATUS = false;

    private static int idTracker = 1;
    public final int appointmentId;
    public final LocalDateTime appointmentDateTime;
    public final int studentId;

    //TODO: replace with caseLog
    public final String appointmentDescription;

    private boolean hasAttended;

    /**
     * Constructs a {@code Appointment}.
     *
     * @param appointmentId          unique id of the appointment.
     * @param appointmentDateTime    date and time of the appointment.
     * @param studentId              unique id of the student.
     * @param appointmentDescription description of the appointment.
     * @param hasAttended            whether student has attended the appointment.
     */
    public Appointment(int appointmentId, LocalDateTime appointmentDateTime,
                       int studentId, String appointmentDescription, boolean hasAttended) {
        requireAllNonNull(appointmentDateTime, appointmentDescription);
        this.appointmentId = appointmentId;
        this.appointmentDateTime = appointmentDateTime;
        // Student ID is the same as the person ID
        this.studentId = studentId;
        idTracker = appointmentId + 1;

        this.appointmentDescription = appointmentDescription;
        this.hasAttended = hasAttended;
    }

    /**
     * Constructs a {@code Appointment} with automatically generated id.
     *
     * @param appointmentDateTime    date and time of the appointment.
     * @param studentId              unique id of the student.
     * @param appointmentDescription description of the appointment.
     */
    public Appointment(LocalDateTime appointmentDateTime, int studentId, String appointmentDescription) {
        this(idTracker, appointmentDateTime, studentId, appointmentDescription, DEFAULT_ATTENDED_STATUS);
    }

    /**
     * Constructs a {@code Appointment} with automatically generated id.
     *
     * @param appointmentDateTime    date and time of the appointment.
     * @param studentId              unique id of the student.
     * @param appointmentDescription description of the appointment.
     * @param hasAttended            whether student has attended the appointment.
     */
    public Appointment(LocalDateTime appointmentDateTime, int studentId,
                       String appointmentDescription, boolean hasAttended) {
        this(idTracker, appointmentDateTime, studentId, appointmentDescription, hasAttended);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("appointmentId", appointmentId)
                .add("appointmentDateTime", appointmentDateTime)
                .add("studentId", studentId)
                .add("appointmentDescription", appointmentDescription)
                .add("hasAttended", hasAttended)
                .toString();
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
                && otherAppointment.appointmentDateTime.equals(this.appointmentDateTime)
                && otherAppointment.studentId == this.studentId
                && otherAppointment.appointmentDescription.equals(this.appointmentDescription)
                && otherAppointment.hasAttended == this.hasAttended;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, appointmentDateTime,
                studentId, appointmentDescription, hasAttended);
    }

    public boolean getAttendedStatus() {
        return hasAttended;
    }

    public void setAttendedStatus(boolean hasAttended) {
        this.hasAttended = hasAttended;
    }

    /**
     * Compares the appointment id of this appointment with another appointment.
     *
     * @param other the other appointment to compare with.
     * @return a negative integer, zero, or a positive integer as this appointment id is less than, equal to,
     *         or greater than the other appointment id.
     */
    @Override
    public int compareTo(Appointment other) {
        return this.appointmentId - other.appointmentId;
    }
}
