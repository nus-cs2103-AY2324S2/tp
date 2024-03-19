package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ScheduleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(null));
    }

    @Test
    public void constructor_invalidSchedule_throwsIllegalArgumentException() {
        String invalidSchedule = "2020-12-12";
        assertThrows(IllegalArgumentException.class, () -> new Schedule(invalidSchedule));
    }

    @Test
    public void isValidSchedule() {
        // null schedule
        assertThrows(NullPointerException.class, () -> Schedule.isValidSchedule(null));

        // invalid Schedule
        assertFalse(Schedule.isValidSchedule("31-31-2020")); // Non-existent month
        assertFalse(Schedule.isValidSchedule("12~12~2020")); // ~ incorrectly used
        assertFalse(Schedule.isValidSchedule("2020-12-12")); // Incorrect format
        assertFalse(Schedule.isValidSchedule("12-2020-12")); // Incorrect format
        assertFalse(Schedule.isValidSchedule("")); // Empty string
        assertFalse(Schedule.isValidSchedule("12-12-2020 12pm")); // Extra strings

        // valid schedule
        assertTrue(Schedule.isValidSchedule("12-12-2020")); // Correct format
        assertTrue(Schedule.isValidSchedule("12/12/2020")); // Correct format
    }

    @Test
    public void equals() {
        Schedule schedule = new Schedule("10-10-2020");

        // same values -> returns true
        assertTrue(schedule.equals(new Schedule("10-10-2020")));

        // same object -> returns true
        assertTrue(schedule.equals(schedule));

        // null -> returns false
        assertFalse(schedule.equals(null));

        // different types -> returns false
        assertFalse(schedule.equals(5.0f));

        // different values -> returns false
        assertFalse(schedule.equals(new Schedule("01-01-2010")));
    }
}
