package staffconnect.model;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import staffconnect.commons.core.GuiSettings;
import staffconnect.commons.core.LogsCenter;
import staffconnect.model.person.Person;

/**
 * Represents the in-memory model of the staff book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StaffBook staffBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given staffBook and userPrefs.
     */
    public ModelManager(ReadOnlyStaffBook staffBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(staffBook, userPrefs);

        logger.fine("Initializing with staff book: " + staffBook + " and user prefs " + userPrefs);

        this.staffBook = new StaffBook(staffBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.staffBook.getPersonList());
    }

    public ModelManager() {
        this(new StaffBook(), new UserPrefs());
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
    public Path getStaffConnectFilePath() {
        return userPrefs.getStaffConnectFilePath();
    }

    @Override
    public void setStaffConnectFilePath(Path staffConnectFilePath) {
        requireNonNull(staffConnectFilePath);
        userPrefs.setStaffConnectFilePath(staffConnectFilePath);
    }

    //=========== StaffBook ================================================================================

    @Override
    public void setStaffBook(ReadOnlyStaffBook staffBook) {
        this.staffBook.resetData(staffBook);
    }

    @Override
    public ReadOnlyStaffBook getStaffBook() {
        return staffBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return staffBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        staffBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        staffBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        staffBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedStaffBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
        return staffBook.equals(otherModelManager.staffBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
