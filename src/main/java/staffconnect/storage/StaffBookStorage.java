package staffconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import staffconnect.commons.exceptions.DataLoadingException;
import staffconnect.model.ReadOnlyStaffBook;
import staffconnect.model.StaffBook;

/**
 * Represents a storage for {@link StaffBook}.
 */
public interface StaffBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStaffBookFilePath();

    /**
     * Returns StaffBook data as a {@link ReadOnlyStaffBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyStaffBook> readStaffBook() throws DataLoadingException;

    /**
     * @see #getStaffBookFilePath()
     */
    Optional<ReadOnlyStaffBook> readStaffBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyStaffBook} to the storage.
     * @param staffBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStaffBook(ReadOnlyStaffBook staffBook) throws IOException;

    /**
     * @see #saveStaffBook(ReadOnlyStaffBook)
     */
    void saveStaffBook(ReadOnlyStaffBook staffBook, Path filePath) throws IOException;

}
