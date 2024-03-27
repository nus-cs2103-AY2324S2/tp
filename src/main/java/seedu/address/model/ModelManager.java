package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.reservation.Reservation;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Reservation> filteredReservations;

    /** Fields to track clear confirmation */
    private boolean isAwaitingClear;
    private boolean isConfirmClear;

    private boolean isViewingArchivedList;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);

        ObservableList<Person> combinedPersons = FXCollections.observableArrayList();
        combinedPersons.addAll(this.addressBook.getPersonList());
        combinedPersons.addAll(this.addressBook.getArchivedPersonList());
        this.filteredPersons = new FilteredList<>(combinedPersons);
        this.filteredPersons.setPredicate(PREDICATE_SHOW_ALL_PERSONS);

        // Listener for changes in personList
        ListChangeListener<Person> personListChangeListener = change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    updateCombinedPersonsList(combinedPersons);
                }
            }
        };

        // Attach listeners to both lists
        this.addressBook.getPersonList().addListener(personListChangeListener);
        this.addressBook.getArchivedPersonList().addListener(personListChangeListener);

        filteredReservations = new FilteredList<>(this.addressBook.getReservationList());

        this.isConfirmClear = false;
        this.isAwaitingClear = false;
        this.isViewingArchivedList = false;
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    // Method to update the combined list
    private void updateCombinedPersonsList(ObservableList<Person> combinedPersons) {
        combinedPersons.clear();
        combinedPersons.addAll(this.addressBook.getPersonList());
        combinedPersons.addAll(this.addressBook.getArchivedPersonList());
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

    //=========== Persons in AddressBook =====================================================================

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
    }

    // For adding straight into the archive list
    @Override
    public void addArchivedPerson(Person person) {
        requireNonNull(person);
        person.setArchived(true);
        addressBook.addArchivedPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ARCHIVED_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void archivePerson(Person target) {
        addressBook.archivePerson(target);
        target.setArchived(true);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void unarchivePerson(Person target) {
        addressBook.unarchivePerson(target);
        target.setArchived(false);
        updateFilteredPersonList(PREDICATE_SHOW_ARCHIVED_PERSONS);
    }

    //=========== Reservations in AddressBook ================================================================

    @Override
    public boolean hasReservation(Reservation reservation) {
        requireNonNull(reservation);
        return addressBook.hasReservation(reservation);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        addressBook.removeReservation(reservation);
    }

    @Override
    public void addReservation(Reservation reservation) {
        addressBook.addReservation(reservation);
        updateFilteredReservationList(PREDICATE_SHOW_ALL_RESERVATIONS);
    }

    //=========== Filtered Person and Reservation List Accessors =============================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        if (isViewingArchivedList) {
            // Apply the predicate only to archived persons if viewing archived list
            filteredPersons.setPredicate(person -> person.isArchived() && predicate.test(person));
        } else {
            // Apply the predicate only to non-archived persons if not viewing archived list
            filteredPersons.setPredicate(person -> !person.isArchived() && predicate.test(person));
        }
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Reservation> getFilteredReservationList() {
        return filteredReservations;
    }

    @Override
    public void updateFilteredReservationList(Predicate<Reservation> predicate) {
        requireNonNull(predicate);
        filteredReservations.setPredicate(predicate);
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
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

    //=========== Command State Management =============================================================

    @Override
    public boolean isAwaitingClear() {
        return this.isAwaitingClear;
    }

    @Override
    public void setAwaitingClear(boolean isAwaitingClear) {
        this.isAwaitingClear = isAwaitingClear;
    }

    @Override
    public boolean isConfirmClear() {
        return this.isConfirmClear;
    }

    @Override
    public void setConfirmClear(boolean isConfirmClear) {
        this.isConfirmClear = isConfirmClear;
    }

    @Override
    public void setViewingArchivedList(boolean isViewingArchived) {
        this.isViewingArchivedList = isViewingArchived;
    }

    @Override
    public boolean isViewingArchivedList() {
        return this.isViewingArchivedList;
    }
}
