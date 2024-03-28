package educonnect.model.student.timetable;

import static educonnect.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import educonnect.model.student.timetable.exceptions.OverlapPeriodException;

public class DayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Day(null));
    }

    @Test
    public void isSameDay() {
        Day day = new Day(DayOfWeek.SUNDAY);

        // same Day -> returns true
        assertTrue(day.isSameDay(new Day(DayOfWeek.SUNDAY)));

        // same object -> returns true
        assertTrue(day.isSameDay(day));

        // different Day -> returns false
        assertFalse(day.isSameDay(new Day(DayOfWeek.MONDAY)));
    }

    @Test
    public void addPeriod_invalidInputs_throwsOverlapPeriodException() throws OverlapPeriodException {
        Day day = new Day(DayOfWeek.SUNDAY);
        Period period1 = // 1 AM to 3 AM
                new Period("period1", LocalTime.of(1, 0, 0), LocalTime.of(3, 0, 0));
        Period period2 = // 3 AM to 5 AM
                new Period("period2", LocalTime.of(3, 0, 0), LocalTime.of(5, 0, 0));
        Period period3 = // 2 AM to 4 PM
                new Period("period3", LocalTime.of(2, 0, 0), LocalTime.of(4, 0, 0));

        day.addPeriod(period1);
        day.addPeriod(period2);

        // has overlap, not successfully added -> throws OverlapPeriodException
        assertThrows(OverlapPeriodException.class, () -> day.addPeriod(period3));
    }
    @Test
    public void addPeriod_validInputs_returnsTrue() throws OverlapPeriodException {
        Day day = new Day(DayOfWeek.SUNDAY);
        Period period1 = // 1 AM to 3 AM
                new Period("period1", LocalTime.of(1, 0, 0), LocalTime.of(3, 0, 0));
        Period period2 = // 3 AM to 5 AM
                new Period("period2", LocalTime.of(3, 0, 0), LocalTime.of(5, 0, 0));

        // no overlap, successfully added -> returns true
        assertTrue(day.addPeriod(period2));
        assertTrue(day.addPeriod(period1));

        // check if the periods added are sorted automatically.
        assertTrue(day.isSorted());
    }

    @Test
    public void convertToCommandString() throws OverlapPeriodException {
        String expectedString1 = "mon: 1-3";
        String expectedString2 = expectedString1 + ", 3-5";
        String expectedString3 = expectedString2 + ", 7-11";

        Day day = new Day(DayOfWeek.MONDAY);
        Period period1 = // 1 AM to 3 AM
                new Period("period1", LocalTime.of(1, 0, 0), LocalTime.of(3, 0, 0));
        Period period2 = // 3 AM to 5 AM
                new Period("period2", LocalTime.of(3, 0, 0), LocalTime.of(5, 0, 0));
        Period period3 = // 7 AM to 11 AM
                new Period("period2", LocalTime.of(7, 0, 0), LocalTime.of(11, 0, 0));
        day.addPeriod(period1);

        // only 1 period
        assertEquals(expectedString1, day.convertToCommandString());

        // multiple periods
        day.addPeriod(period2);
        assertEquals(expectedString2, day.convertToCommandString());

        day.addPeriod(period3);
        assertEquals(expectedString3, day.convertToCommandString());
    }
}

