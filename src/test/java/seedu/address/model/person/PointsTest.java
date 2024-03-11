package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PointsTest {

    @Test
    public void equals() {
        Points points = new Points("5");

        // same object -> returns true
        assertTrue(points.equals(points));

        // same values -> returns true
        assertTrue(points.equals(new Points("5")));

        // different types -> returns false
        assertFalse(points.equals(1));

        // null -> returns false
        assertFalse(points.equals(null));

        // different points -> returns false
        Points differentPoints = new Points("10");
        assertFalse(points.equals(differentPoints));
    }

    @Test
    public void isValidPoints() {
        // valid points
        assertTrue(Points.isValidPoints("10")); // positive integer
        assertTrue(Points.isValidPoints("0")); // zero

        // invalid points
        assertFalse(Points.isValidPoints("-1")); // negative integer
        assertFalse(Points.isValidPoints("5.5")); // non-integer
        assertFalse(Points.isValidPoints("text")); // non-numeric
    }
}
