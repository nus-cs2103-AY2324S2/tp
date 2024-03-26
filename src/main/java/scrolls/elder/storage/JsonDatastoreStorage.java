package scrolls.elder.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import scrolls.elder.commons.core.LogsCenter;
import scrolls.elder.commons.exceptions.DataLoadingException;
import scrolls.elder.commons.exceptions.IllegalValueException;
import scrolls.elder.commons.util.FileUtil;
import scrolls.elder.commons.util.JsonUtil;
import scrolls.elder.model.ReadOnlyDatastore;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonDatastoreStorage implements DatastoreStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDatastoreStorage.class);

    private final Path filePath;

    public JsonDatastoreStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDatastoreFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDatastore> readDatastore() throws DataLoadingException {
        return readDatastore(filePath);
    }

    /**
     * Similar to {@link DatastoreStorage#readDatastore()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyDatastore> readDatastore(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableDatastore> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableDatastore.class);
        if (jsonAddressBook.isEmpty()) {
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
    public void saveDatastore(ReadOnlyDatastore datastore) throws IOException {
        saveDatastore(datastore, filePath);
    }

    /**
     * Similar to {@link DatastoreStorage#saveDatastore(ReadOnlyDatastore)}.
     *
     * @param datastore
     * @param filePath  location of the data. Cannot be null.
     */
    public void saveDatastore(ReadOnlyDatastore datastore, Path filePath) throws IOException {
        requireNonNull(datastore);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDatastore(datastore), filePath);
    }

}
