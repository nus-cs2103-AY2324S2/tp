package vitalconnect.model;

import java.time.LocalDateTime;

/**
 * Represents an appointment with a patient. Each appointment has a patient name
 * and a scheduled date and time.
 */
public class Appointment {
    private String patientName;
    private String patientIc;
    private LocalDateTime dateTime;

    /**
     * Constructs an {@code Appointment} with the specified patient name and date/time.
     *
     * @param patientName The name of the patient for the appointment.
     * @param dateTime The date and time of the appointment.
     */
    public Appointment(String patientName, String patientIc, LocalDateTime dateTime) {
        this.patientIc = patientIc;
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
     * Returns the NRIC of the patient associated with this appointment.
     *
     * @return The patient's ic.
     */
    public String getPatientIc() {
        return patientIc;
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

    /**
     * Checks if this appointment is equal to another object. Two appointments are equal
     * if they have the same patient name, patient NRIC, and date/time.
     *
     * @param that The object to compare with this appointment.
     * @return true if the given object represents an appointment equivalent to this appointment, false otherwise.
     */
    @Override
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(that instanceof Appointment)) {
            return false;
        }

        Appointment otherApt = (Appointment) that;
        return patientName.equals(otherApt.patientName)
                && patientIc.equals(otherApt.patientIc)
                && dateTime.equals(otherApt.dateTime);
    }

}

