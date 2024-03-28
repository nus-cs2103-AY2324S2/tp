package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

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
     * Update the person list to display pinned contacts first.
     */
    void updatePinnedPersonList();

    /**
     * Find a contact by their name.
     * @param targetName Refers to the name identifier.
     * @param message Refers to the exception message for the specific command.
     * @return Person that matches the name.
     * @throws CommandException Handles invalid person message.
     */
    Person findByName(Name targetName, String message) throws CommandException;

    /**
     * Find the person by their name.
     * @param targetName Refers to the name identifier.
     * @param message Refers to the exception message for the specific command.
     * @return Person that matches the name.
     * @throws CommandException Handles invalid person message.
     */
    Person findPersonByName(Name targetName, String message) throws CommandException;

    /**
     * Find the maintainer by their name.
     * @param targetName Refers to the name identifier.
     * @param message Refers to the exception message for the specific command.
     * @return Maintainer that matches the name.
     * @throws CommandException Handles invalid maintainer message.
     */
    Maintainer findMaintainerByName(Name targetName, String message) throws CommandException;

    /**
     * Find the staff by their name.
     * @param targetName Refers to the name identifier.
     * @param message Refers to the exception message for the specific command.
     * @return Staff that matches the name.
     * @throws CommandException Handles invalid staff message.
     */
    Staff findStaffByName(Name targetName, String message) throws CommandException;

    /**
     * Find the supplier by their name.
     * @param targetName Refers to the name identifier.
     * @param message Refers to the exception message for the specific command.
     * @return Supplier that matches the name.
     * @throws CommandException Handles invalid supplier message.
     */
    Supplier findSupplierByName(Name targetName, String message) throws CommandException;

    /**
     * Commits new version of AddressBook into VersionedAddressBook Tracker.
     */
    void commitAddressBook();

    /**
     * Retrieves previous version of AddressBook from VersionedAddressBook Tracker.
     */
    void undoAddressBook();

    /**
     * Retrieves future version of AddressBook due to undo from VerssionedAddressBook Tracker.
     */
    void redoAddressBook();

    /**
     * Returns a boolean value to indicate whether undo command is possible to be carried out.
     * @return True if possible to undo, else False.
     */
    boolean canUndo();

    /**
     * Returns a boolean value to indicate whether redo command is possible to be carried out.
     * @return True if possible to redo, else False.
     */
    boolean canRedo();
}
