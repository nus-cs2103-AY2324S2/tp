package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class to help with building Person objects.
 */
public class AppointmentBuilder {

    public static final int DEFAULT_STUDENT_ID = 1;
    public static final LocalDateTime DEFAULT_DATETIME= LocalDateTime.parse("2024-03-18 09:00",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    public static final boolean DEFAULT_ATTENDED = false;
    public static final String DEFAULT_APPOINTMENT_DESCRIPTION = "This is a dummy appointment";
    public static final int DEFAULT_APPOINTMENT_ID = 1;

    private int studentId;
    private LocalDateTime appointmentDateTime;
    private boolean hasAttended;
    private String appointmentDescription;
    private int appointmentId;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        studentId = DEFAULT_STUDENT_ID;
        appointmentDateTime = DEFAULT_DATETIME;
        hasAttended = DEFAULT_ATTENDED;
        appointmentId = DEFAULT_APPOINTMENT_ID;
        appointmentDescription = DEFAULT_APPOINTMENT_DESCRIPTION;
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        studentId = appointmentToCopy.getStudentId();
        appointmentDateTime = appointmentToCopy.getAppointmentDateTime();
        hasAttended = appointmentToCopy.getAttendedStatus();
        appointmentDescription = appointmentToCopy.getAppointmentDescription();
    }

    /**
     * Sets the {@code studentId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStudentId(int studentId) {
        this.studentId = studentId;
        return this;
    }

    /**
     * Sets the {@code appointmentDateTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
        return this;
    }

    /**
     * Sets the {@code hasAttended} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withHasAttended (Boolean hasAttended) {
        this.hasAttended = hasAttended;
        return this;
    }

    /**
     * Sets the {@code appointmentDescription} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
        return this;
    }

    /**
     * Sets the {@code appointmentId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
        return this;
    }

    public Appointment build() {
        return new Appointment(appointmentId, appointmentDateTime, studentId, appointmentDescription, hasAttended);
    }

}
