package vitalconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import vitalconnect.commons.exceptions.DataLoadingException;
import vitalconnect.model.Appointment;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.ReadOnlyUserPrefs;
import vitalconnect.model.UserPrefs;


/**
 * API of the Storage component
 */
public interface Storage extends ClinicStorage, UserPrefsStorage, AppointmentStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getClinicFilePath();

    @Override
    Optional<ReadOnlyClinic> readClinic() throws DataLoadingException;

    @Override
    void saveClinic(ReadOnlyClinic clinic) throws IOException;

    @Override
    Path getAppointmentFilePath();

    @Override
    Optional<List<Appointment>> readAppointments() throws DataLoadingException;

    @Override
    void saveAppointments(List<Appointment> appointments) throws IOException;

}
