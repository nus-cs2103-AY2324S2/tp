package seedu.address.model;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class InternshipUserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        InternshipUserPrefs userPref = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }
    @Test
    public void setInternshipDataFilePath_nullPath_throwsNullPointerException() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setInternshipDataFilePath(null));
    }
    @Test
    public void resetData_nullUserPrefs_throwsNullPointerException() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.resetData(null));
    }
    @Test
    public void hashCode_nonNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.hashCode();
    }
    @Test
    public void equals_nonNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.equals(userPrefs);
    }
    @Test
    public void equals_nullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.equals(userPrefs);
    }
    @Test
    public void equals_differentInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameInternshipDataFilePathAndGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_differentInternshipDataFilePathAndGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_differentInternshipDataFilePathAndSameGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameInternshipDataFilePathAndDifferentGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_differentInternshipDataFilePathAndNullGuiSettings_nullPointer() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        assertThrows(NullPointerException.class, () -> userPrefs2.setGuiSettings(null));
    }

    @Test
    public void equals_nullInternshipDataFilePathAndDifferentGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_nullInternshipDataFilePathAndNullGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameInternshipDataFilePathAndNullGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_nullInternshipDataFilePathAndSameGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

}
