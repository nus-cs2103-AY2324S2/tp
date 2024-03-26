package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

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
        assertFalse(Deadline.isValidDeadline("")); // Empty string
        assertFalse(Deadline.isValidDeadline(" ")); // Just whitespace
        assertFalse(Deadline.isValidDeadline("23-03-2024")); // Invalid format, should contain slashes
        assertFalse(Deadline.isValidDeadline("23/Mar/2024")); // Example of a invalid deadline using month name
        assertFalse(Deadline.isValidDeadline("23/March/2024")); // Example of a invalid deadline using month name

        // valid deadlines
        assertTrue(Deadline.isValidDeadline("23/03/2024")); // Example of a valid deadline format (YYYY/MM/DD)
    }

    @Test
    public void equals() {
        Deadline deadline = new Deadline("23/03/2024");

        // same values -> returns true
        assertTrue(deadline.equals(new Deadline("23/03/2024")));

        // same object -> returns true
        assertTrue(deadline.equals(deadline));

        // null -> returns false
        assertFalse(deadline.equals(null));

        // different types -> returns false
        assertFalse(deadline.equals(5.0f));

        // different values -> returns false
        assertFalse(deadline.equals(new Deadline("25/04/2025")));
    }
}
