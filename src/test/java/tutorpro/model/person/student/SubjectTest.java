package tutorpro.model.person.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tutorpro.testutil.Assert;

public class SubjectTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Subject(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidSubject = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Subject(invalidSubject));
    }

    @Test
    public void isValidAddress() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> Subject.isValidSubject(null));

        // invalid addresses
        assertFalse(Subject.isValidSubject("")); // empty string
        assertFalse(Subject.isValidSubject(" ")); // spaces only

        // valid addresses
        assertTrue(Subject.isValidSubject("Math"));
        assertTrue(Subject.isValidSubject("-")); // one character
    }

    @Test
    public void equals() {
        Subject subject = new Subject("Valid Subject");

        // same values -> returns true
        assertTrue(subject.equals(new Subject("Valid Subject")));

        // same object -> returns true
        assertTrue(subject.equals(subject));

        // null -> returns false
        assertFalse(subject.equals(null));

        // different types -> returns false
        assertFalse(subject.equals(5.0f));

        // different values -> returns false
        assertFalse(subject.equals(new Subject("Other Valid Subject")));
    }
}
