package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RsvDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RsvDate(null));
    }

    @Test
    public void constructor_invalidRsvDate_throwsIllegalArgumentException() {
        String invalidRsvDate = "";
        assertThrows(IllegalArgumentException.class, () -> new RsvDate(invalidRsvDate));
    }

    @Test
    void isValidRsvDate() {
        // null date
        assertThrows(NullPointerException.class, () -> RsvTime.isValidRsvTime(null));

        // invalid date
        assertFalse(RsvDate.isValidRsvDate("")); // empty string
        assertFalse(RsvDate.isValidRsvDate(" ")); // spaces only
        assertFalse(RsvDate.isValidRsvDate("date")); // non-numeric
        assertFalse(RsvDate.isValidRsvDate("2024/01/32")); // wrong format
        assertFalse(RsvDate.isValidRsvDate("2024- 12-23")); // spaces in string
        assertFalse(RsvDate.isValidRsvDate("2024-01-32")); // invalid day
        assertFalse(RsvDate.isValidRsvDate("2024-13-01")); // invalid month
        assertFalse(RsvDate.isValidRsvDate("2024-02-30")); // impossible date

        // valid date
        assertTrue(RsvDate.isValidRsvDate("2024-01-23"));
        assertTrue(RsvDate.isValidRsvDate("2001-01-01"));
    }

    @Test
    void toStringMethod() {
        RsvDate rsvDate = new RsvDate("2024-01-23");
        assertEquals("2024-01-23", rsvDate.toString());
        assertNotEquals("2024-01-24", rsvDate.toString());
    }

    @Test
    void testEquals() {
        RsvDate rsvDate = new RsvDate("2024-01-23");

        // same values -> returns true
        assertEquals(rsvDate, new RsvDate("2024-01-23"));

        // same object -> returns true
        assertEquals(rsvDate, rsvDate);

        // null -> returns false
        assertNotEquals(null, rsvDate);

        // different types -> returns false
        assertNotEquals("2024-01-23", rsvDate);

        // different values -> returns false
        assertNotEquals(rsvDate, new RsvDate("2024-01-24"));
    }

    @Test
    void hashCodeMethod() {
        RsvDate x = new RsvDate("2024-01-01");
        RsvDate y = new RsvDate("2024-01-01");
        RsvDate z = new RsvDate("2024-01-02");
        assertEquals(x.hashCode(), y.hashCode()); // hash based on value
        assertNotEquals(x.hashCode(), z.hashCode());
    }
}
