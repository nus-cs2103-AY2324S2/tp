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
import seedu.address.model.ReadOnlyPayBack;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonPayBackStorage implements PayBackStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPayBackStorage.class);

    private Path filePath;

    public JsonPayBackStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPayBackFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPayBack> readPayBack() throws DataLoadingException {
        return readPayBack(filePath);
    }

    /**
     * Similar to {@link #readPayBack()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyPayBack> readPayBack(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializablePayBack> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializablePayBack.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void savePayBack(ReadOnlyPayBack payBack) throws IOException {
        savePayBack(payBack, filePath);
    }

    /**
     * Similar to {@link #savePayBack(ReadOnlyPayBack)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePayBack(ReadOnlyPayBack payBack, Path filePath) throws IOException {
        requireNonNull(payBack);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePayBack(payBack), filePath);
    }

}
