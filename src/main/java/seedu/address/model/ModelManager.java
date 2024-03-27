package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.date.Date;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.appointment.AppointmentView;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Appointment> filteredAppointments;
    private final FilteredList<AppointmentView> filteredAppointmentsView;
    private final FilteredList<AppointmentView> filteredAppointmentsDayView;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPatients = new FilteredList<>(this.addressBook.getPatientList());
        this.filteredAppointments = new FilteredList<>(this.addressBook.getAppointmentList());
        this.filteredAppointmentsView = new FilteredList<>(this.addressBook.getAppointmentViewList());
        this.filteredAppointmentsDayView = new FilteredList<>(this.addressBook.getAppointmentViewList());
        this.updateFilteredAppointmentDayViewList();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPatientWithNric(Nric nric) {
        requireNonNull(nric);
        return addressBook.hasPatientWithNric(nric);
    }

    @Override
    public Patient getPatientWithNric(Nric nric) {
        requireNonNull(nric);
        return addressBook.getPatientWithNric(nric);
    }

    @Override
    public void deletePatientWithNric(Nric nric) {
        requireNonNull(nric);
        addressBook.deletePatientWithNric(nric);
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return addressBook.hasPatient(patient);
    }

    @Override
    public void deletePatient(Patient target) {
        addressBook.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        addressBook.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        addressBook.setPatient(target, editedPatient);
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return addressBook.hasAppointment(appointment);
    }

    @Override
    public void cancelAppointment(Appointment appointment, AppointmentView apptView) {
        addressBook.cancelAppointment(appointment, apptView);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        addressBook.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);
        addressBook.setAppointment(target, editedAppointment);
    }

    @Override
    public Appointment getMatchingAppointment(Nric nric, Date date, TimePeriod timePeriod) {
        return addressBook.getMatchingAppointment(nric, date, timePeriod);
    }

    @Override
    public AppointmentView getMatchingAppointmentView(Name name, Appointment appt) {
        return addressBook.getMatchingAppointmentView(name, appt);
    }

    @Override
    public void deleteAppointmentsWithNric(Nric targetNric) {
        requireNonNull(targetNric);
        addressBook.deleteAppointmentsWithNric(targetNric);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    //=========== Filtered Appointment List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public ObservableList<AppointmentView> getFilteredAppointmentViewList() {
        return filteredAppointmentsView;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<AppointmentView> predicate) {
        requireNonNull(predicate);
        filteredAppointmentsView.setPredicate(predicate);
    }

    //=========== Filtered Appointment Day-View List Accessors =====================================================

    @Override
    public ObservableList<AppointmentView> getFilteredAppointmentDayViewList() {
        return filteredAppointmentsDayView;
    }

    @Override
    public void updateFilteredAppointmentDayViewList() {
        Predicate<AppointmentView> predicate = new AppointmentContainsKeywordsPredicate(
            Optional.empty(),
            Optional.of(new Date(LocalDate.now().toString())),
            Optional.empty());
        filteredAppointmentsDayView.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPatients.equals(otherModelManager.filteredPatients);
    }

}
