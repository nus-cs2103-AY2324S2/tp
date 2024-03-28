package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.date.Date;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentView;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;
    Predicate<AppointmentView> PREDICATE_SHOW_ALL_APPOINTMENT_VIEWS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a patient with the same nric as {@code nric} exists in the address book.
     */
    boolean hasPatientWithNric(Nric nric);

    /**
     * Returns patient with the same nric as {@code nric}.
     */
    Patient getPatientWithNric(Nric nric);

    /**
     * Deletes patient with the same nric as {@code nric}.
     */
    void deletePatientWithNric(Nric nric);

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the address book.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the address book.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as
     * another existing patient in the address book.
     */
    void setPatient(Patient target, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in CLInic.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Cancels the given appointment.
     * The appointment must exist in the address book.
     */
    void cancelAppointment(Appointment key);

    /**
     * Adds the given appointment.
     * {@code appointment} must not already exist in CLInic
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the address book.
     * The appointment details of {@code editedAppointment} must
     * not be the same as another existing appointment in CLInic.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /** Returns an unmodifiable view of the filtered appointment view list */
    ObservableList<AppointmentView> getFilteredAppointmentViewList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */

    void updateFilteredAppointmentList(Predicate<AppointmentView> predicate);

    /** Returns an unmodifiable view of the appointment day-view list */
    ObservableList<AppointmentView> getFilteredAppointmentDayViewList();

    /**
     * Updates the appointment day-view list, filtering by today's date
     */
    void updateFilteredAppointmentDayViewList();

    /** Returns an Appointment that matches based on Nric, Date and TimePeriod given **/
    Appointment getMatchingAppointment(Nric nric, Date date, TimePeriod timePeriod);

    /** Deletes all appointments of a targetNric **/
    void deleteAppointmentsWithNric(Nric targetNric);
}
