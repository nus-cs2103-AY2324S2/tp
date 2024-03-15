package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateTest {
    private static final String VALID_DATE = "2020-01-01";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Date("2020-99-99"));
    }

    @Test
    public void equals() {
        Date date = new Date(VALID_DATE);

        // same object -> returns true
        assertTrue(date.equals(date));

        // same values -> returns true
        Date dateCopy = new Date(date.toString());
        assertTrue(date.equals(dateCopy));

        // different types -> returns false
        assertFalse(date.equals(1));

        // null -> returns false
        assertFalse(date.equals(null));

        // different Date -> returns false
        Date differentDate = new Date("2020-01-02");
        assertFalse(date.equals(differentDate));
    }

    @Test
    public void hashcode() {
        Date date = new Date("2020-01-01");

        // same date -> returns same hashcode
        assertEquals(date.hashCode(), new Date("2020-01-01").hashCode());

        // different date -> returns different hashcode
        assertNotEquals(date.hashCode(), new Date("2020-05-01").hashCode());
    }
}
