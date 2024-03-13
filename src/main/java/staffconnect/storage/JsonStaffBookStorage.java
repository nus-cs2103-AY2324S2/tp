package staffconnect.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import staffconnect.commons.core.LogsCenter;
import staffconnect.commons.exceptions.DataLoadingException;
import staffconnect.commons.exceptions.IllegalValueException;
import staffconnect.commons.util.FileUtil;
import staffconnect.commons.util.JsonUtil;
import staffconnect.model.ReadOnlyStaffBook;

/**
 * A class to access StaffBook data stored as a json file on the hard disk.
 */
public class JsonStaffBookStorage implements StaffBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonStaffBookStorage.class);

    private Path filePath;

    public JsonStaffBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getStaffBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStaffBook> readStaffBook() throws DataLoadingException {
        return readStaffBook(filePath);
    }

    /**
     * Similar to {@link #readStaffBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyStaffBook> readStaffBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableStaffBook> jsonStaffBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableStaffBook.class);
        if (!jsonStaffBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonStaffBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveStaffBook(ReadOnlyStaffBook staffBook) throws IOException {
        saveStaffBook(staffBook, filePath);
    }

    /**
     * Similar to {@link #saveStaffBook(ReadOnlyStaffBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStaffBook(ReadOnlyStaffBook staffBook, Path filePath) throws IOException {
        requireNonNull(staffBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStaffBook(staffBook), filePath);
    }

}
