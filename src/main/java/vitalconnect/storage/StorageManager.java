package vitalConnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import vitalConnect.commons.core.LogsCenter;
import vitalConnect.commons.exceptions.DataLoadingException;
import vitalConnect.model.ReadOnlyClinic;
import vitalConnect.model.ReadOnlyUserPrefs;
import vitalConnect.model.UserPrefs;

/**
 * Manages storage of Clinic data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClinicStorage clinicStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ClinicStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClinicStorage clinicStorage, UserPrefsStorage userPrefsStorage) {
        this.clinicStorage = clinicStorage;
        this.userPrefsStorage = userPrefsStorage;
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


    // ================ Clinic methods ==============================

    @Override
    public Path getClinicFilePath() {
        return clinicStorage.getClinicFilePath();
    }

    @Override
    public Optional<ReadOnlyClinic> readClinic() throws DataLoadingException {
        return readClinic(clinicStorage.getClinicFilePath());
    }

    @Override
    public Optional<ReadOnlyClinic> readClinic(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return clinicStorage.readClinic(filePath);
    }

    @Override
    public void saveClinic(ReadOnlyClinic clinic) throws IOException {
        saveClinic(clinic, clinicStorage.getClinicFilePath());
    }

    @Override
    public void saveClinic(ReadOnlyClinic clinic, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        clinicStorage.saveClinic(clinic, filePath);
    }

}
