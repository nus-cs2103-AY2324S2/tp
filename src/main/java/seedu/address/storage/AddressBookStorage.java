package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ImmuniMate;
import seedu.address.model.ReadOnlyImmuniMate;

/**
 * Represents a storage for {@link ImmuniMate}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyImmuniMate}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyImmuniMate> readAddressBook() throws DataLoadingException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyImmuniMate> readAddressBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyImmuniMate} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyImmuniMate addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyImmuniMate)
     */
    void saveAddressBook(ReadOnlyImmuniMate addressBook, Path filePath) throws IOException;

}
