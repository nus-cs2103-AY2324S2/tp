package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LocationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidLocation = "";
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidLocation));
    }

    @Test
    public void isValidLocation() {
        // null locations
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // invalid locations
        assertFalse(Location.isValidLocation(""));
        assertFalse(Location.isValidLocation(" "));

        // valid locations
        assertTrue(Location.isValidLocation("LOCAL"));
        assertTrue(Location.isValidLocation("OVERSEAS"));
        assertTrue(Location.isValidLocation("REMOTE"));
        assertTrue(Location.isValidLocation("local"));
        assertTrue(Location.isValidLocation("overseas"));
        assertTrue(Location.isValidLocation("remote"));
    }

    @Test
    public void equals() {
        Location location = new Location("local");

        // same values -> returns true
        assertTrue(location.equals(new Location("LOCAL")));

        // same object -> returns true
        assertTrue(location.equals(location));

        // null -> returns false
        assertFalse(location.equals(null));

        // different types -> returns false
        assertFalse(location.equals(5.0f));

        // different values -> returns false
        assertFalse(location.equals(new Location("OVERSEAS")));
    }
}
