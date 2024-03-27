package seedu.address.model.coursemate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RateMateTest {

    @Test
    public void constructor_nullthrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating = "";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid ratings
        assertFalse(Rating.isValidRating("")); // empty rating
        assertFalse(Rating.isValidRating(" ")); // spaces only
        assertFalse(Rating.isValidRating("01")); // more than 1 digit
        assertFalse(Rating.isValidRating("great")); // non-numeric
        assertFalse(Rating.isValidRating("6")); // larger than 5
        assertFalse(Rating.isValidRating("-1")); // below 0
        assertFalse(Rating.isValidRating(" 1")); // spaces before digit
        assertFalse(Rating.isValidRating("1 ")); // space after digit
        assertFalse(Rating.isValidRating("4.5")); // non-integer

        // valid rating
        assertTrue(Rating.isValidRating("5")); // single-digit integer between 0 and 5
    }

    @Test
    public void equals() {
        Rating rating = new Rating("1");

        // same values -> returns true
        assertTrue(rating.equals(new Rating("1")));

        // same object -> returns true
        assertTrue(rating.equals(rating));

        // null -> returns false
        assertFalse(rating.equals(null));

        // different types -> returns false
        assertFalse(rating.equals(5.0f));

        // different values -> returns false
        assertFalse(rating.equals(new Rating("5")));
    }
}
