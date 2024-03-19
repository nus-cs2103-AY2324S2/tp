package seedu.realodex.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.realodex.commons.core.LogsCenter;
import seedu.realodex.commons.exceptions.DataLoadingException;
import seedu.realodex.commons.exceptions.IllegalValueException;
import seedu.realodex.commons.util.FileUtil;
import seedu.realodex.commons.util.JsonUtil;
import seedu.realodex.model.ReadOnlyRealodex;

/**
 * A class to access Realodex data stored as a json file on the hard disk.
 */
public class JsonRealodexStorage implements RealodexStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRealodexStorage.class);

    private Path filePath;

    public JsonRealodexStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRealodexFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyRealodex> readRealodex() throws DataLoadingException {
        return readRealodex(filePath);
    }

    /**
     * Similar to {@link #readRealodex()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyRealodex> readRealodex(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableRealodex> jsonRealodex = JsonUtil.readJsonFile(
                filePath, JsonSerializableRealodex.class);
        if (!jsonRealodex.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRealodex.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveRealodex(ReadOnlyRealodex realodex) throws IOException {
        saveRealodex(realodex, filePath);
    }

    /**
     * Similar to {@link #saveRealodex(ReadOnlyRealodex)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveRealodex(ReadOnlyRealodex realodex, Path filePath) throws IOException {
        requireNonNull(realodex);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRealodex(realodex), filePath);
    }
}
