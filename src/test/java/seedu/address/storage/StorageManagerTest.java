package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalEmployees.getTypicalTaskMasterPro;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ReadOnlyTaskMasterPro;
import seedu.address.model.TaskMasterPro;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTaskMasterProStorage taskMasterProStorage = new JsonTaskMasterProStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(taskMasterProStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void taskMasterProReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTaskMasterProStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTaskMasterProStorageTest} class.
         */
        TaskMasterPro original = getTypicalTaskMasterPro();
        storageManager.saveTaskMasterPro(original);
        ReadOnlyTaskMasterPro retrieved = storageManager.readTaskMasterPro().get();
        assertEquals(original, new TaskMasterPro(retrieved));
    }

    @Test
    public void getTaskMasterProFilePath() {
        assertNotNull(storageManager.getTaskMasterProFilePath());
    }

}
