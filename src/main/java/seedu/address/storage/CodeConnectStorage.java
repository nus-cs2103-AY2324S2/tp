package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.CodeConnect;
import seedu.address.model.ReadOnlyCodeConnect;

/**
 * Represents a storage for {@link CodeConnect}.
 */
public interface CodeConnectStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCodeConnectFilePath();

    /**
     * Returns CodeConnect data as a {@link ReadOnlyCodeConnect}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyCodeConnect> readCodeConnect() throws DataLoadingException;

    /**
     * @see #getCodeConnectFilePath()
     */
    Optional<ReadOnlyCodeConnect> readCodeConnect(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyCodeConnect} to the storage.
     * @param codeConnect cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCodeConnect(ReadOnlyCodeConnect codeConnect) throws IOException;

    /**
     * @see #saveCodeConnect(ReadOnlyCodeConnect)
     */
    void saveCodeConnect(ReadOnlyCodeConnect codeConnect, Path filePath) throws IOException;

}
