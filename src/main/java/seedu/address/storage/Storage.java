package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.ReadOnlyAppointmentList;

/**
 * API of the Storage component
 */
public interface Storage extends PatientListStorage, AppointmentListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPatientListFilePath();

    @Override
    Optional<ReadOnlyPatientList> readPatientList() throws DataLoadingException;

    @Override
    void savePatientList(ReadOnlyPatientList patientList) throws IOException;

    @Override
    Path getAppointmentListFilePath();

    @Override
    Optional<ReadOnlyAppointmentList> readAppointmentList() throws DataLoadingException;

    @Override
    void saveAppointmentList(ReadOnlyAppointmentList appointmentList) throws IOException;

}
