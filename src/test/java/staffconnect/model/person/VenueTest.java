package staffconnect.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VenueTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Venue(null));
    }

    @Test
    public void constructor_invalidVenue_throwsIllegalArgumentException() {
        String invalidVenue = "";
        assertThrows(IllegalArgumentException.class, () -> new Venue(invalidVenue));
    }

    @Test
    public void isValidVenue() {
        // null venue
        assertThrows(NullPointerException.class, () -> Venue.isValidVenue(null));

        // invalid venues
        assertFalse(Venue.isValidVenue("")); // empty string
        assertFalse(Venue.isValidVenue(" ")); // spaces only

        // valid venues
        assertTrue(Venue.isValidVenue("Blk 456, Den Road, #01-355"));
        assertTrue(Venue.isValidVenue("-")); // one character
        assertTrue(Venue.isValidVenue("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long venue
    }

    @Test
    public void equals() {
        Venue venue = new Venue("Valid Venue");

        // same values -> returns true
        assertTrue(venue.equals(new Venue("Valid Venue")));

        // same object -> returns true
        assertTrue(venue.equals(venue));

        // null -> returns false
        assertFalse(venue.equals(null));

        // different types -> returns false
        assertFalse(venue.equals(5.0f));

        // different values -> returns false
        assertFalse(venue.equals(new Venue("Other Valid Venue")));
    }
}
