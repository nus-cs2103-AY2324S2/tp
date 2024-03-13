package vitalConnect.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import vitalConnect.commons.core.LogsCenter;
import vitalConnect.commons.exceptions.DataLoadingException;
import vitalConnect.commons.exceptions.IllegalValueException;
import vitalConnect.commons.util.FileUtil;
import vitalConnect.commons.util.JsonUtil;
import vitalConnect.model.ReadOnlyClinic;

/**
 * A class to access Clinic data stored as a json file on the hard disk.
 */
public class JsonClinicStorage implements ClinicStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClinicStorage.class);

    private Path filePath;

    public JsonClinicStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getClinicFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyClinic> readClinic() throws DataLoadingException {
        return readClinic(filePath);
    }

    /**
     * Similar to {@link #readClinic()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyClinic> readClinic(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableClinic> jsonClinic = JsonUtil.readJsonFile(
                filePath, JsonSerializableClinic.class);
        if (!jsonClinic.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonClinic.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveClinic(ReadOnlyClinic clinic) throws IOException {
        saveClinic(clinic, filePath);
    }

    /**
     * Similar to {@link #saveClinic(ReadOnlyClinic)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveClinic(ReadOnlyClinic clinic, Path filePath) throws IOException {
        requireNonNull(clinic);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableClinic(clinic), filePath);
    }

}
