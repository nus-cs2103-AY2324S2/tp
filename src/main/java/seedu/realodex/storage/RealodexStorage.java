package seedu.realodex.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.realodex.commons.exceptions.DataLoadingException;
import seedu.realodex.model.ReadOnlyRealodex;
import seedu.realodex.model.Realodex;

/**
 * Represents a storage for {@link Realodex}.
 */
public interface RealodexStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns Realodex data as a {@link ReadOnlyRealodex}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyRealodex> readAddressBook() throws DataLoadingException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyRealodex> readAddressBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyRealodex} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyRealodex addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyRealodex)
     */
    void saveAddressBook(ReadOnlyRealodex addressBook, Path filePath) throws IOException;

}
