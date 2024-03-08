package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.exceptions.StorageException;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";


    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private final Path filePath;

    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<AddressBook> addressBook = JsonUtil.readJsonFile(filePath, AddressBook.class);
        return addressBook.map(value -> value);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws StorageException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws StorageException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        try {
            FileUtil.createIfMissing(filePath);
            JsonUtil.saveJsonFile(addressBook, filePath);
        } catch (AccessDeniedException e) {
            throw new StorageException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new StorageException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

    }

}
