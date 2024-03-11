package vitalConnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import vitalConnect.commons.exceptions.DataLoadingException;
import vitalConnect.model.ReadOnlyClinic;

/**
 * Represents a storage for {@link vitalConnect.model.Clinic}.
 */
public interface ClinicStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getClinicFilePath();

    /**
     * Returns Clinic data as a {@link ReadOnlyClinic}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyClinic> readClinic() throws DataLoadingException;

    /**
     * @see #getClinicFilePath()
     */
    Optional<ReadOnlyClinic> readClinic(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyClinic} to the storage.
     * @param clinic cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClinic(ReadOnlyClinic clinic) throws IOException;

    /**
     * @see #saveClinic(ReadOnlyClinic)
     */
    void saveClinic(ReadOnlyClinic clinic, Path filePath) throws IOException;

}
