package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MarkTest {

    @Test
    public void validIsMarked() {
        Mark marked = new Mark(true);
        assertTrue(marked.isMarked);

        Mark unmarked = new Mark(false);
        assertFalse(unmarked.isMarked);
    }

    @Test
    public void equals() {
        Mark mark = new Mark(false);

        // same values -> returns true
        assertTrue(mark.equals(new Mark(false)));

        // same object -> returns true
        assertTrue(mark.equals(mark));

        // null -> returns false
        assertFalse(mark.equals(null));

        // different types -> returns false
        assertFalse(mark.equals(5.0f));

        // different values -> returns false
        assertFalse(mark.equals(new Mark(true)));
    }
}
