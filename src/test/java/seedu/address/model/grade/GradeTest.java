package seedu.address.model.grade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GradeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Grade(null));
    }

    @Test
    public void isValidMajor() {
        // null Grade
        assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // invalid Grade
        assertFalse(Grade.isValidGrade("#!")); //special characters
        assertFalse(Grade.isValidGrade("21515")); //5 digit Numeric
        assertFalse(Grade.isValidGrade("a9326014")); //Alphanumeric
        assertFalse(Grade.isValidGrade("Chem1351")); //Alphanumeric
        assertFalse(Grade.isValidGrade("2026")); //Intake Year later than Current Year


        // valid Grade
        assertTrue(Grade.isValidGrade("A")); // Correct Format
        assertTrue(Grade.isValidGrade("B"));
        assertTrue(Grade.isValidGrade("D"));
        assertTrue(Grade.isValidGrade("C"));
        assertTrue(Grade.isValidGrade("F"));
        assertTrue(Grade.isValidGrade("a"));
    }

    @Test
    public void stringTest() {
        Grade grade = new Grade("A");
        Grades valid = Grades.A;
        Grades inValid = Grades.B;

        assertTrue(grade.toString().equals(valid.toString()));
        assertFalse(grade.toString().equals(inValid.toString()));
    }

    @Test
    public void hashTest() {
        Grade grade = new Grade("A");
        Grades valid = Grades.A;
        Grades inValid = Grades.B;

        assertTrue(grade.hashCode() == valid.hashCode());
        assertFalse(grade.hashCode() == inValid.hashCode());
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
