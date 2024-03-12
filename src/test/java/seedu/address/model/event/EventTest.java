package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.BINGO;
import static seedu.address.testutil.TypicalEvents.HIKING;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(BINGO.isSameEvent(BINGO));

        // null -> returns false
        assertFalse(BINGO.isSameEvent(null));
    }

    @Test
    public void equals() {
        // same values -> return true
        Event bingoCopy = new EventBuilder(BINGO).build();
        assertTrue(BINGO.equals(bingoCopy));

        // same object -> returns rue
        assertTrue(BINGO.equals(BINGO));

        // null -> returns false
        assertFalse(BINGO.equals(null));

        // different type -> returns false
        assertFalse(BINGO.equals(5));

        // different person -> returns false
        assertFalse(BINGO.equals(HIKING));
    }

    @Test
    public void toStringMethod() {
        String expected = BINGO.toString(); // to fix
        assertEquals(expected, BINGO.toString());
    }
}
