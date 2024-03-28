package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyArticleBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook and ArticleBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private ArticleBookStorage articleBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}
     * and {@code ArticleBookStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage,
                          ArticleBookStorage articleBookStorage) {
        this.addressBookStorage = addressBookStorage;
        this.articleBookStorage = articleBookStorage;
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

    @Override
    public Path getArticleBookFilePath() {
        return articleBookStorage.getArticleBookFilePath();
    }
    public Optional<ReadOnlyArticleBook> readArticleBook() throws DataLoadingException {
        return readArticleBook(articleBookStorage.getArticleBookFilePath());
    }

    /**
     * @see #getArticleBookFilePath()
     */
    @Override
    public Optional<ReadOnlyArticleBook> readArticleBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return articleBookStorage.readArticleBook(filePath);
    }

    /**
     * Saves the given {@link ReadOnlyArticleBook} to the storage.
     * @param articleBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveArticleBook(ReadOnlyArticleBook articleBook) throws IOException {
        saveArticleBook(articleBook, articleBookStorage.getArticleBookFilePath());
    }

    /**
     * @see #saveArticleBook(ReadOnlyArticleBook)
     */
    @Override
    public void saveArticleBook(ReadOnlyArticleBook articleBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        articleBookStorage.saveArticleBook(articleBook, filePath);
    }
}
