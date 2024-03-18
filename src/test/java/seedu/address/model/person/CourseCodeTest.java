package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class CourseCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CourseCode(null));
    }

    @Test
    public void constructor_invalidCC_throwsIllegalArgumentException() {
        String invalidCC = "";
        assertThrows(IllegalArgumentException.class, () -> new CourseCode(invalidCC));
    }

    @Test
    public void isValidClass() {
        assertThrows(NullPointerException.class, () -> CourseCode.isValidClass(null));

        assertFalse(CourseCode.isValidClass(""));
        assertFalse(CourseCode.isValidClass(" "));
        assertFalse(CourseCode.isValidClass("^"));
        assertFalse(CourseCode.isValidClass("cs21$$"));

        assertTrue(CourseCode.isValidClass("CS2103"));
        assertTrue(CourseCode.isValidClass("abcd"));
        assertTrue(CourseCode.isValidClass("1234"));
    }

    @Test
    public void equals() {
        CourseCode cc = new CourseCode("CS2103");

        assertTrue(cc.equals(new CourseCode("CS2103")));

        assertTrue(cc.equals(cc));

        assertFalse(cc.equals(null));

        assertFalse(cc.equals(5));

        assertFalse(cc.equals(new CourseCode("nhs2062")));
    }

}
