package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class WeekTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Week(null));
    }

    @Test
    public void constructor_invalidWeek_throwsIllegalArgumentException() {
        Index invalidWeek = Index.fromOneBased(100);
        assertThrows(IllegalArgumentException.class, () -> new Week(invalidWeek));
    }

    @Test
    public void isValidWeek() {
        // null Week
        assertThrows(NullPointerException.class, () -> Week.isValidWeek(null));

        // invalid Weeks
        assertFalse(Week.isValidWeek(Index.fromZeroBased(100))); // out of bounds
        assertFalse(Week.isValidWeek(Index.fromOneBased(14))); // one past

        // valid Week
        assertTrue(Week.isValidWeek(Index.fromOneBased(1)));
        assertTrue(Week.isValidWeek(Index.fromOneBased(2)));
        assertTrue(Week.isValidWeek(Index.fromOneBased(13)));
    }

    @Test
    public void equals() {
        Week week = new Week(Index.fromOneBased(1));

        // same values -> returns true
        assertEquals(week, new Week(Index.fromOneBased(1)));

        // same object -> returns true
        assertEquals(week, week);

        // null -> returns false
        assertNotEquals(null, week);

        // different types -> returns false
        assertNotEquals(week, 5.0f);

        // different values -> returns false
        assertNotEquals(week, new Week(Index.fromOneBased(2)));
    }
}
