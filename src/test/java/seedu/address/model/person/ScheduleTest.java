package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;

public class ScheduleTest {

    @Test
    public void constructor_singleParameter_scheduleInitialized() {
        LocalDateTime schedule = LocalDateTime.now();
        Schedule testSchedule = new Schedule(schedule);
        assertEquals(schedule, testSchedule.getSchedule());
        assertFalse(testSchedule.getIsDone()); // By default, isDone should be false
    }

    @Test
    public void constructor_twoParameters_scheduleAndIsDoneInitialized() {
        LocalDateTime schedule = LocalDateTime.now();
        Schedule testSchedule = new Schedule(schedule, true);
        assertEquals(schedule, testSchedule.getSchedule());
        assertTrue(testSchedule.getIsDone());
    }

    @Test
    public void getIsMissed_returnsCorrectIsMissed() {
        LocalDateTime schedule = LocalDateTime.of(2022, 1, 1, 12, 0);
        Schedule testSchedule = new Schedule(schedule, false);
        assertEquals(true, testSchedule.getIsMissed());
    }

    @Test
    public void getIsMissedReminder_isDoneTrue_returnsCorrectIsMissedReminder() {
        LocalDateTime schedule = LocalDateTime.of(2022, 1, 1, 12, 0);
        Schedule testSchedule = new Schedule(schedule, true);
        assertEquals(false, testSchedule.getIsMissedReminder());
    }

    @Test
    public void getIsMissedReminder_isDoneFalse_returnsCorrectIsMissedReminder() {
        LocalDateTime schedule = LocalDateTime.of(2022, 1, 1, 12, 0);
        Schedule testSchedule = new Schedule(schedule, false);
        assertEquals(true, testSchedule.getIsMissedReminder());
    }
    @Test
    public void toString_formattingCorrect() {
        LocalDateTime schedule = LocalDateTime.of(2022, 1, 1, 12, 0);
        Schedule testSchedule = new Schedule(schedule, false);
        assertEquals("2022-01-01 12:00/0", testSchedule.toString());
    }

    @Test
    public void showSchedule_upcomingAppointment_returnsCorrectMessage() {
        LocalDateTime futureSchedule = LocalDateTime.now().plusDays(1);
        Schedule testSchedule = new Schedule(futureSchedule, false);
        assertEquals("Your next appointment is "
                + DateTimeUtil.parseDateToString(futureSchedule), testSchedule.showSchedule());
    }

    @Test
    public void showSchedule_noUpcomingAppointment_returnsCorrectMessage() {
        LocalDateTime pastSchedule = LocalDateTime.now().minusDays(1);
        Schedule testSchedule = new Schedule(pastSchedule, true);
        assertEquals("No upcoming appointment", testSchedule.showSchedule());
    }

    @Test
    public void checkIsDoneFromString_validInput_returnsCorrectBoolean() {
        assertTrue(Schedule.checkIsDoneFromString("1"));
        assertFalse(Schedule.checkIsDoneFromString("0"));
    }

    @Test
    public void isDoneToString_isDoneTrue_returnsCorrectString() {
        Schedule testSchedule = new Schedule(LocalDateTime.now(), true);
        assertEquals("1", testSchedule.isDoneToString());
    }

    @Test
    public void isDoneToString_isDoneFalse_returnsCorrectString() {
        Schedule testSchedule = new Schedule(LocalDateTime.now(), false);
        assertEquals("0", testSchedule.isDoneToString());
    }

    @Test
    public void compareTo() {
        LocalDateTime schedule1 = LocalDateTime.of(2022, 1, 1, 12, 0);
        LocalDateTime schedule2 = LocalDateTime.of(2022, 1, 2, 12, 0);
        Schedule testSchedule1 = new Schedule(schedule1, false);
        Schedule testSchedule2 = new Schedule(schedule2, false);
        assertTrue(testSchedule1.compareTo(testSchedule2) < 0);
        assertTrue(testSchedule1.compareTo(testSchedule1) == 0);
        assertTrue(testSchedule2.compareTo(testSchedule1) > 0);
    }

    @Test
    public void getScheduleDateString() {
        LocalDateTime schedule = LocalDateTime.of(2022, 1, 1, 12, 0);
        Schedule testSchedule = new Schedule(schedule, false);
        assertEquals(DateTimeUtil.parseDateToString(schedule), testSchedule.getScheduleDateString());
    }

    @Test
    public void equals() {
        LocalDateTime scheduleDate = LocalDateTime.of(2022, 1, 1, 12, 0);
        Schedule schedule = new Schedule(scheduleDate, false);

        // same values -> returns true
        Schedule scheduleCopy = new Schedule(scheduleDate, false);
        assertTrue(schedule.equals(scheduleCopy));

        // same object -> returns true
        assertTrue(schedule.equals(schedule));

        // null -> returns false
        assertFalse(schedule.equals(null));

        // different type -> returns false
        assertFalse(schedule.equals(5));

        // different values -> returns false
        LocalDateTime otherScheduleDate = LocalDateTime.of(2022, 1, 2, 12, 0);
        Schedule otherSchedule = new Schedule(otherScheduleDate, false);
        assertFalse(schedule.equals(otherSchedule));
    }
}
