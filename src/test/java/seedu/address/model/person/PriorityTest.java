package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_blankPriority_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Priority(""));; // empty string
        assertThrows(IllegalArgumentException.class, () -> new Priority(" ")); // spaces only
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Priority("invalid"));
        assertThrows(IllegalArgumentException.class, () -> new Priority("x"));
        assertThrows(IllegalArgumentException.class, () -> new Priority("random"));
    }

    @Test
    public void constructor_validPriority_success() {
        // valid priority
        assertEquals(new Priority("low"), new Priority("low")); // full form, lowercase
        assertEquals(new Priority("medium"), new Priority("medium")); // full form, lowercase
        assertEquals(new Priority("high"), new Priority("high")); // full form, lowercase
        assertEquals(new Priority("vip"), new Priority("vip")); // full form, lowercase
        assertEquals(new Priority("l"), new Priority("l")); // short form, lowercase
        assertEquals(new Priority("m"), new Priority("m")); // short form, lowercase
        assertEquals(new Priority("h"), new Priority("h")); // short form, lowercase
        assertEquals(new Priority("v"), new Priority("v")); // short form, lowercase
        assertEquals(new Priority("LOW"), new Priority("LOW")); // full form, uppercase
        assertEquals(new Priority("MEDIUM"), new Priority("MEDIUM")); // full form, uppercase
        assertEquals(new Priority("HIGH"), new Priority("HIGH")); // full form, uppercase
        assertEquals(new Priority("VIP"), new Priority("VIP")); // full form, uppercase
        assertEquals(new Priority("L"), new Priority("L")); // short form, uppercase
        assertEquals(new Priority("M"), new Priority("M")); // short form, uppercase
        assertEquals(new Priority("H"), new Priority("H")); // short form, uppercase
        assertEquals(new Priority("V"), new Priority("V")); // short form, uppercase
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // blank priority
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only

        // invalid priority
        assertFalse(Priority.isValidPriority("invalid"));
        assertFalse(Priority.isValidPriority("x"));
        assertFalse(Priority.isValidPriority("random"));

        // valid priority
        assertTrue(Priority.isValidPriority("low")); // full form, lowercase
        assertTrue(Priority.isValidPriority("medium")); // full form, lowercase
        assertTrue(Priority.isValidPriority("high")); // full form, lowercase
        assertTrue(Priority.isValidPriority("vip")); // full form, lowercase
        assertTrue(Priority.isValidPriority("l")); // short form, lowercase
        assertTrue(Priority.isValidPriority("m")); // short form, lowercase
        assertTrue(Priority.isValidPriority("h")); // short form, lowercase
        assertTrue(Priority.isValidPriority("v")); // short form, lowercase
        assertTrue(Priority.isValidPriority("LOW")); // full form, uppercase
        assertTrue(Priority.isValidPriority("MEDIUM")); // full form, uppercase
        assertTrue(Priority.isValidPriority("HIGH")); // full form, uppercase
        assertTrue(Priority.isValidPriority("VIP")); // full form, uppercase
        assertTrue(Priority.isValidPriority("L")); // short form, uppercase
        assertTrue(Priority.isValidPriority("M")); // short form, uppercase
        assertTrue(Priority.isValidPriority("H")); // short form, uppercase
        assertTrue(Priority.isValidPriority("V")); // short form, uppercase
    }

    @Test
    public void equals() {
        Priority priority = new Priority("medium");

        // same values -> returns true
        Priority priorityCopy = new Priority("medium");

        // same object -> returns true
        assertTrue(priority.equals(priority));

        // null -> returns false
        assertFalse(priority.equals(null));

        // different types -> returns false
        assertFalse(priority.equals(5.0f));

        // different values -> returns false
        assertFalse(priority.equals(new Priority("high")));
    }
}
