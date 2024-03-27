package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.PatientList;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.appointment.ReadOnlyAppointmentList;

/**
 * Represents a storage for {@link PatientList}.
 */
public interface PatientListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPatientListFilePath();

    /**
     * Returns PatientList data as a {@link ReadOnlyPatientList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyPatientList> readPatientList() throws DataLoadingException;

    /**
     * @see #getPatientListFilePath()
     */
    Optional<ReadOnlyPatientList> readPatientList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyPatientList} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePatientList(ReadOnlyPatientList addressBook) throws IOException;

    /**
     * @see #savePatientList(ReadOnlyPatientList)
     */
    void savePatientList(ReadOnlyPatientList addressBook, Path filePath) throws IOException;

}
