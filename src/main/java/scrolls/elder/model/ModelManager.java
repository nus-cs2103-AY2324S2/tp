package scrolls.elder.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import scrolls.elder.commons.core.GuiSettings;
import scrolls.elder.commons.core.LogsCenter;
import scrolls.elder.commons.util.CollectionUtil;
import scrolls.elder.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final Datastore datastore;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Person> filteredVolunteers;
    private final FilteredList<Person> filteredBefriendees;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyDatastore datastore, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(datastore, userPrefs);

        logger.fine("Initializing with datastore: " + datastore + " and user prefs " + userPrefs);

        this.datastore = new Datastore(datastore);
        this.userPrefs = new UserPrefs(userPrefs);

        PersonStore personStore = this.datastore.getMutablePersonStore();
        filteredPersons = new FilteredList<>(personStore.getPersonList());

        filteredVolunteers = new FilteredList<>(personStore.getPersonList(), person -> person.isVolunteer());

        filteredBefriendees = new FilteredList<>(personStore.getPersonList(), person -> !(person.isVolunteer()));
    }

    /**
     * Initializes a ModelManager with the default datastore and user preferences.
     */
    public ModelManager() {
        this(new Datastore(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
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
    public Path getDatastoreFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setDatastoreFilePath(Path datastoreFilePath) {
        requireNonNull(datastoreFilePath);
        userPrefs.setAddressBookFilePath(datastoreFilePath);
    }

    //=========== Datastore ================================================================================
    @Override
    public ReadOnlyDatastore getDatastore() {
        return datastore;
    }

    @Override
    public Datastore getMutableDatastore() {
        return datastore;
    }

    @Override
    public void setDatastore(ReadOnlyDatastore d) {
        datastore.resetData(d);
    }

    //=========== Filtered Person List Accessors =============================================================

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Person> getFilteredVolunteerList() {
        return filteredVolunteers;
    }

    @Override
    public ObservableList<Person> getFilteredBefriendeeList() {
        return filteredBefriendees;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
        filteredVolunteers.setPredicate(person -> predicate.test(person) && person.isVolunteer());
        filteredBefriendees.setPredicate(person -> predicate.test(person) && !(person.isVolunteer()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return datastore.equals(otherModelManager.datastore)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
