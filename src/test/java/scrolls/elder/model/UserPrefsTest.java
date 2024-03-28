package scrolls.elder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import scrolls.elder.testutil.Assert;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setDatastoreFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPrefs.setDatastoreFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setDatastoreFilePath(Paths.get("data/store/file/path"));

        // same object -> returns true
        assertEquals(userPrefs, userPrefs);

        // same values -> returns true
        UserPrefs userPrefsCopy = new UserPrefs(userPrefs);
        assertEquals(userPrefs, userPrefsCopy);

        // null -> returns false
        assertNotEquals(userPrefs, null);

        // different types -> returns false
        assertNotEquals(userPrefs, new Object());

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setDatastoreFilePath(Paths.get("differentFilePath"));
        assertNotEquals(userPrefs, differentUserPrefs);
    }
}
