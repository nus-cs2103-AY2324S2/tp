package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyStaffConnect;

/**
 * Represents a storage for {@link seedu.address.model.StaffConnect}.
 */
public interface AddressBookStorage {

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
