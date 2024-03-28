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
import seedu.address.model.ReadOnlyPatientList;

/**
 * A class to access PatientList data stored as a json file on the hard disk.
 */
public class JsonPatientListStorage implements PatientListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPatientListStorage.class);

    private Path personsFilePath;

    /**
     * Creates a {@code JsonPatientListStorage} with the given file paths.
     * @param personsFilePath the file path for the person data. Cannot be null.
     */

    public JsonPatientListStorage(Path personsFilePath) {
        this.personsFilePath = personsFilePath;
    }

    public Path getPatientListFilePath() {
        return personsFilePath;
    }

    @Override
    public Optional<ReadOnlyPatientList> readPatientList() throws DataLoadingException {
        return readPatientList(personsFilePath);
    }

    /**
     * Similar to {@link #readPatientList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyPatientList> readPatientList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializablePatientList> jsonPatientList = JsonUtil.readJsonFile(
                filePath, JsonSerializablePatientList.class);
        if (!jsonPatientList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPatientList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }


    @Override
    public void savePatientList(ReadOnlyPatientList patientList) throws IOException {
        savePatientList(patientList, personsFilePath);
    }

    /**
     * Similar to {@link #savePatientList(ReadOnlyPatientList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePatientList(ReadOnlyPatientList addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePatientList(addressBook), filePath);
    }

}
