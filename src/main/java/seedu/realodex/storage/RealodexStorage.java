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
    Path getRealodexFilePath();

    /**
     * Returns Realodex data as a {@link ReadOnlyRealodex}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyRealodex> readRealodex() throws DataLoadingException;

    /**
     * @see #getRealodexFilePath()
     */
    Optional<ReadOnlyRealodex> readRealodex(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyRealodex} to the storage.
     * @param realodex cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRealodex(ReadOnlyRealodex realodex) throws IOException;

    /**
     * @see #saveRealodex(ReadOnlyRealodex)
     */
    void saveRealodex(ReadOnlyRealodex realodex, Path filePath) throws IOException;

}
