package scrolls.elder.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import scrolls.elder.commons.core.GuiSettings;
import scrolls.elder.model.Datastore;
import scrolls.elder.model.ReadOnlyDatastore;
import scrolls.elder.model.UserPrefs;
import scrolls.elder.testutil.TypicalDatastore;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonDatastoreStorage datastoreStorage = new JsonDatastoreStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(datastoreStorage, userPrefsStorage);
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
    public void datastoreReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonDatastoreStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonDatastoreStorageTest} class.
         */
        Datastore original = TypicalDatastore.getTypicalDatastore();
        storageManager.saveDatastore(original);
        ReadOnlyDatastore retrieved = storageManager.readDatastore().get();
        assertEquals(original, new Datastore(retrieved));
    }

    @Test
    public void getDatastoreFilePath() {
        assertNotNull(storageManager.getDatastoreFilePath());
    }

}
