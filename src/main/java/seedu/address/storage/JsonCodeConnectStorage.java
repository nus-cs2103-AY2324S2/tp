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
import seedu.address.model.ReadOnlyCodeConnect;

/**
 * A class to access CodeConnect data stored as a json file on the hard disk.
 */
public class JsonCodeConnectStorage implements CodeConnectStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCodeConnectStorage.class);

    private Path filePath;

    public JsonCodeConnectStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCodeConnectFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCodeConnect> readCodeConnect() throws DataLoadingException {
        return readCodeConnect(filePath);
    }

    /**
     * Similar to {@link #readCodeConnect()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyCodeConnect> readCodeConnect(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableCodeConnect> jsonCodeConnect = JsonUtil.readJsonFile(
                filePath, JsonSerializableCodeConnect.class);
        if (!jsonCodeConnect.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCodeConnect.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveCodeConnect(ReadOnlyCodeConnect codeConnect) throws IOException {
        saveCodeConnect(codeConnect, filePath);
    }

    /**
     * Similar to {@link #saveCodeConnect(ReadOnlyCodeConnect)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCodeConnect(ReadOnlyCodeConnect codeConnect, Path filePath) throws IOException {
        requireNonNull(codeConnect);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCodeConnect(codeConnect), filePath);
    }

}
