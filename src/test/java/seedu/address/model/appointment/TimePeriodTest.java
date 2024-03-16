package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimePeriodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Time time = new Time("10:00");
        assertThrows(NullPointerException.class, () -> new TimePeriod(time, null));
        assertThrows(NullPointerException.class, () -> new TimePeriod(null, time));
    }

    @Test
    public void constructor_invalidTimePeriod_throwsIllegalArgumentException() {
        Time startTime = new Time("10:00");
        Time endTime = new Time("09:00");
        assertThrows(IllegalArgumentException.class, () -> new TimePeriod(startTime, endTime));
    }

    @Test
    public void isValidTimePeriod() {
        Time time = new Time("10:00");

        // null time
        assertThrows(NullPointerException.class, () -> TimePeriod.isValidTimePeriod(time, null));
        assertThrows(NullPointerException.class, () -> TimePeriod.isValidTimePeriod(null, time));
        assertThrows(NullPointerException.class, () -> TimePeriod.isValidTimePeriod(null, null));

        assertFalse(TimePeriod.isValidTimePeriod(new Time("12:00"), new Time("12:00"))); // duration 0
        assertFalse(TimePeriod.isValidTimePeriod(new Time("12:01"), new Time("12:00"))); // start after end
        assertFalse(TimePeriod.isValidTimePeriod(new Time("23:59"), new Time("00:01"))); // start after end

        // Valid time period
        assertTrue(TimePeriod.isValidTimePeriod(new Time("11:00"), new Time("12:00")));
        assertTrue(TimePeriod.isValidTimePeriod(new Time("09:00"), new Time("09:01")));
    }

    @Test
    public void equals() {
        Time startTime = new Time("12:00");
        Time endTime = new Time("13:00");
        TimePeriod timePeriod = new TimePeriod(startTime, endTime);

        // same values -> returns true
        assertTrue(timePeriod.equals(new TimePeriod(new Time("12:00"), new Time("13:00"))));

        // same object -> returns true
        assertTrue(timePeriod.equals(timePeriod));

        // null -> returns false
        assertFalse(timePeriod.equals(null));

        // different types -> returns false
        assertFalse(timePeriod.equals(5.0f));

        // different values -> returns false
        assertFalse(timePeriod.equals(new TimePeriod(new Time("12:30"), new Time("13:00"))));
    }
}
