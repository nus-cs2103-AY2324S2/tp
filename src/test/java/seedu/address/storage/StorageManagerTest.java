package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPersons.getTypicalNetConnect;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.NetConnect;
import seedu.address.model.ReadOnlyNetConnect;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonNetConnectStorage netConnectStorage = new JsonNetConnectStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(netConnectStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is
         * properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link
         * JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void netConnectReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is
         * properly wired to the
         * {@link JsonNetConnectStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link
         * JsonNetConnectStorageTest} class.
         */
        NetConnect original = getTypicalNetConnect();
        storageManager.saveNetConnect(original);
        ReadOnlyNetConnect retrieved = storageManager.readNetConnect().get();
        assertEquals(original, new NetConnect(retrieved));
    }

    @Test
    public void getNetConnectFilePath() {
        assertNotNull(storageManager.getNetConnectFilePath());
    }

}
