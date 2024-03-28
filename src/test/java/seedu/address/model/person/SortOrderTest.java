package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class SortOrderTest {

    @Test
    public void getSortOrder_null_returnsInvalid() {
        assertEquals(SortOrder.getSortOrder(null), SortOrder.INVALID);
    }

    @Test
    public void constructor_blankPriority_returnsInvalid() {
        assertEquals(SortOrder.getSortOrder(""), SortOrder.INVALID); // empty string
        assertEquals(SortOrder.getSortOrder(" "), SortOrder.INVALID); // spaces only
    }

    @Test
    public void constructor_invalidPriority_returnsInvalid() {
        assertEquals(SortOrder.getSortOrder("invalid"), SortOrder.INVALID);
        assertEquals(SortOrder.getSortOrder("x"), SortOrder.INVALID);
        assertEquals(SortOrder.getSortOrder("random"), SortOrder.INVALID);
    }

    @Test
    public void constructor_validPriority_success() {
        assertEquals(SortOrder.getSortOrder("asc"), SortOrder.ASC); // lowercase
        assertEquals(SortOrder.getSortOrder("desc"), SortOrder.DESC); // lowercase
        assertEquals(SortOrder.getSortOrder("ASC"), SortOrder.ASC); // uppercase
        assertEquals(SortOrder.getSortOrder("DESC"), SortOrder.DESC); // uppercase
    }

    @Test
    public void isValidSortOrder() {
        // null sort order
        assertFalse(SortOrder.isValidSortOrder(null));

        // blank sort order
        assertFalse(SortOrder.isValidSortOrder("")); // empty string
        assertFalse(SortOrder.isValidSortOrder(" ")); // spaces only

        // invalid sort order
        assertFalse(SortOrder.isValidSortOrder("invalid"));
        assertFalse(SortOrder.isValidSortOrder("x"));
        assertFalse(SortOrder.isValidSortOrder("random"));

        // valid sort order
        assertTrue(SortOrder.isValidSortOrder("asc")); // lowercase
        assertTrue(SortOrder.isValidSortOrder("desc")); // lowercase
        assertTrue(SortOrder.isValidSortOrder("ASC")); // uppercase
        assertTrue(SortOrder.isValidSortOrder("DESC")); // uppercase
    }

    @Test
    public void equals() {
        SortOrder sortOrder = SortOrder.getSortOrder("asc");

        // same values -> returns true
        assertTrue(sortOrder.equals(SortOrder.getSortOrder("asc")));

        // same object -> returns true
        assertTrue(sortOrder.equals(sortOrder));

        // null -> returns false
        assertFalse(sortOrder.equals(null));

        // different types -> returns false
        assertFalse(sortOrder.equals(5.0f));

        // different values -> returns false
        assertFalse(sortOrder.equals(SortOrder.getSortOrder("desc")));
    }
}
