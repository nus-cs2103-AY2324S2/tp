package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
}
