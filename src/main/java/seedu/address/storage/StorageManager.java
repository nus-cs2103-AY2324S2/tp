package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyPayBack;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of PayBack data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PayBackStorage payBackStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PayBackStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PayBackStorage payBackStorage, UserPrefsStorage userPrefsStorage) {
        this.payBackStorage = payBackStorage;
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


    // ================ PayBack methods ==============================

    @Override
    public Path getPayBackFilePath() {
        return payBackStorage.getPayBackFilePath();
    }

    @Override
    public Optional<ReadOnlyPayBack> readPayBack() throws DataLoadingException {
        return readPayBack(payBackStorage.getPayBackFilePath());
    }

    @Override
    public Optional<ReadOnlyPayBack> readPayBack(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return payBackStorage.readPayBack(filePath);
    }

    @Override
    public void savePayBack(ReadOnlyPayBack payBack) throws IOException {
        savePayBack(payBack, payBackStorage.getPayBackFilePath());
    }

    @Override
    public void savePayBack(ReadOnlyPayBack payBack, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        payBackStorage.savePayBack(payBack, filePath);
    }

}
