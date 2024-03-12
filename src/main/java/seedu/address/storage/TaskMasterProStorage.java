package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyTaskMasterPro;
import seedu.address.model.TaskMasterPro;

/**
 * Represents a storage for {@link TaskMasterPro}.
 */
public interface TaskMasterProStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskMasterProFilePath();

    /**
     * Returns TaskMasterPro data as a {@link ReadOnlyTaskMasterPro}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyTaskMasterPro> readTaskMasterPro() throws DataLoadingException;

    /**
     * @see #getTaskMasterProFilePath()
     */
    Optional<ReadOnlyTaskMasterPro> readTaskMasterPro(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyTaskMasterPro} to the storage.
     * @param taskMasterPro cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskMasterPro(ReadOnlyTaskMasterPro taskMasterPro) throws IOException;

    /**
     * @see #saveTaskMasterPro(ReadOnlyTaskMasterPro)
     */
    void saveTaskMasterPro(ReadOnlyTaskMasterPro taskMasterPro, Path filePath) throws IOException;

}
