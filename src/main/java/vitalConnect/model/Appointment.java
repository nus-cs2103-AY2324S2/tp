package vitalConnect.model;

import java.time.LocalDateTime;

/**
 * Represents an appointment with a patient. Each appointment has a patient name
 * and a scheduled date and time.
 */
public class Appointment {
    private String patientName;
    private LocalDateTime dateTime;

    /**
     * Constructs an {@code Appointment} with the specified patient name and date/time.
     *
     * @param patientName The name of the patient for the appointment.
     * @param dateTime The date and time of the appointment.
     */
    public Appointment(String patientName, LocalDateTime dateTime) {
        this.patientName = patientName;
        this.dateTime = dateTime;
    }

    /**
     * Returns the name of the patient associated with this appointment.
     *
     * @return The patient's name.
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Returns the date and time of the appointment.
     *
     * @return The date and time of the appointment.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns a string representation of the appointment, including the patient's name
     * and the date and time of the appointment.
     *
     * @return A string representation of the appointment.
     */
    @Override
    public String toString() {

        return "Appointment with " + patientName + " on " + dateTime;
    }

}

