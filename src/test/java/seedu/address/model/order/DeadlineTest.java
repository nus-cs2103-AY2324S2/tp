package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadlines
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("11-15-2024 21:51")); // invalid date format
        assertFalse(Deadline.isValidDeadline("41-15-2024 21:51")); // invalid date
        assertFalse(Deadline.isValidDeadline("15-41-2024 21:51")); // invalid date

        // valid deadlines
        assertTrue(Deadline.isValidDeadline("15-12-3024 21:51")); // long deadline
        assertTrue(Deadline.isValidDeadline("11-05-2024 21:51"));
        assertTrue(Deadline.isValidDeadline("01-05-2024 01:01"));
    }

    @Test
    public void equals() {
        Deadline deadline = new Deadline("11-05-2024 21:51");

        // same values -> returns true
        assertEquals(deadline, new Deadline("11-05-2024 21:51"));

        // same object -> returns true
        assertEquals(deadline, deadline);

        // null -> returns false
        assertNotEquals(null, deadline);

        // different types -> returns false
        assertNotEquals(deadline, 0.0);

        // different values -> returns false
        assertNotEquals(deadline, new Deadline("11-05-2024 21:52"));
    }

}
