package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.reservation.Reservation;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = person -> !person.isArchived();
    Predicate<Person> PREDICATE_SHOW_ARCHIVED_PERSONS = Person::isArchived;
    Predicate<Reservation> PREDICATE_SHOW_ALL_RESERVATIONS = unused -> true;

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
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given person into the archive list.
     * {@code person} must not already exist in the address book.
     */
    void addArchivedPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a reservation made by the same person and has the same date and time as
     * {@code reservation} exists in the address book.
     */
    boolean hasReservation(Reservation reservation);

    /**
     * Deletes the given reservation.
     * The reservation must exist in the address book.
     */
    void deleteReservation(Reservation target);

    /**
     * Adds the given reservation.
     * {@code reservation} must not already exist in the address book.
     */
    void addReservation(Reservation reservation);

    /** Returns an unmodifiable view of the filtered reservation list */
    ObservableList<Reservation> getFilteredReservationList();

    /**
     * Updates the filter of the filtered reservation list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredReservationList(Predicate<Reservation> predicate);

    /**
     * Returns true if the clear command was the previously called command.
     * This should be used to check whether the user has the raised the intention to clear all data from
     * the address book.
     *
     * @return true if the clear operation was previously called, false otherwise.
     */
    boolean isAwaitingClear();

    /**
     * Sets the waiting status for clearing the address book.
     * This should be called to when user inputs "clear".
     *
     * @param isAwaitingClear true to indicate a "clear" command has been called, false otherwise.
     */
    void setAwaitingClear(boolean isAwaitingClear);

    /**
     * Returns true if the clear operation has been confirmed.
     * This should be used to check whether the user has confirmed the intention to clear all data from the
     * address book.
     *
     * @return true if the clear operation is confirmed, false otherwise.
     */
    boolean isConfirmClear();

    /**
     * Sets the confirmation status for clearing the address book.
     * This should be called to confirm or cancel the clear operation based on user input.
     *
     * @param isConfirmClear true to set the clear operation as confirmed, false otherwise.
     */
    void setConfirmClear(boolean isConfirmClear);

    /**
     * Checks if the user is currently viewing the archived list.
     * This should be used by operations that behave differently depending
     * on whether the user is viewing the archived list or the active persons list,
     * allowing them to determine the appropriate list to act upon.
     *
     * @return true if the user is viewing the archived list, false if the user is viewing the active persons list.
     */
    boolean isViewingArchivedList();

    /**
     * Sets the view state to indicate whether the user is viewing the archived list.
     * This should be used to toggle between viewing the active persons list and the archived persons list.
     * When set to true, operations that depend on the current view state should act on the archived list.
     * When set to false, operations should act on the active persons list.
     *
     * @param isViewingArchived true to indicate that the archived list is being viewed,
     *                          false to indicate the active persons list is being viewed.
     */
    void setViewingArchivedList(boolean isViewingArchived);

    /**
     * Archives the specified person by removing them from the active list and adding them to the archived list.
     * The specified person must exist in the active list.
     *
     * @param target The person to be archived.
     */
    void archivePerson(Person target);

    /**
     * Unarchives the specified person by removing them from the archived list and adding them to the
     * active contacts list.
     * The specified person must exist in the archived list.
     *
     * @param target The person to be archived.
     */
    void unarchivePerson(Person target);
}
