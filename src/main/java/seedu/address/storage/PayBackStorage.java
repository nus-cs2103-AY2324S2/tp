package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.PayBack;
import seedu.address.model.ReadOnlyPayBack;

/**
 * Represents a storage for {@link PayBack}.
 */
public interface PayBackStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPayBackFilePath();

    /**
     * Returns PayBack data as a {@link ReadOnlyPayBack}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyPayBack> readPayBack() throws DataLoadingException;

    /**
     * @see #getPayBackFilePath()
     */
    Optional<ReadOnlyPayBack> readPayBack(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyPayBack} to the storage.
     * @param payBack cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePayBack(ReadOnlyPayBack payBack) throws IOException;

    /**
     * @see #savePayBack(ReadOnlyPayBack)
     */
    void savePayBack(ReadOnlyPayBack payBack, Path filePath) throws IOException;

}
