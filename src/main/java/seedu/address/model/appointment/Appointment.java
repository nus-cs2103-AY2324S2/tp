package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Appointment class that describes an appointment
 */
public class Appointment {

    private static final String MESSAGE_CONSTRAINTS_INVALID_DATE =
            "Appointment should be made with a date today onwards";

    // The doctor in charge of the appointment
    private final Nric doctorNric;

    // The patient benefiting from the appointment
    private final Nric patientNric;

    // The date of the appointment
    private final AppointmentDate appointmentDate;

    // Message to outputs in case constraints are not met

    private final AppointmentId appointmentId;

    /**
     * Constructs a new appointment instance
     * @param doctorNric doctor in charge
     * @param patientNric patient of the appointment
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
     * Constructs a new appointment instance
     * @param doctorNric doctor in charge
     * @param patientNric patient of the appointment
     * @param appointmentDate date of the appointment
     * @param appointmentId id of the appointment
     */
    public Appointment(Nric doctorNric, Nric patientNric, AppointmentDate appointmentDate,
                       AppointmentId appointmentId) {
        requireAllNonNull(doctorNric, patientNric, appointmentDate);
        checkArgument(isValidAppointment(appointmentDate), MESSAGE_CONSTRAINTS_INVALID_DATE);
        this.doctorNric = doctorNric;
        this.patientNric = patientNric;
        this.appointmentDate = appointmentDate;
        this.appointmentId = appointmentId;
    }

    /**
     * Checks if appointment is valid by comparing appointment date against current date.
     * A valid new appointment can only be in the future, not the past.
     *
     * @param appointmentDate Date to check validity of
     * @return boolean if appointment is valid or not
     */
    public boolean isValidAppointment(AppointmentDate appointmentDate) {
        AppointmentDate currentDate = new AppointmentDate(LocalDate.now());
        return appointmentDate.compareTo(currentDate) > -1;
    }

    /**
     * Gets doctor in charge
     * @return Doctor in charge
     */
    public Nric getDoctorNric() {
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

    /**
     * Checks if appointment is same as input one by comparing persons involved and date.
     * @param appt input appointment to compare current appointment against
     * @return boolean indicating if appointments are the same or not
     */
    public boolean isSameAppointment(Appointment appt) {
        if (appt == this) {
            return true;
        }

        return appt != null
                && appt.getDoctorNric().equals(this.getDoctorNric())
                && appt.getPatientNric().equals(this.getPatientNric())
                && appt.getAppointmentDate().equals(this.getAppointmentDate());
    }

    /**
     * Checks if the given {@code Person} is associated with this appointment either as a doctor or a patient.
     *
     * @param person The {@code Person} to check if associated with this appointment.
     * @return {@code true} if the person's NRIC matches either the doctor's NRIC or the patient's NRIC,
     *         {@code false} otherwise.
     */
    public boolean appointmentContainsPerson(Person person) {
        return person.getNric().equals(this.doctorNric)
                || person.getNric().equals(this.patientNric);
    }

    @Override
    public boolean equals(Object appt) {
        if (appt == this) {
            return true;
        }

        if (!(appt instanceof Appointment)) {
            return false;
        }

        Appointment appointment = (Appointment) appt;

        return appt != null
                && appointment.getDoctorNric().equals(this.getDoctorNric())
                && appointment.getPatientNric().equals(this.getPatientNric())
                && appointment.getAppointmentDate().equals(this.getAppointmentDate());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Date", getAppointmentDate())
                .add("Doctor", getDoctorNric())
                .add("Patient", getPatientNric())
                .add("Id", getAppointmentId())
                .toString();
    }
}
