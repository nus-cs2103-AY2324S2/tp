package seedu.address.model;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

public class InternshipUserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        InternshipUserPrefs userPref = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setInternshipDataFilePath(null));
    }

}
