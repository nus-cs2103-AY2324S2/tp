package seedu.address.commons.core.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null time
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // blank time
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only

        // missing parts
        assertFalse(Date.isValidDate("-02-20")); // missing YYYY part
        assertFalse(Date.isValidDate("2024-02")); // only 2 parts
        assertFalse(Date.isValidDate("02-31")); // only 2 parts
        assertFalse(Date.isValidDate("2024-02-")); // missing dd part

        // invalid parts
        assertFalse(Date.isValidDate("0134-12-02")); // invalid YYYY part
        assertFalse(Date.isValidDate("20244-12-02")); // invalid YYYY part
        assertFalse(Date.isValidDate("2024-13-02")); // invalid MM part
        assertFalse(Date.isValidDate("2024-00-02")); // invalid MM part
        assertFalse(Date.isValidDate("2024-12-00")); // invalid dd part
        assertFalse(Date.isValidDate("2024-02-30")); // invalid dd part
        assertFalse(Date.isValidDate("2024/20/02")); // invalid delimiters
        assertFalse(Date.isValidDate("2024 20 02")); // invalid delimiters
        assertFalse(Date.isValidDate("2024:20:02")); // invalid delimiters

        assertFalse(Date.isValidDate("2024-12-12-03")); // additional fields given
        assertFalse(Date.isValidDate("      2024/20/02")); // leading space
        assertFalse(Date.isValidDate("2024/20/02   ")); // trailing space

        // valid date
        assertTrue(Date.isValidDate("2024-03-24"));
        assertTrue(Date.isValidDate("2024-02-02"));
        assertTrue(Date.isValidDate("2024-12-31"));
    }

    @Test
    public void equals() {
        Date date = new Date("2024-03-24");

        // same values -> returns true
        assertTrue(date.equals(new Date("2024-03-24")));

        // same object -> returns true
        assertTrue(date.equals(date));

        // null -> returns false
        assertFalse(date.equals(null));

        // different types -> returns false
        assertFalse(date.equals(5.0f));

        // different values -> returns false
        assertFalse(date.equals(new Date("2024-03-23")));
    }
}

