package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ImmuniMate immuniMate;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given system and userPrefs.
     */
    public ModelManager(ReadOnlyImmuniMate immuniMate, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(immuniMate, userPrefs);

        logger.fine("Initializing with Immunimate: " + immuniMate + " and user prefs " + userPrefs);

        this.immuniMate = new ImmuniMate(immuniMate);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.immuniMate.getPersonList());
    }

    public ModelManager() {
        this(new ImmuniMate(), new UserPrefs());
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
    public Path getImmunimateFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setImmunimateFilePath(Path immuniMateFilePath) {
        requireNonNull(immuniMateFilePath);
        userPrefs.setAddressBookFilePath(immuniMateFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setImmuniMate(ReadOnlyImmuniMate immuniMate) {
        this.immuniMate.resetData(immuniMate);
    }

    @Override
    public ReadOnlyImmuniMate getImmuniMate() {
        return immuniMate;
    }

    //TODO test cases
    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return immuniMate.hasPerson(person);
    }
    //TODO test cases
    @Override
    public void deletePerson(Person target) {
        immuniMate.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        immuniMate.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        immuniMate.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

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
        return immuniMate.equals(otherModelManager.immuniMate)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
