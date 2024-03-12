package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void equals() {
        EventName eventName = new EventName("Valid EventName");

        // same values -> returns true
        assertTrue(eventName.equals(new EventName("Valid EventName")));

        // same object -> returns true
        assertTrue(eventName.equals(eventName));

        // null -> returns false
        assertFalse(eventName.equals(null));

        // different types -> returns false
        assertFalse(eventName.equals(5.0f));

        // different values -> returns false
        assertFalse(eventName.equals(new EventName("Other Valid EventName")));
    }

}
