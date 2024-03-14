package seedu.address.model.appointment;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Type;

import java.time.LocalDate;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Appointment {

    // The doctor in charge of the appointment
    private final Nric doctorNric;

    // The patient benefiting from the appointment
    private final Nric patientNric;

    // The date of the appointment
    private final AppointmentDate appointmentDate;

    // Message to outputs in case constraints are not met
    private static final String MESSAGE_CONSTRAINTS_INVALID_DATE =
            "Appointment should be made with a date today onwards";

    private final AppointmentId appointmentId;

    /**
     * Constructs a new appointment instance
     * @param doctor doctor in charge
     * @param patient patient of the appointment
     * @param appointmentDate date of the appointment
     */
    public Appointment(Nric doctorNric, Nric patientNric, AppointmentDate appointmentDate) {
        requireAllNonNull(doctorNric, patientNric, appointmentDate);
        checkArgument(isValidAppointment(appointmentDate), MESSAGE_CONSTRAINTS_INVALID_DATE);
        this.doctorNric = doctorNric;
        this.patientNric = patientNric;
        this.appointmentDate = appointmentDate;
        this.appointmentId = new AppointmentId();
    }

    /**
     * Checks if appointment is valid by comparing appointment date against current date.
     * A valid new appointment can only be in the future, not the past.
     *
     * @param appointmentDate Date to check validity of
     * @return boolean if appointment is valid or not
     */
    private boolean isValidAppointment(AppointmentDate appointmentDate) {
        AppointmentDate currentDate = new AppointmentDate(LocalDate.now());
        return appointmentDate.compareTo(currentDate) > -1;
    }

    /**
     * Gets doctor in charge
     * @return Doctor in charge
     */
    public Nric getDoctoNric() {
        return doctorNric;
    }

    /**
     * Gets patient of the appointment
     * @return patient of the appointment
     */
    public Nric getPatientNric() {
        return patientNric;
    }

    public AppointmentId getAppointmentId() {
        return this.appointmentId;
    }

    /**
     * Gets date of the appointment
     * @return date of the appointment
     */
    public AppointmentDate getAppointmentDate() {
        return appointmentDate;
    }

    public boolean isSameAppointment(Appointment appt) {
        if (appt == this) {
            return true;
        }

        return appt != null
                && appt.getDoctoNric().equals(this.getDoctoNric())
                && appt.getPatientNric().equals(this.getPatientNric())
                && appt.getAppointmentDate().equals(this.getAppointmentDate());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Date", getAppointmentDate())
                .add("Doctor", getDoctoNric())
                .add("Patient", getPatientNric())
                .add("Id", getAppointmentId())
                .toString();
    }
}
