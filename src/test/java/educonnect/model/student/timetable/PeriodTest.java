package educonnect.model.student.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import educonnect.model.student.timetable.exceptions.InvalidPeriodException;
import educonnect.testutil.Assert;
public class PeriodTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Period(null, null, null));
    }
    @Test
    public void constructor_invalidInputs_throwsInvalidPersonException() {
        LocalTime time1 = LocalTime.of(1, 0, 0);
        LocalTime time2 = LocalTime.of(2, 0, 0);
        Assert.assertThrows(InvalidPeriodException.class, () -> new Period("period", time2, time1));
        Assert.assertThrows(InvalidPeriodException.class, () -> new Period("", time1, time2));
        Assert.assertThrows(InvalidPeriodException.class, () -> new Period(" ", time1, time2));
    }
    @Test
    public void hasOverlap() {
        Period period1 = // 1 AM to 3 AM
                new Period("period1", LocalTime.of(1, 0, 0), LocalTime.of(3, 0, 0));
        Period period2 = // 3 AM to 5 AM
                new Period("period2", LocalTime.of(3, 0, 0), LocalTime.of(5, 0, 0));
        Period period3 = // 2 AM to 4 PM
                new Period("period3", LocalTime.of(2, 0, 0), LocalTime.of(4, 0, 0));

        // has no overlap -> returns false
        assertFalse(period1.hasOverlap(period2));
        assertFalse(period2.hasOverlap(period1));

        // has overlap -> returns true
        assertTrue(period1.hasOverlap(period3));
        assertTrue(period2.hasOverlap(period3));
        assertTrue(period3.hasOverlap(period3));
        assertTrue(period3.hasOverlap(period1));
        assertTrue(period3.hasOverlap(period2));
    }

    @Test
    public void convertToCommandString() {
        Period period1 = // 1 PM to 3 PM
                new Period("period1",
                        LocalTime.of(13, 0, 0),
                        LocalTime.of(15, 0, 0));

        assertEquals("13-15", period1.convertToCommandString());
    }

    @Test
    public void compareTo() {
        Period period1 = // 1 AM to 3 AM
                new Period("period1", LocalTime.of(1, 0, 0), LocalTime.of(3, 0, 0));
        Period period2 = // 3 AM to 5 AM
                new Period("period2", LocalTime.of(3, 0, 0), LocalTime.of(5, 0, 0));
        Period period3 = // 2 AM to 4 PM
                new Period("period3", LocalTime.of(2, 0, 0), LocalTime.of(4, 0, 0));
        Period period4 = // 2 AM to 4 PM
                new Period("period4", LocalTime.of(2, 0, 0), LocalTime.of(4, 0, 0));

        // smaller than (starts earlier) -> returns -1
        assertEquals(period1.compareTo(period2), -1);
        assertEquals(period1.compareTo(period3), -1);
        assertEquals(period3.compareTo(period2), -1);

        // larger than (starts later) -> returns 1
        assertEquals(period2.compareTo(period1), 1);
        assertEquals(period2.compareTo(period3), 1);
        assertEquals(period3.compareTo(period1), 1);

        // same time or same object -> returns 0
        assertEquals(period1.compareTo(period1), 0);
        assertEquals(period3.compareTo(period4), 0);
    }
}
