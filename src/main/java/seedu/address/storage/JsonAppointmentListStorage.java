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
import seedu.address.model.appointment.ReadOnlyAppointmentList;

/**
 * A class to access PatientList data stored as a json file on the hard disk.
 */
public class JsonAppointmentListStorage implements AppointmentListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAppointmentListStorage.class);

    private Path appointmentFilePath;

    /**
     * Creates a {@code JsonPatientListStorage} with the given file paths.
     * @param appointmentFilePath the file path for the appointment list data. Can be null.
     */

    public JsonAppointmentListStorage(Path appointmentFilePath) {
        this.appointmentFilePath = appointmentFilePath;
    }

    public Path getAppointmentListFilePath() {
        return appointmentFilePath;
    }

    @Override
    public Optional<ReadOnlyAppointmentList> readAppointmentList() throws DataLoadingException {
        return readAppointmentList(appointmentFilePath);
    }

    /**
     * Similar to {@link #readAppointmentList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyAppointmentList> readAppointmentList(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableAppointmentList> jsonAppointmentList = JsonUtil.readJsonFile(
                filePath, JsonSerializableAppointmentList.class);
        if (!jsonAppointmentList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAppointmentList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    /**
     * Saves the given {@link ReadOnlyAppointmentList} to the storage.
     *
     * @param appointmentList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveAppointmentList(ReadOnlyAppointmentList appointmentList) throws IOException {
        saveAppointmentList(appointmentList, appointmentFilePath);
    }

    /**
     * @see #saveAppointmentList(ReadOnlyAppointmentList)
     */
    @Override
    public void saveAppointmentList(ReadOnlyAppointmentList appointmentList, Path filePath) throws IOException {
        requireNonNull(appointmentList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAppointmentList(appointmentList), filePath);
    }

}

