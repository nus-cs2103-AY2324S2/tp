package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipUserPrefs;

/**
 * Represents a storage for {@link seedu.address.model.UserPrefs}.
 */
public interface InternshipUserPrefsStorage {

    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getUserPrefsFilePath();

    /**
     * Returns UserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if the loading of data from preference file failed.
     */
    Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException;

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyUserPrefs} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException;

}
