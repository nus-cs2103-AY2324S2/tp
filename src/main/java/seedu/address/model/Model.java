package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.ReadOnlyAppointmentList;
import seedu.address.model.patient.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

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
    Path getPatientListFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setPatientListFilePath(Path patientListFilePath);

    /**
     * Replaces address book data with the data in {@code patientList}.
     */
    void setPatientList(ReadOnlyPatientList patientList);

    /** Returns the PatientList */
    ReadOnlyPatientList getPatientList();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Patient patient);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Patient target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Patient patient);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Patient target, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Patient> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Patient> predicate);

    /**
     * Returns true if an appointment with the same details as {@code appointment} exists in the appointment list.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Adds the given appointment.
     * {@code appointment} must not already exist in the appointment list.
     */
    void addAppointment(Appointment appointment);

    /**
     * Returns an unmodifiable view of the filtered appointment list
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /**
     * Deletes the given appointment.
     * The appointment must exist in the appointment list.
     */
    void deleteAppointment(Appointment target);

    /**
     * Get appointments from inside person list
     */
    ReadOnlyAppointmentList getAppointmentList();
}
