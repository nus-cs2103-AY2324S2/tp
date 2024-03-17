package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;

public interface ArticleBookStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getArticleBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyArticleBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyArticleBook> readAddressBook() throws DataLoadingException;

    /**
     * @see #getArticleBookFilePath()
     */
    Optional<ReadOnlyArticleBook> readAddressBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyArticleBook addressBook) throws IOException;

    /**
     * @see #saveArticleBook(ReadOnlyArticleBook)
     */
    void saveArticleBook(ReadOnlyArticleBook articleBook, Path filePath) throws IOException;
}
