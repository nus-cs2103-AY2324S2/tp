package seedu.address.storage;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyArticleBook;

/**
 * Represents a storage for {@link seedu.address.model.ArticleBook}.
 */
public interface ArticleBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getArticleBookFilePath();

    /**
     * Returns ArticleBook data as a {@link ReadOnlyArticleBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyArticleBook> readArticleBook() throws DataLoadingException;

    /**
     * @see #getArticleBookFilePath()
     */
    Optional<ReadOnlyArticleBook> readArticleBook(Path filePath) throws DataLoadingException;
}
