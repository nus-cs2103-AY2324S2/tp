package educonnect.model.student.timetable;

import static educonnect.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import educonnect.model.student.timetable.exceptions.NumberOfDaysException;
import educonnect.model.student.timetable.exceptions.OverlapPeriodException;

public class TimetableTest {
    private static final String EMPTY_TIMETABLE_FIVE =
            "Timetable\n"
            + "For MONDAY, schedule is:\n\n"
            + "For TUESDAY, schedule is:\n\n"
            + "For WEDNESDAY, schedule is:\n\n"
            + "For THURSDAY, schedule is:\n\n"
            + "For FRIDAY, schedule is:\n\n";
    private static final String EMPTY_TIMETABLE_SEVEN =
            EMPTY_TIMETABLE_FIVE
            + "For SATURDAY, schedule is:\n\n"
            + "For SUNDAY, schedule is:\n\n";

    private static final Period PERIOD_1 = new Period("period1",
            LocalTime.of(13, 0, 0),
            LocalTime.of(14, 0, 0));
    private static final Period PERIOD_2 = new Period("period2",
            LocalTime.of(15, 0, 0),
            LocalTime.of(17, 0, 0));
    private static final Period PERIOD_3 = new Period("period3",
            LocalTime.of(14, 0, 0),
            LocalTime.of(16, 0, 0));

    @Test
    public void constructor() {
        Timetable timetable5 = new Timetable(5);
        Timetable timetable7 = new Timetable(7);
        Timetable timetableDefault = new Timetable();

        assertEquals(EMPTY_TIMETABLE_FIVE, timetable5.toString());
        assertEquals(EMPTY_TIMETABLE_SEVEN, timetable7.toString());
        assertEquals(EMPTY_TIMETABLE_FIVE, timetableDefault.toString());
    }

    @Test
    public void addPeriodToDay_invalidInputs_throwsOverlapPeriodException() throws OverlapPeriodException {
        Timetable timetable5 = new Timetable(5);
        timetable5.addPeriodToDay(1, PERIOD_3);

        // adding to Monday, period from 2 PM to 4 PM, failure -> throws OverlapPeriodException
        assertThrows(OverlapPeriodException.class, () ->
                timetable5.addPeriodToDay(1, PERIOD_3));

        // adding to a day outside the normal 7 days, failure -> returns false
        assertThrows(NumberOfDaysException.class, () ->
                timetable5.addPeriodToDay(10, PERIOD_3));
    }

    @Test
    public void addPeriodToDay_validInputs_returnsTrue() throws OverlapPeriodException {
        Timetable timetable5 = new Timetable(5);

        // adding to Monday, period from 1 PM to 2 PM, success -> returns true
        assertTrue(timetable5.addPeriodToDay(1, PERIOD_1));

        // adding to Monday, period from 3 PM to 5 PM, success -> returns true
        assertTrue(timetable5.addPeriodToDay(1, PERIOD_2));

        // adding to Friday, period from 3 PM to 5 PM, success -> returns true
        assertTrue(timetable5.addPeriodToDay(5, PERIOD_2));

        // adding to Friday, period from 1 PM to 2 PM, success -> returns true
        assertTrue(timetable5.addPeriodToDay(5, PERIOD_1));
    }

    @Test
    public void convertToCommandString() throws OverlapPeriodException {
        String expectedString1 = "mon: 14-16";
        String expectedString2 = expectedString1 + "tue: 13-14, 15-17";
        String expectedString3 = expectedString2 + "fri: 13-14, 14-16";

        Timetable timetable5 = new Timetable(5);

        // one day only
        timetable5.addPeriodToDay(1, PERIOD_3);
        assertEquals(expectedString1, timetable5.convertToCommandString());

        // multiple days
        timetable5.addPeriodToDay(2, PERIOD_1);
        timetable5.addPeriodToDay(2, PERIOD_2);
        assertEquals(expectedString2, timetable5.convertToCommandString());

        timetable5.addPeriodToDay(5, PERIOD_1);
        timetable5.addPeriodToDay(5, PERIOD_3);
        assertEquals(expectedString3, timetable5.convertToCommandString());
    }
}
