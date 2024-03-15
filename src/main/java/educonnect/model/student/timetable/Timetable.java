package educonnect.model.student.timetable;

import java.time.DayOfWeek;
import java.util.ArrayList;

import educonnect.model.student.timetable.exceptions.NumberOfDaysException;

/**
 * Represents the timetable of a student for a week.
 */
public class Timetable {
    private static final boolean TIMETABLE_7_DAYS = true; // default is 5 days
    private static final int NUMBER_OF_DAYS_MAX = 7;
    private static final int NUMBER_OF_DAYS_TYPICAL = 5;
    private final ArrayList<Day> days;
    private final int numOfDays;

    public Timetable() {
        this.numOfDays = TIMETABLE_7_DAYS ? NUMBER_OF_DAYS_MAX : NUMBER_OF_DAYS_TYPICAL;
        this.days = createTimetable(this.numOfDays);
    }
    public Timetable(int numOfDays) {
        this.numOfDays = numOfDays;
        this.days = createTimetable(this.numOfDays);
    }

    /**
     * Helper method to initialise empty timetable with the specified number of days.
     * Typical values in a week are 5 or 7. The week always start with Monday.
     *
     * @param numOfDays number of days in the week.
     * @return an {@code ArrayList<Day>} containing each day in the week.
     */
    private ArrayList<Day> createTimetable(int numOfDays) {
        if (numOfDays < 1 || numOfDays > NUMBER_OF_DAYS_MAX) {
            throw new NumberOfDaysException();
        }

        ArrayList<Day> days = new ArrayList<>(numOfDays);
        for (int i = 1; i <= numOfDays; i++) {
            DayOfWeek dayOfWeek = DayOfWeek.of(i);
            Day day = new Day(dayOfWeek);
            days.add(i - 1, day);
        }

        return days;
    }

    public static boolean getTimetable7Days() {
        return TIMETABLE_7_DAYS;
    }

    /**
     * Adds a {@code Period} to a specified day.
     *
     * @param dayNumber the {@code int} representing the day of the week, 1 represents Monday.
     * @param period a period of time to be added into the timetable.
     * @return true if successfully added.
     */
    public boolean addPeriodToDay(int dayNumber, Period period) {
        if (dayNumber < 1 || dayNumber > this.numOfDays) {
            return false;
        }

        Day day = this.days.get(dayNumber - 1);
        return day.addPeriod(period);
    }

    public boolean addPeriodsToDay(int dayNumber, ArrayList<Period> periods) {
        if (dayNumber < 1 || dayNumber > this.numOfDays) {
            return false;
        }

        for (Period period : periods) {
            addPeriodToDay(dayNumber, period);
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Timetable\n");
        for (Day eachDay : this.days) {
            sb.append(eachDay.toString()).append("\n");
        }
        return sb.toString();
    }
}