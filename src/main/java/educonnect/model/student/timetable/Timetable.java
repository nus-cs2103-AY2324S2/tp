package educonnect.model.student.timetable;

import java.util.ArrayList;

import educonnect.model.student.timetable.exceptions.NumberOfDaysException;
import educonnect.model.student.timetable.exceptions.OverlapPeriodException;

/**
 * Represents the timetable of a student for a week.
 */
public class Timetable {
    private static final boolean TIMETABLE_7_DAYS = false; // default is 5 days
    private static final int NUMBER_OF_DAYS_MAX = 7;
    private static final int NUMBER_OF_DAYS_TYPICAL = 5;
    private final ArrayList<Day> days;
    private final int numOfDays;

    /**
     * Default constructor for {@code Timetable} class.
     * Checks against TIMETABLE_7_DAYS for 5 or 7 days in the week.
     */
    public Timetable() {
        this.numOfDays = TIMETABLE_7_DAYS ? NUMBER_OF_DAYS_MAX : NUMBER_OF_DAYS_TYPICAL;
        this.days = createTimetable(this.numOfDays);
    }

    /**
     * Overloaded constructor where the number of days in a week can be specified.
     * @param numOfDays the number of days in a week to keep track of.
     */
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
    private ArrayList<Day> createTimetable(int numOfDays) throws NumberOfDaysException {
        if (numOfDays < 1 || numOfDays > NUMBER_OF_DAYS_MAX) {
            throw new NumberOfDaysException();
        }

        ArrayList<Day> days = new ArrayList<>(numOfDays);
        for (int i = 1; i <= numOfDays; i++) {
            Day day = new Day(i);
            days.add(i - 1, day);
        }

        return days;
    }

    /**
     * Gets a {@code boolean} of whether the timetable is 5 or 7 days.
     * @return {@code true} if 7 days, {@code false} if 5 days.
     */
    public static boolean is7Days() {
        return TIMETABLE_7_DAYS;
    }

    /**
     * Adds a {@code Period} to a specified day.
     *
     * @param dayNumber the {@code int} representing the day of the week, 1 represents Monday.
     * @param period a period of time to be added into the timetable.
     * @return true if successfully added.
     */
    public boolean addPeriodToDay(int dayNumber, Period period) throws NumberOfDaysException, OverlapPeriodException {
        if (dayNumber < 1 || dayNumber > this.numOfDays) {
            throw new NumberOfDaysException();
        }

        Day day = this.days.get(dayNumber - 1);
        return day.addPeriod(period);
    }

    /**
     * Adds a Collection of {@code Period} to a specified day.
     *
     * @param dayNumber the {@code int} representing the day of the week, 1 represents Monday.
     * @param periods a {@code Collection} of {@code Period} of time to be added into the timetable.
     * @return {@code false} if the day specified is not within the week, otherwise {@code true}.
     * @throws OverlapPeriodException if there is an overlap in the periods given.
     */
    public boolean addPeriodsToDay(int dayNumber, ArrayList<Period> periods) throws OverlapPeriodException {
        if (dayNumber < 1 || dayNumber > this.numOfDays) {
            return false;
        }

        for (Period period : periods) {
            addPeriodToDay(dayNumber, period);
        }
        return true;
    }

    public String convertToCommandString() {
        StringBuilder sb = new StringBuilder();
        for (Day day : days) {
            sb.append(day.convertToCommandString());
        }

        return sb.toString();
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
