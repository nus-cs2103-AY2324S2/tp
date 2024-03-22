package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
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
     * Adds the given person and updates the filteredList to show all.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given person without updating the filteredList.
     * {@code person} must not already exist in the address book.
     */
    void addPersonKeepFilter(Person person);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Retrieves the last viewed {@link Person} instance.
     * <p>
     * This method returns an {@link Optional} which will be empty if no person has
     * been viewed last, or will contain a reference to the {@link Person} object
     * that was last viewed.
     *
     * @return an {@link Optional} containing the last viewed {@link Person} if such
     *     a person exists, or an empty {@link Optional} if no person has been viewed last.
     */
    Optional<Person> getLastViewedPerson();

    /**
     * Resets the information about the last viewed person.
     */
    void resetLastViewedPerson();

    /**
     * Updates the record of the last viewed person to the specified {@link Person}.
     * <p>
     * This method sets the internally tracked last viewed person to the provided
     * {@link Person} instance.
     *
     * @param p the {@link Person} instance to set as the last viewed person; cannot be {@code null}.
     */
    void updateLastViewedPerson(Person p);
}
