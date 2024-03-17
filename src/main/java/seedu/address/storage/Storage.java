package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, ArticleBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getArticleBookFilePath();

    @Override
    Optional<ReadOnlyArticleBook> readArticleBook() throws DataLoadingException;

    /**
     * @see #getArticleBookFilePath()
     */
    @Override
    Optional<ReadOnlyArticleBook> readAddressBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param articleBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    void saveArticleBook(ReadOnlyArticleBook articleBook) throws IOException;

    /**
     * @see #saveArticleBook(ReadOnlyArticleBook)
     */
    @Override
    void saveArticleBook(ReadOnlyArticleBook articleBook, Path filePath) throws IOException;



}
