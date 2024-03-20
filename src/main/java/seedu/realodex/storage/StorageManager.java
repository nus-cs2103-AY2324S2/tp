package seedu.realodex.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.realodex.commons.core.LogsCenter;
import seedu.realodex.commons.exceptions.DataLoadingException;
import seedu.realodex.model.ReadOnlyRealodex;
import seedu.realodex.model.ReadOnlyUserPrefs;
import seedu.realodex.model.UserPrefs;

/**
 * Manages storage of Realodex data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private RealodexStorage realodexStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code RealodexStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(RealodexStorage realodexStorage, UserPrefsStorage userPrefsStorage) {
        this.realodexStorage = realodexStorage;
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


    // ================ Realodex methods ==============================

    @Override
    public Path getRealodexFilePath() {
        return realodexStorage.getRealodexFilePath();
    }

    @Override
    public Optional<ReadOnlyRealodex> readRealodex() throws DataLoadingException {
        return readRealodex(realodexStorage.getRealodexFilePath());
    }

    @Override
    public Optional<ReadOnlyRealodex> readRealodex(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return realodexStorage.readRealodex(filePath);
    }

    @Override
    public void saveRealodex(ReadOnlyRealodex realodex) throws IOException {
        saveRealodex(realodex, realodexStorage.getRealodexFilePath());
    }

    @Override
    public void saveRealodex(ReadOnlyRealodex realodex, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        realodexStorage.saveRealodex(realodex, filePath);
    }

}
