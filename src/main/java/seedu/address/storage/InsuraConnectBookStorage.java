package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InsuraConnectBook;
import seedu.address.model.ReadOnlyInsuraConnectBook;

/**
 * Represents a storage for {@link InsuraConnectBook}.
 */
public interface InsuraConnectBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyInsuraConnectBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyInsuraConnectBook> readAddressBook() throws DataLoadingException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyInsuraConnectBook> readAddressBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyInsuraConnectBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyInsuraConnectBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyInsuraConnectBook)
     */
    void saveAddressBook(ReadOnlyInsuraConnectBook addressBook, Path filePath) throws IOException;

}
