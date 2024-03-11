package staffconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import staffconnect.commons.exceptions.DataLoadingException;
import staffconnect.model.ReadOnlyStaffConnect;

/**
 * Represents a storage for {@link staffconnect.model.StaffConnect}.
 */
public interface StaffConnectStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns StaffConnect data as a {@link ReadOnlyStaffConnect}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyStaffConnect> readAddressBook() throws DataLoadingException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyStaffConnect> readAddressBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyStaffConnect} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyStaffConnect addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyStaffConnect)
     */
    void saveAddressBook(ReadOnlyStaffConnect addressBook, Path filePath) throws IOException;

}
