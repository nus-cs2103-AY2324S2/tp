package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RsvTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RsvTime(null));
    }

    @Test
    public void constructor_invalidRsvTime_throwsIllegalArgumentException() {
        String invalidRsvTime = "";
        assertThrows(IllegalArgumentException.class, () -> new RsvTime(invalidRsvTime));
    }

    @Test
    public void isValidRsvTime() {
        // null time
        assertThrows(NullPointerException.class, () -> RsvTime.isValidRsvTime(null));

        // invalid time
        assertFalse(RsvTime.isValidRsvTime("")); // empty string
        assertFalse(RsvTime.isValidRsvTime(" ")); // spaces only
        assertFalse(RsvTime.isValidRsvTime("time")); // non-numeric
        assertFalse(RsvTime.isValidRsvTime("2j49")); // alphabets within digits
        assertFalse(RsvTime.isValidRsvTime("2 59")); // spaces within digits
        assertFalse(RsvTime.isValidRsvTime("000")); // less than four digits
        assertFalse(RsvTime.isValidRsvTime("00000")); // more than four digits
        assertFalse(RsvTime.isValidRsvTime("2360")); // invalid minute
        assertFalse(RsvTime.isValidRsvTime("2459")); // invalid hour

        // valid time
        assertTrue(RsvTime.isValidRsvTime("0000"));
        assertTrue(RsvTime.isValidRsvTime("0001"));
        assertTrue(RsvTime.isValidRsvTime("2359"));
    }

    @Test
    public void toStringMethod() {
        RsvTime rsvTime = new RsvTime("2359");
        assertEquals("2359", rsvTime.toString());
        assertNotEquals("0000", rsvTime.toString());
    }

    @Test
    public void testEquals() {
        RsvTime rsvTime = new RsvTime("2359");

        // same values -> returns true
        assertTrue(rsvTime.equals(new RsvTime("2359")));

        // same object -> returns true
        assertTrue(rsvTime.equals(rsvTime));

        // null -> returns false
        assertFalse(rsvTime.equals(null));

        // different types -> returns false
        assertFalse(rsvTime.equals(5.0f));

        // different values -> returns false
        assertFalse(rsvTime.equals(new RsvTime("0000")));
    }

    @Test
    public void hashCodeMethod() {
        RsvTime x = new RsvTime("2359");
        RsvTime y = new RsvTime("2359");
        RsvTime z = new RsvTime("0000");
        assertEquals(x.hashCode(), y.hashCode()); // hash based on value
        assertNotEquals(x.hashCode(), z.hashCode());
    }
}
