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
    public Path getAddressBookFilePath() {
        return realodexStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyRealodex> readAddressBook() throws DataLoadingException {
        return readAddressBook(realodexStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyRealodex> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return realodexStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyRealodex addressBook) throws IOException {
        saveAddressBook(addressBook, realodexStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyRealodex addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        realodexStorage.saveAddressBook(addressBook, filePath);
    }

}
