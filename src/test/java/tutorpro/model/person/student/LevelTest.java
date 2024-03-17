package tutorpro.model.person.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tutorpro.testutil.Assert;

public class LevelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Level(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidLevel = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Level(invalidLevel));
        String invalidLevel2 = "Primary 6";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Level(invalidLevel2));
        String invalidLevel3 = "University";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Level(invalidLevel3));
    }

    @Test
    public void isValidAddress() {
        // null address
        Assert.assertThrows(NullPointerException.class, () -> Level.isValidLevel(null));

        // invalid addresses
        assertFalse(Level.isValidLevel("S5"));
        assertFalse(Level.isValidLevel("J3"));
        assertFalse(Level.isValidLevel("P9"));

        // valid addresses
        assertTrue(Level.isValidLevel("P6"));
        assertTrue(Level.isValidLevel("S4"));
        assertTrue(Level.isValidLevel("UNI"));
        assertTrue(Level.isValidLevel("OTHER"));
    }

    @Test
    public void equals() {
        Level level = new Level("P6");

        // same values -> returns true
        assertTrue(level.equals(new Level("P6")));

        // same object -> returns true
        assertTrue(level.equals(level));

        // null -> returns false
        assertFalse(level.equals(null));

        // different types -> returns false
        assertFalse(level.equals(5.0f));

        // different values -> returns false
        assertFalse(level.equals(new Level("J1")));
    }
}
