package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidStringId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStringId));
    }

    @Test
    void isValidStudentId() {
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only
        assertFalse(StudentId.isValidStudentId("91")); // less than 3 numbers
        assertFalse(StudentId.isValidStudentId("phone")); // non-numeric
        assertFalse(StudentId.isValidStudentId("9011p041")); // alphabets within digits
        assertFalse(StudentId.isValidStudentId("9312 1534")); // spaces within digits

        // valid
        assertTrue(StudentId.isValidStudentId("91191")); // exactly 3 numbers
    }

    @Test
    public void equals() {
        StudentId studentId = new StudentId("00001");

        // same values -> returns true
        assertTrue(studentId.equals(new StudentId("00001")));

        // same object -> returns true
        assertTrue(studentId.equals(studentId));

        // null -> returns false
        assertFalse(studentId.equals(null));

        // different types -> returns false
        assertFalse(studentId.equals(5.0f));

        // different values -> returns false
        assertFalse(studentId.equals(new StudentId("91255")));
    }

}
