package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setPayBackFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setPayBackFilePath(null));
    }

    @Test
    public void hashCode_equalObjects_returnsSameHashCode() {
        UserPrefs userPrefs1 = new UserPrefs();
        UserPrefs userPrefs2 = new UserPrefs();
        userPrefs2.setGuiSettings(userPrefs1.getGuiSettings());
        userPrefs2.setPayBackFilePath(userPrefs1.getPayBackFilePath());

        assertEquals(userPrefs1.hashCode(), userPrefs2.hashCode());
    }

}
