package seedu.address.model.house;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LevelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Level(null));
    }

    @Test
    public void constructor_invalidLevel_throwsIllegalArgumentException() {
        String invalidLevel = "";
        assertThrows(IllegalArgumentException.class, () -> new Level(invalidLevel));
    }

    @Test
    public void isValidLevel() {
        // null level
        assertThrows(NullPointerException.class, () -> Level.isValidLevel(null));

        // blank levels
        assertFalse(Level.isValidLevel("")); // empty string
        assertFalse(Level.isValidLevel(" ")); // spaces only

        // invalid levels
        assertFalse(Level.isValidLevel("0")); // 1 zero only
        assertFalse(Level.isValidLevel("00")); // 2 zeroes only
        assertFalse(Level.isValidLevel("a")); // non-digit only
        assertFalse(Level.isValidLevel("aa")); // non-digit only
        assertFalse(Level.isValidLevel("###")); // non-digit only
        assertFalse(Level.isValidLevel("!@#")); // non-digit only
        assertFalse(Level.isValidLevel("a1")); // digit + non-digit
        assertFalse(Level.isValidLevel("1a")); // non-digit + digit
        assertFalse(Level.isValidLevel("111")); // number of digits > 2
        assertFalse(Level.isValidLevel("44aa")); // number of digits >= 2 with letters
        assertFalse(Level.isValidLevel("44aaaaa")); // number of digits >= 2 with letters

        // valid levels
        assertTrue(Level.isValidLevel("1")); // one digit
        assertTrue(Level.isValidLevel("11")); // two digits
        assertTrue(Level.isValidLevel("01")); // two digits
    }

    @Test
    public void equals() {
        Level level = new Level("99");

        // same values -> returns true
        assertTrue(level.equals(new Level("99")));

        // same object -> returns true
        assertTrue(level.equals(level));

        // null -> returns false
        assertFalse(level.equals(null));

        // different types -> returns false
        assertFalse(level.equals(5.0f));

        // different values -> returns false
        assertFalse(level.equals(new Level("11")));
    }
}
