package staffconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import staffconnect.commons.core.LogsCenter;
import staffconnect.commons.exceptions.DataLoadingException;
import staffconnect.model.ReadOnlyStaffBook;
import staffconnect.model.ReadOnlyUserPrefs;
import staffconnect.model.UserPrefs;

/**
 * Manages storage of StaffBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private StaffBookStorage staffBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code StaffBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(StaffBookStorage staffBookStorage, UserPrefsStorage userPrefsStorage) {
        this.staffBookStorage = staffBookStorage;
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


    // ================ StaffBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return staffBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyStaffBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(staffBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyStaffBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return staffBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyStaffBook staffBook) throws IOException {
        saveAddressBook(staffBook, staffBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyStaffBook staffBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        staffBookStorage.saveAddressBook(staffBook, filePath);
    }

}
