package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.beans.property.ReadOnlyStringProperty;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NusNet;
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

    /**
     * Returns the person in the address book whose NusNet ID matches the given NusNet.
     * @param nusNet The NusNet ID to search for.
     * @return An Optional containing the matched person if found, or an empty Optional otherwise.
     */
    Optional<Person> getPersonByNusNet(NusNet nusNet);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Changes the course code with the one specified */
    void changeCode(String code);

    /**
     * Returns a ReadOnlyStringProperty for the course code.
     * This allows observers to track changes to the course code.
     * @return a read-only string property representing the course code.
     */
    ReadOnlyStringProperty courseCodeProperty();

    /**
     * Returns the file path where the course name data is stored.
     * This method provides the location of the storage file that contains
     * the course name information. The path can be used to read from or write to
     * the file directly.
     *
     * @return the {@link Path} representing the file path of the course name data.
     */
    Path getCourseNameFilePath();

    /**
     * Sets the file path for storing the course name data.
     * This method updates the storage location to the specified file path.
     * Future operations to save the course name will use this new path.
     *
     * @param courseNameFilePath the {@link Path} to the file where the course name data should be saved.
     */
    void setCourseNameFilePath(Path courseNameFilePath);

    /**
     * Saves the given course name information to the storage.
     * This method serializes the {@link ReadOnlyCourseName} object and saves it
     * to the designated storage file. It is used to persist changes to the course
     * name across application runs.
     *
     * @param course the {@link ReadOnlyCourseName} object representing the course name to be saved.
     */
    void setCourseName(ReadOnlyCourseName course);


    /**
     * Retrieves the course name from the storage.
     * This method reads the stored course name data from the file and deserializes it
     * into a {@link CourseName} object. It is used to load the course name information
     * when the application starts or whenever the course name needs to be accessed.
     *
     * @return the {@link CourseName} object representing the course name retrieved from storage
     */
    CourseName getCourseName();

}
