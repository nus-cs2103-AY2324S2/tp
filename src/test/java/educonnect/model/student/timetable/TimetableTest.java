package educonnect.model.student.timetable;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @Test
    public void test_constructor() {
        Timetable timetable5 = new Timetable(5);
        Timetable timetable7 = new Timetable(7);

        assertEquals(timetable5.toString(), EMPTY_TIMETABLE_FIVE);
        assertEquals(timetable7.toString(), EMPTY_TIMETABLE_SEVEN);
    }

    @Test
    public void test_addPeriodToDay() {
        Timetable timetable5 = new Timetable(5);

        // adding to Monday, period from 1 PM to 2 PM, success -> returns true
        assertTrue(timetable5.addPeriodToDay(1,
                new Period("period1",
                        LocalTime.of(13, 0, 0),
                        LocalTime.of(14, 0, 0))));

        // adding to Monday, period from 3 PM to 5 PM, success -> returns false
        assertTrue(timetable5.addPeriodToDay(1,
                new Period("period2",
                        LocalTime.of(15, 0, 0),
                        LocalTime.of(17, 0, 0))));

        // adding to Friday, period from 3 PM to 5 PM, success -> returns false
        assertTrue(timetable5.addPeriodToDay(5,
                new Period("period2",
                        LocalTime.of(15, 0, 0),
                        LocalTime.of(17, 0, 0))));

        // adding to Friday, period from 1 PM to 2 PM, success -> returns true
        assertTrue(timetable5.addPeriodToDay(5,
                new Period("period1",
                        LocalTime.of(13, 0, 0),
                        LocalTime.of(14, 0, 0))));

        // adding to Monday, period from 2 PM to 4 PM, failure -> returns false
        assertFalse(timetable5.addPeriodToDay(1,
                new Period("period1a",
                        LocalTime.of(14, 0, 0),
                        LocalTime.of(16, 0, 0))));

        // adding to a day outside the normal 7 days, failure -> returns false
        assertFalse(timetable5.addPeriodToDay(10,
                new Period("period1a",
                        LocalTime.of(14, 0, 0),
                        LocalTime.of(16, 0, 0))));

        System.out.println(timetable5);
    }
}
