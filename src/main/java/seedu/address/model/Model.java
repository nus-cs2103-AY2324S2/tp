package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.startup.Startup;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Startup> PREDICATE_SHOW_ALL_STARTUPS = unused -> true;

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
     * Returns true if a startup with the same identity as {@code startup} exists in the address book.
     */
    boolean hasStartup(Startup startup);

    /**
     * Deletes the given startup.
     * The startup must exist in the address book.
     */
    void deleteStartup(Startup target);

    /**
     * Adds the given startup.
     * {@code startup} must not already exist in the address book.
     */
    void addStartup(Startup startup);

    /**
     * Replaces the given startup {@code target} with {@code editedStartup}.
     * {@code target} must exist in the address book.
     * The startup identity of {@code editedStartup} must not be the same as another existing startup
     * in the address book.
     */
    void setStartup(Startup target, Startup editedStartup);

    /** Returns an unmodifiable view of the filtered startup list */
    ObservableList<Startup> getFilteredStartupList();

    /**
     * Updates the filter of the filtered startup list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStartupList(Predicate<Startup> predicate);
}
