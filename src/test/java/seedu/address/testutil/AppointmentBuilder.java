package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.appointment.Appointment;


/**
 * A utility class to help with building Person objects.
 */
public class AppointmentBuilder {

    public static final int DEFAULT_APPOINTMENT_ID = 1;
    public static final LocalDateTime DEFAULT_APPOINTMENT_DATE_TIME = LocalDateTime.of(2020, 12, 12, 12, 12);
    public static final int DEFAULT_STUDENT_ID = 1;

    public static final String DEFAULT_APPOINTMENT_DESCRIPTION = "Appointment Description";

    public static final boolean DEFAULT_HAS_ATTENDED = false;

    private int appointmentId;
    private LocalDateTime appointmentDateTime;
    private int studentId;

    //TODO: replace with caseLog
    private String appointmentDescription;

    private boolean hasAttended;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        appointmentId = DEFAULT_APPOINTMENT_ID;
        appointmentDateTime = DEFAULT_APPOINTMENT_DATE_TIME;
        studentId = DEFAULT_STUDENT_ID;
        appointmentDescription = DEFAULT_APPOINTMENT_DESCRIPTION;
        hasAttended = DEFAULT_HAS_ATTENDED;
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        appointmentId = appointmentToCopy.getAppointmentId();
        appointmentDateTime = appointmentToCopy.getAppointmentDateTime();
        studentId = appointmentToCopy.getStudentId();
        appointmentDescription = appointmentToCopy.getAppointmentDescription();
        hasAttended = appointmentToCopy.getAttendedStatus();
    }

    /**
     * Sets the {@code appointmentId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
        return this;
    }

    /**
     * Set the {@code withAppointmentDateTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentDateTime(LocalDateTime dateTime) {
        this.appointmentDateTime = dateTime;
        return this;
    }

    /**
     * Sets the {@code studentId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStudentId(int studentId) {
        this.studentId = studentId;
        return this;
    }

    /**
     * Sets the {@code AppointmentDescription} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
        return this;
    }

    /**
     * Sets the {@code hasAttended} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withHasAttended(boolean hasAttended) {
        this.hasAttended = hasAttended;
        return this;
    }

    public Appointment build() {
        return new Appointment(appointmentId, appointmentDateTime, studentId, appointmentDescription, hasAttended);
    }

}