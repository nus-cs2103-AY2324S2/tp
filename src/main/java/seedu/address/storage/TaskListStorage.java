package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.TaskList;

/**
 * Represents a storage for {@link seedu.address.model.TaskList}.
 */
public interface TaskListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskListFilePath();

    /**
     * Returns TaskList data.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<TaskList> readTaskList() throws DataLoadingException;

    /**
     * @see #getTaskListFilePath()
     */
    Optional<TaskList> readTaskList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link TaskList} to the storage.
     * @param taskList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskList(TaskList taskList) throws IOException;

    /**
     * @see #saveTaskList(TaskList)
     */
    void saveTaskList(TaskList taskList, Path filePath) throws IOException;
}
