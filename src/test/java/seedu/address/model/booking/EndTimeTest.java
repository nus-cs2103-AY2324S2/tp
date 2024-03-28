package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EndTimeTest {

    @Test
    public void constructor_invalidEndTime_throwsDateTimeParseException() {
        String invalidEndTime = "2024-15-40 26:00"; // Invalid date and time
        assertThrows(IllegalArgumentException.class, () -> new EndTime(invalidEndTime));
    }

    @Test
    public void isValidEndTime() {
        // invalid end times
        assertFalse(EndTime.isValidEndTime("")); // Empty string
        assertFalse(EndTime.isValidEndTime("2024-01-32 24:60")); // Non-existent date and time

        // valid end times
        assertTrue(EndTime.isValidEndTime("2024-03-19 12:00")); // Correct format
    }

    @Test
    public void equals() {
        EndTime endTime = new EndTime("2024-03-19 12:00");

        // same values -> returns true
        assertTrue(endTime.equals(new EndTime("2024-03-19 12:00")));

        // same object -> returns true
        assertTrue(endTime.equals(endTime));

        // null -> returns false
        assertFalse(endTime.equals(null));

        // different types -> returns false
        assertFalse(endTime.equals(new String("2024-03-19 12:00")));

        // different values -> returns false
        assertFalse(endTime.equals(new EndTime("2024-03-20 12:00")));
    }

    @Test
    public void hashCode_test() {
        EndTime endTime1 = new EndTime("2024-12-31 18:00");
        EndTime endTime2 = new EndTime("2024-12-31 18:00");
        EndTime endTime3 = new EndTime("2024-12-31 19:00");

        // Same end times should have the same hash code
        assertEquals(endTime1.hashCode(), endTime2.hashCode());

        // Different end times should ideally have different hash codes
        assertNotEquals(endTime1.hashCode(), endTime3.hashCode());
    }
}
