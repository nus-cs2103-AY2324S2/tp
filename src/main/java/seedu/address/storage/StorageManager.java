package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyCodeConnect;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of CodeConnect data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private CodeConnectStorage codeConnectStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code CodeConnectStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CodeConnectStorage codeConnectStorage, UserPrefsStorage userPrefsStorage) {
        this.codeConnectStorage = codeConnectStorage;
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


    // ================ CodeConnect methods ==============================

    @Override
    public Path getCodeConnectFilePath() {
        return codeConnectStorage.getCodeConnectFilePath();
    }

    @Override
    public Optional<ReadOnlyCodeConnect> readCodeConnect() throws DataLoadingException {
        return readCodeConnect(codeConnectStorage.getCodeConnectFilePath());
    }

    @Override
    public Optional<ReadOnlyCodeConnect> readCodeConnect(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return codeConnectStorage.readCodeConnect(filePath);
    }

    @Override
    public void saveCodeConnect(ReadOnlyCodeConnect codeConnect) throws IOException {
        saveCodeConnect(codeConnect, codeConnectStorage.getCodeConnectFilePath());
    }

    @Override
    public void saveCodeConnect(ReadOnlyCodeConnect codeConnect, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        codeConnectStorage.saveCodeConnect(codeConnect, filePath);
    }

}
