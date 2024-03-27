package vitalconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import vitalconnect.commons.core.LogsCenter;
import vitalconnect.commons.exceptions.DataLoadingException;
import vitalconnect.model.Appointment;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.ReadOnlyUserPrefs;
import vitalconnect.model.UserPrefs;

/**
 * Manages storage of Clinic data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClinicStorage clinicStorage;
    private UserPrefsStorage userPrefsStorage;
    private AppointmentStorage appointmentStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ClinicStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClinicStorage clinicStorage, UserPrefsStorage userPrefsStorage,
                          AppointmentStorage appointmentStorage) {
        this.clinicStorage = clinicStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.appointmentStorage = appointmentStorage;
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

    // Appointment methods
    @Override
    public Path getAppointmentFilePath() {
        return appointmentStorage.getAppointmentFilePath();
    }

    @Override
    public Optional<List<Appointment>> readAppointments() throws DataLoadingException {
        return appointmentStorage.readAppointments();
    }

    @Override
    public Optional<List<Appointment>> readAppointments(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return appointmentStorage.readAppointments(filePath);
    }

    @Override
    public void saveAppointments(List<Appointment> appointments) throws IOException {
        appointmentStorage.saveAppointments(appointments);
        saveAppointments(appointments, appointmentStorage.getAppointmentFilePath());
    }

    @Override
    public void saveAppointments(List<Appointment> appointments, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        appointmentStorage.saveAppointments(appointments, filePath);
    }



}
