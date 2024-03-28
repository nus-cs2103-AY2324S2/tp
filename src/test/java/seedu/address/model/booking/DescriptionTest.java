package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

public class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string

        // valid description
        assertTrue(Description.isValidDescription("peter jack")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only

    }

    @Test
    public void equals() {
        Description description = new Description("Valid description");

        // same values -> returns true
        assertTrue(description.equals(new Description("Valid description")));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals(new Name("Other Valid Name")));
    }

    @Test
    public void hashCode_test() {
        Description description1 = new Description("Meeting");
        Description description2 = new Description("Meeting");
        Description description3 = new Description("Appointment");

        // Same descriptions should have the same hash code
        assertEquals(description1.hashCode(), description2.hashCode());

        // Different descriptions should ideally have different hash codes
        assertNotEquals(description1.hashCode(), description3.hashCode());
    }
}
