package educonnect.model.student;

import static educonnect.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidStudentId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStudentId));
    }

    @Test
    public void isValidStudentId() {
        // null address
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid student id
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only
        assertFalse(StudentId.isValidStudentId("A")); // one character
        assertFalse(StudentId.isValidStudentId("A127461242412319824X"));

        // valid student id
        assertTrue(StudentId.isValidStudentId("A1234567H"));
        assertTrue(StudentId.isValidStudentId("A1274612X")); // long id
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void equals() {
        StudentId studentId = new StudentId("A1234567X");

        // same values -> returns true
        assertTrue(studentId.equals(new StudentId("A1234567X")));

        // same object -> returns true
        assertTrue(studentId.equals(studentId));

        // null -> returns false
        assertFalse(studentId.equals(null));

        // different types -> returns false
        assertFalse(studentId.equals(5.0f));

        // different values -> returns false
        assertFalse(studentId.equals(new StudentId("A2345678U")));
    }
}
