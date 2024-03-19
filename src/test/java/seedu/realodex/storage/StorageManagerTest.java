package seedu.realodex.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.realodex.testutil.TypicalPersons.getTypicalRealodex;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.realodex.commons.core.GuiSettings;
import seedu.realodex.model.ReadOnlyRealodex;
import seedu.realodex.model.Realodex;
import seedu.realodex.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonRealodexStorage realodexStorage = new JsonRealodexStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(realodexStorage, userPrefsStorage);
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
    public void realodexReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonRealodexStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonRealodexStorageTest} class.
         */
        Realodex original = getTypicalRealodex();
        storageManager.saveRealodex(original);
        ReadOnlyRealodex retrieved = storageManager.readRealodex().get();
        assertEquals(original, new Realodex(retrieved));
    }

    @Test
    public void getRealodexFilePath() {
        assertNotNull(storageManager.getRealodexFilePath());
    }

}
