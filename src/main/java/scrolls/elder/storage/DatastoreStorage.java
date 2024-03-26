package scrolls.elder.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import scrolls.elder.commons.exceptions.DataLoadingException;
import scrolls.elder.model.ReadOnlyDatastore;
import scrolls.elder.model.ReadOnlyPersonStore;

/**
 * Represents a storage for {@link Datastore}.
 */
public interface DatastoreStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDatastoreFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyPersonStore}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyDatastore> readDatastore() throws DataLoadingException;

    /**
     * @see #getDatastoreFilePath()
     */
    Optional<ReadOnlyDatastore> readDatastore(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyPersonStore} to the storage.
     *
     * @param datastore cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDatastore(ReadOnlyDatastore datastore) throws IOException;

    /**
     * @see #saveDatastore(ReadOnlyDatastore)
     */
    void saveDatastore(ReadOnlyDatastore datastore, Path filePath) throws IOException;

}
