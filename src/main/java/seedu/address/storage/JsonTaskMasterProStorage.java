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
import seedu.address.model.ReadOnlyTaskMasterPro;

/**
 * A class to access TaskMasterPro data stored as a json file on the hard disk.
 */
public class JsonTaskMasterProStorage implements TaskMasterProStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskMasterProStorage.class);

    private Path filePath;

    public JsonTaskMasterProStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskMasterProFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskMasterPro> readTaskMasterPro() throws DataLoadingException {
        return readTaskMasterPro(filePath);
    }

    /**
     * Similar to {@link #readTaskMasterPro()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyTaskMasterPro> readTaskMasterPro(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskMasterPro> jsonTaskMasterPro = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskMasterPro.class);
        if (!jsonTaskMasterPro.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskMasterPro.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveTaskMasterPro(ReadOnlyTaskMasterPro taskMasterPro) throws IOException {
        saveTaskMasterPro(taskMasterPro, filePath);
    }

    /**
     * Similar to {@link #saveTaskMasterPro(ReadOnlyTaskMasterPro)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskMasterPro(ReadOnlyTaskMasterPro taskMasterPro, Path filePath) throws IOException {
        requireNonNull(taskMasterPro);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskMasterPro(taskMasterPro), filePath);
    }
}
