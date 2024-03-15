package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyNetConnect;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of NetConnect data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final NetConnectStorage netConnectStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code NetConnectStorage} and
     * {@code UserPrefStorage}.
     */
    public StorageManager(NetConnectStorage netConnectStorage, UserPrefsStorage userPrefsStorage) {
        this.netConnectStorage = netConnectStorage;
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

    // ================ NetConnect methods ==============================

    @Override
    public Path getNetConnectFilePath() {
        return netConnectStorage.getNetConnectFilePath();
    }

    @Override
    public Optional<ReadOnlyNetConnect> readNetConnect() throws DataLoadingException {
        return readNetConnect(netConnectStorage.getNetConnectFilePath());
    }

    @Override
    public Optional<ReadOnlyNetConnect> readNetConnect(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return netConnectStorage.readNetConnect(filePath);
    }

    @Override
    public void saveNetConnect(ReadOnlyNetConnect netConnect) throws IOException {
        saveNetConnect(netConnect, netConnectStorage.getNetConnectFilePath());
    }

    @Override
    public void saveNetConnect(ReadOnlyNetConnect netConnect, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        netConnectStorage.saveNetConnect(netConnect, filePath);
    }

}
