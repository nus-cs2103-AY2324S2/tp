package staffconnect.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FacultyTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Faculty(null));
    }

    @Test
    public void constructor_invalidFaculty_throwsIllegalArgumentException() {
        String invalidFaculty = "";
        assertThrows(IllegalArgumentException.class, () -> new Faculty(invalidFaculty));
    }

    @Test
    public void isValidFaculty() {
        // null Faculty
        assertThrows(NullPointerException.class, () -> Faculty.isValidFaculty(null));

        // invalid faculties
        assertFalse(Faculty.isValidFaculty("")); // empty string
        assertFalse(Faculty.isValidFaculty(" ")); // spaces only

        // valid Faculties
        assertTrue(Faculty.isValidFaculty("~")); // one character
        assertTrue(Faculty.isValidFaculty("School of Computing")); // long faculty
        assertTrue(Faculty.isValidFaculty("abcdefg"));
    }

    @Test
    public void equals() {
        Faculty faculty = new Faculty("a faculty");

        // same values -> returns true
        assertEquals(faculty, new Faculty("a faculty"));

        // same object -> returns true
        assertEquals(faculty, faculty);

        // null -> returns false
        assertNotEquals(null, faculty);

        // different types -> returns false
        assertFalse(faculty.equals(5.0f));

        // different values -> returns false
        assertNotEquals(faculty, new Faculty("Other Faculty"));
    }
}
