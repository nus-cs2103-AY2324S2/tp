package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyClassBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    private ClassBookStorage classBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage,
                          ClassBookStorage classBookStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.classBookStorage = classBookStorage;
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
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    //classBook methods

    @Override
    public Path getClassBookFilePath() {
        return classBookStorage.getClassBookFilePath();
    }

    @Override
    public Optional<ReadOnlyClassBook> readClassBook() throws DataLoadingException {
        return readClassBook(classBookStorage.getClassBookFilePath());
    }

    @Override
    public Optional<ReadOnlyClassBook> readClassBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return classBookStorage.readClassBook(filePath);
    }

//    @Override
//    public void createJsonFileForEachCC(Optional<JsonSerializableClassBook> classBook) throws IOException,
//            IllegalValueException {
//        classBookStorage.createJsonFileForEachCC(classBook);
//    }
//
//    @Override
//    public void createJsonFileForEachCC(JsonSerializableClassBook classBook) throws IOException, IllegalValueException {
//        classBookStorage.createJsonFileForEachCC(classBook);
//    }

    @Override
    public void saveClassBook(ReadOnlyClassBook classBook) throws IOException, IllegalValueException {
        saveClassBook(classBook, classBookStorage.getClassBookFilePath());
    }

    @Override
    public void saveClassBook(ReadOnlyClassBook classBook, Path filePath) throws IOException, IllegalValueException {
        logger.fine("Attempting to write to data file: " + filePath);
        classBookStorage.saveClassBook(classBook, filePath);
    }
}
