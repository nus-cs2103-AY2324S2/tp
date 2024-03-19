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
import seedu.address.model.ReadOnlyNetConnect;

/**
 * A class to access NetConnect data stored as a json file on the hard disk.
 */
public class JsonNetConnectStorage implements NetConnectStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonNetConnectStorage.class);

    private final Path filePath;

    public JsonNetConnectStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getNetConnectFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyNetConnect> readNetConnect() throws DataLoadingException {
        return readNetConnect(filePath);
    }

    /**
     * Similar to {@link #readNetConnect()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyNetConnect> readNetConnect(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableNetConnect> jsonNetConnect = JsonUtil.readJsonFile(
                filePath, JsonSerializableNetConnect.class);
        if (!jsonNetConnect.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonNetConnect.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveNetConnect(ReadOnlyNetConnect netConnect) throws IOException {
        saveNetConnect(netConnect, filePath);
    }

    /**
     * Similar to {@link #saveNetConnect(ReadOnlyNetConnect)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveNetConnect(ReadOnlyNetConnect netConnect, Path filePath) throws IOException {
        requireNonNull(netConnect);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNetConnect(netConnect), filePath);
    }

}
