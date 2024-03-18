package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudioTest {

    @Test
    public void constructor_validStudioNumber_success() {
        String validStudioNumber = "S2";
        Studio studio = new Studio(validStudioNumber);
        assertEquals(validStudioNumber, studio.studio);
    }

    @Test
    public void constructor_invalidStudioNumber_throwsIllegalArgumentException() {
        String invalidStudioNumber = "InvalidStudio";
        assertThrows(IllegalArgumentException.class, () -> new Studio(invalidStudioNumber));
    }

    @Test
    public void isValidConstructorParam_emptyStudio_returnsTrue() {
        assertTrue(Studio.isValidConstructorParam(""));
    }

    @Test
    public void isValidConstructorParam_validStudio_returnsTrue() {
        assertTrue(Studio.isValidConstructorParam("S2"));
    }

    @Test
    public void isValidConstructorParam_invalidStudio_returnsFalse() {
        assertFalse(Studio.isValidConstructorParam("InvalidStudio"));
    }

    @Test
    public void isValidStudio_validStudio_returnsTrue() {
        assertTrue(Studio.isValidStudio("S2"));
    }

    @Test
    public void isValidStudio_invalidStudio_returnsFalse() {
        assertFalse(Studio.isValidStudio("InvalidStudio"));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Studio studio1 = new Studio("S2");
        Studio studio2 = new Studio("S2");
        assertTrue(studio1.equals(studio2));
    }

    @Test
    public void equals_sameStudio_returnsTrue() {
        Studio studio1 = new Studio("S2");
        assertTrue(studio1.equals(studio1));
    }

    @Test
    public void equals_differentStudio_returnsFalse() {
        Studio studio1 = new Studio("S2");
        Studio studio2 = new Studio("S3");
        assertFalse(studio1.equals(studio2));
    }

    @Test void equals_differentObject_returnsFalse() {
        Studio studio1 = new Studio("S2");
        assertFalse(studio1.equals("studio1"));
    }
}
