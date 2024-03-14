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

    private final NetConnect netConnect;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given netConnect and userPrefs.
     */
    public ModelManager(ReadOnlyNetConnect netConnect, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(netConnect, userPrefs);

        logger.fine("Initializing with address book: " + netConnect + " and user prefs " + userPrefs);

        this.netConnect = new NetConnect(netConnect);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.netConnect.getPersonList());
    }

    public ModelManager() {
        this(new NetConnect(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

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
    public Path getNetConnectFilePath() {
        return userPrefs.getNetConnectFilePath();
    }

    @Override
    public void setNetConnectFilePath(Path netConnectFilePath) {
        requireNonNull(netConnectFilePath);
        userPrefs.setNetConnectFilePath(netConnectFilePath);
    }

    // =========== NetConnect
    // ================================================================================

    @Override
    public void setNetConnect(ReadOnlyNetConnect netConnect) {
        this.netConnect.resetData(netConnect);
    }

    @Override
    public ReadOnlyNetConnect getNetConnect() {
        return netConnect;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return netConnect.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        netConnect.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        netConnect.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        netConnect.setPerson(target, editedPerson);
    }

    // =========== Filtered Person List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of
     * {@code versionedNetConnect}
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
        return netConnect.equals(otherModelManager.netConnect)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
