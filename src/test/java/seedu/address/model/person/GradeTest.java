package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class GradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Grade(null));
    }

    @Test
    public void constructor_invalidGrade_throwsIllegalArgumentException() {
        String invalidGrade = "E";
        assertThrows(IllegalArgumentException.class, () -> new Grade(invalidGrade));
    }

    @Test
    public void isValidGrade() {
        // null grade
        assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // invalid grades
        assertFalse(Grade.isValidGrade("C++")); // no extra symbols
        assertFalse(Grade.isValidGrade("E")); // only from A to D

        // valid grades
        assertTrue(Grade.isValidGrade("B-"));
        assertTrue(Grade.isValidGrade("C+"));
    }

    @Test
    public void equals() {
        Grade grade = new Grade("A");

        // same values -> returns true
        assertTrue(grade.equals(new Grade("A")));

        // same object -> returns true
        assertTrue(grade.equals(grade));

        // null -> returns false
        assertFalse(grade.equals(null));

        // different types -> returns false
        assertFalse(grade.equals(5.0f));

        // different values -> returns false
        assertFalse(grade.equals(new Grade("B")));
    }
}
