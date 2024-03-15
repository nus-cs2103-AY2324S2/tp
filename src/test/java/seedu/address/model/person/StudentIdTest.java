package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        // null studentId
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid studentId
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only
        assertFalse(StudentId.isValidStudentId("^")); // only non-alphanumeric characters
        assertFalse(StudentId.isValidStudentId("P012")); // does not follow the format A[0-9][A-Z]

        // valid studentId
        assertTrue(StudentId.isValidStudentId("A0123456X"));
    }

    @Test
    public void equals() {
        StudentId studentId = new StudentId("A0123456X");

        // same values -> returns true
        assertTrue(studentId.equals(new StudentId("A0123456X")));

        // same object -> returns true
        assertTrue(studentId.equals(studentId));

        // null -> returns false
        assertFalse(studentId.equals(null));

        // different types -> returns false
        assertFalse(studentId.equals(5.0f));

        // different values -> returns false
        assertFalse(studentId.equals(new StudentId("A0277658U")));
    }
}
