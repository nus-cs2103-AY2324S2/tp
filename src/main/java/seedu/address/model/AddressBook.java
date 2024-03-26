package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.date.Date;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.appointment.AppointmentView;
import seedu.address.model.appointment.AppointmentViewList;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.UniquePatientList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePatient comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePatientList patients;
    private final AppointmentList appointments;
    private final AppointmentViewList appointmentView;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        patients = new UniquePatientList();
        appointments = new AppointmentList();
        appointmentView = new AppointmentViewList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Patients in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPatients(newData.getPatientList());
        setAppointments(newData.getAppointmentList());
    }

    //// patient-level operations

    /**
     * Returns true if a patient with the same name as {@code patient} exists in the address book.
     */
    public boolean hasPatientWithNric(Nric nric) {
        requireNonNull(nric);
        return patients.hasPatientWithNric(nric);
    }

    /**
     * Returns true if a patient with the same name as {@code patient} exists in the address book.
     */
    public Patient getPatientWithNric(Nric nric) {
        requireNonNull(nric);
        return patients.getPatientWithNric(nric);
    }

    /**
     * Deletes if a patient with the same nric as {@code nric} exists in the address book.
     */
    public void deletePatientWithNric(Nric nric) {
        requireNonNull(nric);
        patients.deletePatientWithNric(nric);
        appointments.deleteAppointmentsWithNric(nric);
    }

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Adds a patient to the address book.
     * The patient must not already exist in the address book.
     */
    public void addPatient(Patient p) {
        patients.add(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as
     * another existing patient in the address book.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
    }

    //// appointment-level operations

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
        List<AppointmentView> apptListView = new ArrayList<AppointmentView>();
        for (Appointment appointment : appointments) {
            AppointmentView apptView = createAppointmentView(appointment);
            apptListView.add(apptView);
        }
        this.appointmentView.setAppointmentViews(apptListView);
    }


    /**
     * Returns true if an appointment with the same identity as {@code appointment}
     * exists in the address book.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds an appointment to the address book.
     * The appointment must not already exist in the address book.
     */
    public void addAppointment(Appointment appt) {
        appointments.add(appt);
        AppointmentView apptView = createAppointmentView(appt);
        appointmentView.add(apptView);
    }

    /**
     * Adds an appointmentView to the address book.
     * The appointment must not already exist in the address book.
     */
    public void addAppointmentView(AppointmentView apptView) {
        appointmentView.add(apptView);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedAppointment} must not be the same as another
     * existing patient in the address book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);
        appointments.setAppointment(target, editedAppointment);
        AppointmentView targetApptView = createAppointmentView(target);
        AppointmentView editedApptView = createAppointmentView(editedAppointment);
        appointmentView.setAppointmentView(targetApptView, editedApptView);

    }

    /**
     * Cancels {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void cancelAppointment(Appointment key) {
        appointments.remove(key);
        appointmentView.remove(new AppointmentView(getPatientWithNric(key.getNric()).getName(), key));
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patients", patients)
                .toString();
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<AppointmentView> getAppointmentViewList() {
        return appointmentView.asUnmodifiableObservableList();
    }

    public Appointment getMatchingAppointment(Nric nric, Date date, TimePeriod timePeriod) {
        return appointments.getMatchingAppointment(nric, date, timePeriod);
    }

    /**
     * Create AppointmentView from appointment
     */
    public AppointmentView createAppointmentView(Appointment appointment) {
        Patient patient = getPatientWithNric(appointment.getNric());
        return new AppointmentView(patient.getName(), appointment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return patients.equals(otherAddressBook.patients)
                && appointments.equals(otherAddressBook.appointments);
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }
}
