package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mark(null));
    }

    @Test
    public void constructor_invalidMark_throwsIllegalArgumentException() {
        String invalidMark = "!-@=";
        assertThrows(IllegalArgumentException.class, () -> new Mark(invalidMark));
    }

    @Test
    public void isValidMark() {
        // null mark
        assertThrows(NullPointerException.class, () -> Mark.isValidMark(null));

        // invalid mark
        assertFalse(Mark.isValidMark("")); // empty string
        assertFalse(Mark.isValidMark(" ")); // spaces only
        assertFalse(Mark.isValidMark("^")); // only non-alphanumeric characters
        assertFalse(Mark.isValidMark("health*")); // contains non-alphanumeric characters
        assertFalse(Mark.isValidMark("peter jack")); // alphabets only
        assertFalse(Mark.isValidMark("12345")); // numbers only
        assertFalse(Mark.isValidMark("peter the 2nd")); // alphanumeric characters

        //valid mark
        assertTrue(Mark.isValidMark("true")); // boolean values
        assertTrue(Mark.isValidMark("false"));
    }

    @Test
    public void equals() {
        Mark mark = new Mark("false");

        // same values -> returns true
        assertTrue(mark.equals(new Mark("false")));

        // same object -> returns true
        assertTrue(mark.equals(mark));

        // null -> returns false
        assertFalse(mark.equals(null));

        // different types -> returns false
        assertFalse(mark.equals(5.0f));

        // different values -> returns false
        assertFalse(mark.equals(new Mark("true")));
    }
}
