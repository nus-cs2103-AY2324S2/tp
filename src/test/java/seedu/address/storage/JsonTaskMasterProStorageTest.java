package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.HOON;
import static seedu.address.testutil.TypicalEmployees.IDA;
import static seedu.address.testutil.TypicalEmployees.getTypicalTaskMasterPro;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyTaskMasterPro;
import seedu.address.model.TaskMasterPro;


public class JsonTaskMasterProStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskMasterProStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskMasterPro_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskMasterPro(null));
    }

    private java.util.Optional<ReadOnlyTaskMasterPro> readTaskMasterPro(String filePath) throws Exception {
        return new JsonTaskMasterProStorage(Paths.get(filePath))
                .readTaskMasterPro(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskMasterPro("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readTaskMasterPro("notJsonFormatTaskMasterPro.json"));
    }

    @Test
    public void readTaskMasterPro_invalidEmployeeTaskMasterPro_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readTaskMasterPro("invalidEmployeeTaskMasterPro.json"));
    }

    @Test
    public void readTaskMasterPro_invalidAndValidEmployeeTaskMasterPro_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readTaskMasterPro("invalidAndValidEmployeeTaskMasterPro.json"));
    }

    @Test
    public void readAndSaveTaskMasterPro_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaskMasterPro.json");
        TaskMasterPro original = getTypicalTaskMasterPro();
        JsonTaskMasterProStorage jsonTaskMasterProStorage = new JsonTaskMasterProStorage(filePath);

        // Save in new file and read back
        jsonTaskMasterProStorage.saveTaskMasterPro(original, filePath);
        ReadOnlyTaskMasterPro readBack = jsonTaskMasterProStorage.readTaskMasterPro(filePath).get();
        assertEquals(original, new TaskMasterPro(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEmployee(HOON);
        original.removeEmployee(ALICE);
        jsonTaskMasterProStorage.saveTaskMasterPro(original, filePath);
        readBack = jsonTaskMasterProStorage.readTaskMasterPro(filePath).get();
        assertEquals(original, new TaskMasterPro(readBack));

        // Save and read without specifying file path
        original.addEmployee(IDA);
        jsonTaskMasterProStorage.saveTaskMasterPro(original); // file path not specified
        readBack = jsonTaskMasterProStorage.readTaskMasterPro().get(); // file path not specified
        assertEquals(original, new TaskMasterPro(readBack));

    }

    @Test
    public void saveTaskMasterPro_nullTaskMasterPro_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskMasterPro(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskMasterPro} at the specified {@code filePath}.
     */
    private void saveTaskMasterPro(ReadOnlyTaskMasterPro taskMasterPro, String filePath) {
        try {
            new JsonTaskMasterProStorage(Paths.get(filePath))
                    .saveTaskMasterPro(taskMasterPro, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskMasterPro_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskMasterPro(new TaskMasterPro(), null));
    }
}
