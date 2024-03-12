package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.commons.util.DateTimeUtil;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    @Test
    public void constructor_singleParameter_scheduleInitialized() {
        LocalDateTime schedule = LocalDateTime.now();
        Schedule testSchedule = new Schedule(schedule);
        assertEquals(schedule, testSchedule.getSchedule());
        assertFalse(testSchedule.getAppointmentStatus()); // By default, isDone should be false
    }

    @Test
    public void constructor_twoParameters_scheduleAndIsDoneInitialized() {
        LocalDateTime schedule = LocalDateTime.now();
        Schedule testSchedule = new Schedule(schedule, true);
        assertEquals(schedule, testSchedule.getSchedule());
        assertTrue(testSchedule.getIsDone());
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
        assertEquals("Your next appointment is " +
                DateTimeUtil.parseDateToString(futureSchedule), testSchedule.showSchedule());
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
}

