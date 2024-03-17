package seedu.address.storage;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Represents a storage with readonly access for {@link seedu.address.model.AddressBook}.
 * This is used to access the data of the AddressBook without modifying the data.
 * Used for importing data
 */
public interface ReadOnlyAddressBookStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException;
}
