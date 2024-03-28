package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.ReadOnlyAppointmentList;

/**
 * Manages storage of PatientList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PatientListStorage patientListStorage;

    private AppointmentListStorage appointmentListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PatientListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PatientListStorage patientListStorage, AppointmentListStorage appointmentListStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.patientListStorage = patientListStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.appointmentListStorage = appointmentListStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ PatientList methods ==============================

    @Override
    public Path getPatientListFilePath() {
        return patientListStorage.getPatientListFilePath();
    }

    @Override
    public void savePatientList(ReadOnlyPatientList addressBook) throws IOException {
        savePatientList(addressBook, patientListStorage.getPatientListFilePath());
    }

    @Override
    public void savePatientList(ReadOnlyPatientList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write persons to data file: " + filePath);
        patientListStorage.savePatientList(addressBook, filePath);
    }

    @Override
    public Optional<ReadOnlyPatientList> readPatientList() throws DataLoadingException {
        return readPatientList(patientListStorage.getPatientListFilePath());
    }

    @Override
    public Optional<ReadOnlyPatientList> readPatientList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return patientListStorage.readPatientList(filePath);
    }

    // ================ AppointmentList methods ==============================

    @Override
    public Path getAppointmentListFilePath() {
        return appointmentListStorage.getAppointmentListFilePath();
    }


    @Override
    public Optional<ReadOnlyAppointmentList> readAppointmentList() throws DataLoadingException {
        return readAppointmentList(appointmentListStorage.getAppointmentListFilePath());
    }



    @Override
    public Optional<ReadOnlyAppointmentList> readAppointmentList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read appointments data from file: " + filePath);
        return appointmentListStorage.readAppointmentList(filePath);
    }

    /**
     * @param appointmentList
     * @throws IOException
     */
    @Override
    public void saveAppointmentList(ReadOnlyAppointmentList appointmentList) throws IOException {
        // This saves to a different JSON file.
        saveAppointmentList(appointmentList, appointmentListStorage.getAppointmentListFilePath());
    }

    @Override
    public void saveAppointmentList(ReadOnlyAppointmentList appointmentList, Path filePath) throws IOException {
        logger.fine("Attempting to write appointments to data file: " + filePath);
        appointmentListStorage.saveAppointmentList(appointmentList, filePath);
    }

}
