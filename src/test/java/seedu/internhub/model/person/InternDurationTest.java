package seedu.internhub.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InternDurationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InternDuration(null));
    }

    @Test
    public void isValidInternDuration() {
        // null address
        assertThrows(NullPointerException.class, () -> InternDuration.isValidInternDuration(null));

        // invalid addresses
        assertFalse(InternDuration.isValidInternDuration("")); // empty string
        assertFalse(InternDuration.isValidInternDuration(" ")); // spaces only

        // valid addresses
        assertTrue(InternDuration.isValidInternDuration("6 months"));
        assertTrue(InternDuration.isValidInternDuration("-")); // one character
    }

    @Test
    public void equals() {
        InternDuration internDuration = new InternDuration("Valid InternDuration");

        // same values -> returns true
        assertTrue(internDuration.equals(new InternDuration("Valid InternDuration")));

        // same object -> returns true
        assertTrue(internDuration.equals(internDuration));

        // null -> returns false
        assertFalse(internDuration.equals(null));

        // different types -> returns false
        assertFalse(internDuration.equals(5.0f));

        // different values -> returns false
        assertFalse(internDuration.equals(new Address("Other Valid InternDuration")));
    }
}
