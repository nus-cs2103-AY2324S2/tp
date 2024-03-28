package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StartTimeTest {

    @Test
    public void constructor_invalidStartTime_throwsDateTimeParseException() {
        String invalidStartTime = "2024-03-33 25:00"; // Invalid date and time
        assertThrows(IllegalArgumentException.class, () -> new StartTime(invalidStartTime));
    }

    @Test
    public void isValidStartTime() {
        // invalid start times
        assertFalse(StartTime.isValidStartTime("")); // Empty string
        assertFalse(StartTime.isValidStartTime("2024-02-30 24:01")); // Non-existent date and time

        // valid start times
        assertTrue(StartTime.isValidStartTime("2024-03-19 12:00")); // Correct format
    }

    @Test
    public void equals() {
        StartTime startTime = new StartTime("2024-03-19 12:00");

        // same values -> returns true
        assertTrue(startTime.equals(new StartTime("2024-03-19 12:00")));

        // same object -> returns true
        assertTrue(startTime.equals(startTime));

        // null -> returns false
        assertFalse(startTime.equals(null));

        // different types -> returns false
        assertFalse(startTime.equals(new String("2024-03-19 12:00")));

        // different values -> returns false
        assertFalse(startTime.equals(new StartTime("2024-03-20 12:00")));
    }

    @Test
    public void hashCode_test() {
        StartTime startTime1 = new StartTime("2024-03-19 12:00");
        StartTime startTime2 = new StartTime("2024-03-19 12:00");
        StartTime startTime3 = new StartTime("2024-03-20 12:00");

        // Same start times should have the same hash code
        assertEquals(startTime1.hashCode(), startTime2.hashCode());

        // Different start times should ideally have different hash codes
        assertNotEquals(startTime1.hashCode(), startTime3.hashCode());
    }
}
