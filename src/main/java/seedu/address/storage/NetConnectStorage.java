package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyNetConnect;

/**
 * Represents a storage for {@link seedu.address.model.NetConnect}.
 */
public interface NetConnectStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getNetConnectFilePath();

    /**
     * Returns NetConnect data as a {@link ReadOnlyNetConnect}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyNetConnect> readNetConnect() throws DataLoadingException;

    /**
     * @see #getNetConnectFilePath()
     */
    Optional<ReadOnlyNetConnect> readNetConnect(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyNetConnect} to the storage.
     *
     * @param netConnect cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNetConnect(ReadOnlyNetConnect netConnect) throws IOException;

    /**
     * @see #saveNetConnect(ReadOnlyNetConnect)
     */
    void saveNetConnect(ReadOnlyNetConnect netConnect, Path filePath) throws IOException;

}
