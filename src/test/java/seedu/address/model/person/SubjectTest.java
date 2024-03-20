package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SubjectTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Subject(null));
    }

    @Test
    public void constructor_invalidSubject_throwsIllegalArgumentException() {
        String invalidSubject = "";
        assertThrows(IllegalArgumentException.class, () -> new Subject(invalidSubject));
    }

    @Test
    public void isValidSubject() {
        // null subject
        assertThrows(NullPointerException.class, () -> Subject.isValidSubject(null));

        // invalid subjects
        assertFalse(Subject.isValidSubject("")); // empty string
        assertFalse(Subject.isValidSubject(" ")); // spaces only
        assertFalse(Subject.isValidSubject("123")); // numeric characters
        assertFalse(Subject.isValidSubject("!@#$%")); // special characters

        // valid subjects
        assertTrue(Subject.isValidSubject("Math"));
        assertTrue(Subject.isValidSubject("English Literature"));
        assertTrue(Subject.isValidSubject("Computer Science"));
        assertTrue(Subject.isValidSubject("Social Studies"));
    }

    @Test
    public void equals() {
        Subject subject = new Subject("Math");

        // same values -> returns true
        assertEquals(subject, new Subject("Math"));

        // same object -> returns true
        assertEquals(subject, subject);

        // null -> returns false
        assertNotEquals(null, subject);

        // different types -> returns false
        assertNotEquals(5.0f, subject, String.valueOf(0.0));

        // different values -> returns false
        assertNotEquals(subject, new Subject("Science"));
    }
}
