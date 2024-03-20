package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CourseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Course(null));
    }

    @Test
    public void constructor_invalidCourse_throwsIllegalArgumentException() {
        String invalidCourse = "12CS20S";
        assertThrows(IllegalArgumentException.class, () -> new Course(invalidCourse));
    }

    @Test
    public void isValidCourse() {
        // null course
        assertThrows(NullPointerException.class, () -> Course.isValidCourse(null));

        // blank course
        assertFalse(Course.isValidCourse("")); // empty string
        assertFalse(Course.isValidCourse(" ")); // spaces only

        // missing parts
        assertFalse(Course.isValidCourse("1234")); // missing alphabet prefix
        assertFalse(Course.isValidCourse("CS")); // missing digits

        // invalid courses
        assertFalse(Course.isValidCourse("C1231")); // at least 2 alphabet prefix
        assertFalse(Course.isValidCourse("CS123")); // cannot have less than 4 digits
        assertFalse(Course.isValidCourse("CS12345")); // cannot have more than 4 digits
        assertFalse(Course.isValidCourse("CS1231CS")); // at most 1 alphabet postfix
        assertFalse(Course.isValidCourse("CS123S1")); // no mixing of digits and alphabets
        assertFalse(Course.isValidCourse("CS0123")); // first digit cannot be less than 1
        assertFalse(Course.isValidCourse("CS9123")); // first digit cannot be more than 8
        assertFalse(Course.isValidCourse("CS-1123")); // no invalid character "-"
        assertFalse(Course.isValidCourse("CS11 23")); // no whitespaces in-between characters

        // valid courses
        assertTrue(Course.isValidCourse("CS1234")); // no alphabet postfix
        assertTrue(Course.isValidCourse("CS1231S")); // 1 alphabet postfix
        assertTrue(Course.isValidCourse("CS1111")); // digits all equal
        assertTrue(Course.isValidCourse("PLS8002B")); // first digit is 8
        assertTrue(Course.isValidCourse("  CS1234  ")); // leading and trailing whitespaces
        assertTrue(Course.isValidCourse("cS1234s")); // small letters
        assertTrue(Course.isValidCourse("CS1999")); // last 3 digits greater than 8
        assertTrue(Course.isValidCourse("  Cs8970s    ")); // mixed of above properties
    }
}
