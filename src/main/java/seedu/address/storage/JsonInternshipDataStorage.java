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
import seedu.address.model.ReadOnlyInternshipData;

/**
 * A class to access InternshipData data stored as a json file on the hard disk.
 */
public class JsonInternshipDataStorage implements InternshipDataStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInternshipDataStorage.class);

    private Path filePath;

    public JsonInternshipDataStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInternshipDataFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInternshipData> readInternshipData() throws DataLoadingException {
        return readInternshipData(filePath);
    }

    /**
     * Similar to {@link #readInternshipData()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyInternshipData> readInternshipData(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableInternshipData> jsonInternshipData = JsonUtil.readJsonFile(
                filePath, JsonSerializableInternshipData.class);
        if (!jsonInternshipData.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInternshipData.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveInternshipData(ReadOnlyInternshipData internshipData) throws IOException {
        saveInternshipData(internshipData, filePath);
    }

    /**
     * Similar to {@link #saveInternshipData(ReadOnlyInternshipData)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInternshipData(ReadOnlyInternshipData internshipData, Path filePath) throws IOException {
        requireNonNull(internshipData);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInternshipData(internshipData), filePath);
    }
}
