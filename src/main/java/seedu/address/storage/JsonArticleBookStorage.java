package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyArticleBook;

/**
 * A class to access ArticleBook data stored as a json file
 */
public class JsonArticleBookStorage implements ArticleBookStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonArticleBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getArticleBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyArticleBook> readArticleBook() throws DataLoadingException {
        return readArticleBook(filePath);
    }

    /**
     * Similar to {@link #readArticleBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyArticleBook> readArticleBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableArticleBook> jsonArticleBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableArticleBook.class);
        if (!jsonArticleBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonArticleBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveArticleBook(ReadOnlyArticleBook articleBook) throws IOException {
        saveArticleBook(articleBook, filePath);
    }

    /**
     * Similar to {@link #saveArticleBook(ReadOnlyArticleBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveArticleBook(ReadOnlyArticleBook articleBook, Path filePath) throws IOException {
        requireNonNull(articleBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableArticleBook(articleBook), filePath);
    }
}
