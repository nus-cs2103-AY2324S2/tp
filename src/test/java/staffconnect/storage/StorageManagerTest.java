package staffconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static staffconnect.testutil.TypicalPersons.getTypicalStaffBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import staffconnect.commons.core.GuiSettings;
import staffconnect.model.ReadOnlyStaffBook;
import staffconnect.model.StaffBook;
import staffconnect.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonStaffBookStorage staffBookStorage = new JsonStaffBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(staffBookStorage, userPrefsStorage);
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
    public void staffBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonStaffBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonStaffBookStorageTest} class.
         */
        StaffBook original = getTypicalStaffBook();
        storageManager.saveStaffBook(original);
        ReadOnlyStaffBook retrieved = storageManager.readStaffBook().get();
        assertEquals(original, new StaffBook(retrieved));
    }

    @Test
    public void getStaffBookFilePath() {
        assertNotNull(storageManager.getStaffBookFilePath());
    }

}
