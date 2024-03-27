package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PaxTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Pax(null));
    }

    @Test
    public void constructor_invalidPax_throwsIllegalArgumentException() {
        String invalidPax = "";
        assertThrows(IllegalArgumentException.class, () -> new Pax(invalidPax));
    }

    @Test
    public void isValidPax() {
        // null pax
        assertThrows(NullPointerException.class, () -> Pax.isValidPax(null));

        // invalid pax
        assertFalse(Pax.isValidPax("")); // empty string
        assertFalse(Pax.isValidPax(" ")); // spaces only
        assertFalse(Pax.isValidPax("pax")); // non-numeric
        assertFalse(Pax.isValidPax("9011p041")); // alphabets within digits
        assertFalse(Pax.isValidPax("9312 1534")); // spaces within digits
        assertFalse(Pax.isValidPax("0")); // zero is not allowed

        // valid pax
        assertTrue(Pax.isValidPax("1")); // smallest possible reservation
        assertTrue(Pax.isValidPax("1000")); // large reservation
    }

    @Test
    public void toStringMethod() {
        Pax pax = new Pax("4");
        assertEquals("4", pax.toString());
        assertNotEquals("5", pax.toString());
    }

    @Test
    public void testEquals() {
        Pax pax = new Pax("4");

        // same values -> returns true
        assertTrue(pax.equals(new Pax("4")));

        // same object -> returns true
        assertTrue(pax.equals(pax));

        // null -> returns false
        assertFalse(pax.equals(null));

        // different types -> returns false
        assertFalse(pax.equals(5.0f));

        // different values -> returns false
        assertFalse(pax.equals(new Pax("8")));
    }

    @Test
    public void hashCodeMethod() {
        Pax x = new Pax("4");
        Pax y = new Pax("4");
        Pax z = new Pax("5");
        assertEquals(x.hashCode(), y.hashCode()); // hash based on value
        assertNotEquals(x.hashCode(), z.hashCode());
    }
}
