package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StarTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Star(null));
    }

    @Test
    public void constructor_invalidStar_throwsIllegalArgumentException() {
        Integer invalidStar = -1;
        assertThrows(IllegalArgumentException.class, () -> new Star(invalidStar));
    }

    @Test
    public void isValidStar() {
        // null star
        assertThrows(NullPointerException.class, () -> Star.isValidStar(null));

        // invalid stars
        assertFalse(Star.isValidStar(-1)); // negative number
        assertFalse(Star.isValidStar(-5)); // negative number

        // valid stars
        assertTrue(Star.isValidStar(0)); // zero stars
        assertTrue(Star.isValidStar(1)); // positive number
        assertTrue(Star.isValidStar(5)); // positive number
    }

    @Test
    public void equals() {
        Star star = new Star(1);

        // same values -> returns true
        assertTrue(star.equals(new Star(1)));

        // same object -> returns true
        assertTrue(star.equals(star));

        // null -> returns false
        assertFalse(star.equals(null));

        // different types -> returns false
        assertFalse(star.equals(5.0f));

        // different values -> returns false
        assertFalse(star.equals(new Star(2)));
    }
}
