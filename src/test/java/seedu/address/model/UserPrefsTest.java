package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void constructor_defaultValues_success() {
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(new GuiSettings(), userPrefs.getGuiSettings());
        assertEquals(Paths.get("data", "addressbook.json"), userPrefs.getAddressBookFilePath());
        assertEquals(Paths.get("data", "classbook.json"), userPrefs.getClassBookFilePath());
    }

    @Test
    public void constructor_withReadOnlyUserPrefs_success() {
        ReadOnlyUserPrefs readOnlyUserPrefs = new UserPrefs();
        UserPrefs userPrefs = new UserPrefs(readOnlyUserPrefs);
        assertEquals(new GuiSettings(), userPrefs.getGuiSettings());
        assertEquals(readOnlyUserPrefs.getAddressBookFilePath(), userPrefs.getAddressBookFilePath());
        assertEquals(readOnlyUserPrefs.getClassBookFilePath(), userPrefs.getClassBookFilePath());
    }

    @Test
    public void setAddressBookFilePath_validPath_success() {
        UserPrefs userPrefs = new UserPrefs();
        Path newPath = Paths.get("new", "path", "to", "addressbook.json");
        userPrefs.setAddressBookFilePath(newPath);
        assertEquals(newPath, userPrefs.getAddressBookFilePath());
    }

    @Test
    public void setClassBookFilePath_validPath_success() {
        UserPrefs userPrefs = new UserPrefs();
        Path newPath = Paths.get("new", "path", "to", "classbook.json");
        userPrefs.setClassBookFilePath(newPath);
        assertEquals(newPath, userPrefs.getClassBookFilePath());
    }

    @Test
    public void equals_sameObject_true() {
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(userPrefs, userPrefs);
    }

    @Test
    public void equals_equalContents_true() {
        UserPrefs userPrefs1 = new UserPrefs();
        UserPrefs userPrefs2 = new UserPrefs();
        assertEquals(userPrefs1, userPrefs2);
    }

    @Test
    public void equals_differentContents_false() {
        UserPrefs userPrefs1 = new UserPrefs();
        UserPrefs userPrefs2 = new UserPrefs();
        userPrefs2.setAddressBookFilePath(Paths.get("different", "path", "to", "addressbook.json"));
        assertNotEquals(userPrefs1, userPrefs2);
    }

    @Test
    public void toString_correctFormat_success() {
        UserPrefs userPrefs = new UserPrefs();
        String expectedString = "Gui Settings : " + userPrefs.getGuiSettings() + "\n"
                + "Local data file location : " + userPrefs.getAddressBookFilePath() + "\n"
                + "Local data file location: " + userPrefs.getClassBookFilePath();
        assertEquals(expectedString, userPrefs.toString());
    }
}
