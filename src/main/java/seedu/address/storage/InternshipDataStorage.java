package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyInternshipData;

/**
 * Represents a storage for {@link seedu.address.model.InternshipData}.
 */
public interface InternshipDataStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternshipDataFilePath();

    /**
     * Returns InternshipData data as a {@link ReadOnlyInternshipData}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyInternshipData> readInternshipData() throws DataLoadingException;

    /**
     * @see #getInternshipDataFilePath()
     */
    Optional<ReadOnlyInternshipData> readInternshipData(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyInternshipData} to the storage.
     * @param internshipData cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternshipData(ReadOnlyInternshipData internshipData) throws IOException;

    /**
     * @see #saveInternshipData(ReadOnlyInternshipData)
     */
    void saveInternshipData(ReadOnlyInternshipData internshipData, Path filePath) throws IOException;
}
