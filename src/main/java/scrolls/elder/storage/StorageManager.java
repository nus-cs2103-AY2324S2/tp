package scrolls.elder.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import scrolls.elder.commons.core.LogsCenter;
import scrolls.elder.commons.exceptions.DataLoadingException;
import scrolls.elder.model.ReadOnlyDatastore;
import scrolls.elder.model.ReadOnlyUserPrefs;
import scrolls.elder.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final DatastoreStorage datastoreStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code DatatstoreStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(DatastoreStorage datastoreStorage, UserPrefsStorage userPrefsStorage) {
        this.datastoreStorage = datastoreStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getDatastoreFilePath() {
        return datastoreStorage.getDatastoreFilePath();
    }

    @Override
    public Optional<ReadOnlyDatastore> readDatastore() throws DataLoadingException {
        return readDatastore(datastoreStorage.getDatastoreFilePath());
    }

    @Override
    public Optional<ReadOnlyDatastore> readDatastore(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return datastoreStorage.readDatastore(filePath);
    }

    @Override
    public void saveDatastore(ReadOnlyDatastore datastore) throws IOException {
        saveDatastore(datastore, datastoreStorage.getDatastoreFilePath());
    }

    @Override
    public void saveDatastore(ReadOnlyDatastore datastore, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        datastoreStorage.saveDatastore(datastore, filePath);
    }

}
