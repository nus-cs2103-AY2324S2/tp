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
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid addresses
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only

        // valid addresses
        assertTrue(StudentId.isValidStudentId("A1111111D"));
        assertTrue(StudentId.isValidStudentId("A3333333D"));
        assertTrue(StudentId
                .isValidStudentId("A2222222D"));
    }

    @Test
    public void equals() {
        StudentId studentId = new StudentId("A1111111D");

        // same values -> returns true
        assertTrue(studentId.equals(new StudentId("A1111111D")));

        // same object -> returns true
        assertTrue(studentId.equals(studentId));

        // null -> returns false
        assertFalse(studentId.equals(null));

        // different types -> returns false
        assertFalse(studentId.equals(5.0f));

        // different values -> returns false
        assertFalse(studentId.equals(new StudentId("A2222222D")));
    }
}
