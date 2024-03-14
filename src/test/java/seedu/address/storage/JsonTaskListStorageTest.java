package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

public class JsonTaskListStorageTest {

    private static TaskList taskList = new TaskList();
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskListStorageTest");
    @Test
    public void readTaskList_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskList("NonExistentFile.json").isPresent());
    }
    @Test
    public void saveTaskList_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskList(null, "SomeFile.json"));
    }
    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    private Optional<TaskList> readTaskList(String filePath) throws Exception {
        return new JsonTaskListStorage(Paths.get(filePath)).readTaskList(addToTestDataPathIfNotNull(filePath));
    }

    private Optional<TaskList> readTaskList(Path filePath) throws Exception {
        return new JsonTaskListStorage(filePath).readTaskList(filePath);
    }

    private void saveTaskList(TaskList taskList, String filePath) throws IOException {
        try {
            new JsonTaskListStorage(Paths.get(filePath)).saveTaskList(taskList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    private void saveTaskList(TaskList taskList, Path filePath) throws IOException {
        try {
            new JsonTaskListStorage(filePath).saveTaskList(taskList, filePath);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    private static TaskList getTypicalTaskList() {
        taskList.addTask(new Task("test 1"));
        taskList.addTask(new Task("test 2"));
        taskList.addTask(new Task("test 3"));
        return taskList;
    }
}
