package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class SortCriteriaTest {

    @Test
    public void getSortCriteria_null_returnsInvalid() {
        assertEquals(SortCriteria.getSortCriteria(null), SortCriteria.INVALID);
    }

    @Test
    public void constructor_blankPriority_returnsInvalid() {
        assertEquals(SortCriteria.getSortCriteria(""), SortCriteria.INVALID); // empty string
        assertEquals(SortCriteria.getSortCriteria(" "), SortCriteria.INVALID); // spaces only
    }

    @Test
    public void constructor_invalidPriority_returnsInvalid() {
        assertEquals(SortCriteria.getSortCriteria("invalid"), SortCriteria.INVALID);
        assertEquals(SortCriteria.getSortCriteria("x"), SortCriteria.INVALID);
        assertEquals(SortCriteria.getSortCriteria("random"), SortCriteria.INVALID);
    }

    @Test
    public void constructor_validPriority_success() {
        assertEquals(SortCriteria.getSortCriteria("name"), SortCriteria.NAME); // lowercase
        assertEquals(SortCriteria.getSortCriteria("priority"), SortCriteria.PRIORITY); // lowercase
        assertEquals(SortCriteria.getSortCriteria("lastmet"), SortCriteria.LASTMET); // lowercase
        assertEquals(SortCriteria.getSortCriteria("NAME"), SortCriteria.NAME); // uppercase
        assertEquals(SortCriteria.getSortCriteria("PRIORITY"), SortCriteria.PRIORITY); // uppercase
        assertEquals(SortCriteria.getSortCriteria("LASTMET"), SortCriteria.LASTMET); // uppercase
    }

    @Test
    public void isValidSortCriteria() {
        // null sort criteria
        assertFalse(SortCriteria.isValidSortCriteria(null));

        // blank sort criteria
        assertFalse(SortCriteria.isValidSortCriteria("")); // empty string
        assertFalse(SortCriteria.isValidSortCriteria(" ")); // spaces only

        // invalid sort criteria
        assertFalse(SortCriteria.isValidSortCriteria("invalid"));
        assertFalse(SortCriteria.isValidSortCriteria("x"));
        assertFalse(SortCriteria.isValidSortCriteria("random"));

        // valid sort criteria
        assertTrue(SortCriteria.isValidSortCriteria("name")); // lowercase
        assertTrue(SortCriteria.isValidSortCriteria("priority")); // lowercase
        assertTrue(SortCriteria.isValidSortCriteria("lastmet")); // lowercase
        assertTrue(SortCriteria.isValidSortCriteria("NAME")); // uppercase
        assertTrue(SortCriteria.isValidSortCriteria("PRIORITY")); // uppercase
        assertTrue(SortCriteria.isValidSortCriteria("LASTMET")); // uppercase
    }

    @Test
    public void equals() {
        SortCriteria sortCriteria = SortCriteria.getSortCriteria("name");

        // same values -> returns true
        assertTrue(sortCriteria.equals(SortCriteria.getSortCriteria("name")));

        // same object -> returns true
        assertTrue(sortCriteria.equals(sortCriteria));

        // null -> returns false
        assertFalse(sortCriteria.equals(null));

        // different types -> returns false
        assertFalse(sortCriteria.equals(5.0f));

        // different values -> returns false
        assertFalse(sortCriteria.equals(SortCriteria.getSortCriteria("priority")));
    }
}
