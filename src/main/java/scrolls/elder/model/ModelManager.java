package scrolls.elder.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.logging.Logger;

import scrolls.elder.commons.core.GuiSettings;
import scrolls.elder.commons.core.LogsCenter;
import scrolls.elder.commons.util.CollectionUtil;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final Datastore datastore;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyDatastore datastore, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(datastore, userPrefs);

        logger.fine("Initializing with datastore: " + datastore + " and user prefs " + userPrefs);

        this.datastore = new Datastore(datastore);
        this.userPrefs = new UserPrefs(userPrefs);
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
        return userPrefs.getDatastoreFilePath();
    }

    @Override
    public void setDatastoreFilePath(Path datastoreFilePath) {
        requireNonNull(datastoreFilePath);
        userPrefs.setDatastoreFilePath(datastoreFilePath);
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

    //=========== Overrides =============================================================

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
            && userPrefs.equals(otherModelManager.userPrefs);
    }

}
