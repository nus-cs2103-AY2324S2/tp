package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyTaskMasterPro;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TaskMasterPro data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TaskMasterProStorage taskMasterProStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskMasterProStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaskMasterProStorage taskMasterProStorage, UserPrefsStorage userPrefsStorage) {
        this.taskMasterProStorage = taskMasterProStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TaskMasterPro methods ==============================

    @Override
    public Path getTaskMasterProFilePath() {
        return taskMasterProStorage.getTaskMasterProFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskMasterPro> readTaskMasterPro() throws DataLoadingException {
        return readTaskMasterPro(taskMasterProStorage.getTaskMasterProFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskMasterPro> readTaskMasterPro(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskMasterProStorage.readTaskMasterPro(filePath);
    }

    @Override
    public void saveTaskMasterPro(ReadOnlyTaskMasterPro taskMasterPro) throws IOException {
        saveTaskMasterPro(taskMasterPro, taskMasterProStorage.getTaskMasterProFilePath());
    }

    @Override
    public void saveTaskMasterPro(ReadOnlyTaskMasterPro taskMasterPro, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskMasterProStorage.saveTaskMasterPro(taskMasterPro, filePath);
    }

}
