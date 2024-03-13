package educonnect.model.student.timetable;

import java.time.DayOfWeek;
import java.util.ArrayList;

import educonnect.model.student.timetable.exceptions.NumberOfDaysException;

/**
 * Represents the timetable of a student for a week.
 */
public class Timetable {
    private static final int NUMBER_OF_DAYS_MAX = 7;
    private final ArrayList<Day> days;
    private final int numOfDays;

    Timetable() {
        this.numOfDays = NUMBER_OF_DAYS_MAX;
        this.days = createTimetable(this.numOfDays);
    }
    Timetable(int numOfDays) {
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
