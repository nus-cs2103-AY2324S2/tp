package educonnect.model.student.timetable;

import static java.util.Objects.requireNonNull;
import static educonnect.logic.parser.CliSyntax.PREFIXES_TIMETABLE_DAYS;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import educonnect.model.student.timetable.exceptions.OverlapPeriodException;

/**
 * Represents a day in a weekly timetable schedule.
 */
public class Day {
    private final DayOfWeek dayOfWeek;
    private final ArrayList<Period> periods;

    /**
     * Constructor for JSON Serialisation, included only for JSON to work, not intended as a constructor to be used!
     */
    private Day() {
        this.dayOfWeek = null;
        this.periods = new ArrayList<>();
    }

    /**
     * Constructor for {@code Day} objects, uses {@code int} as input.
     * @param day int representing the day of the week,
     *            starts from 1, representing MONDAY,
     *            till 7, representing SUNDAY.
     */
    public Day(int day) {
        this.dayOfWeek = DayOfWeek.of(day);
        this.periods = new ArrayList<>();
    }

    /**
     * Constructor for {@code Day} objects, uses {@code DayOfWeek} as input.
     * @param day {@code DayOfWeek} enum, e.g {@code DayOfWeek.MONDAY}.
     */
    public Day(DayOfWeek day) {
        requireNonNull(day);
        this.dayOfWeek = day;
        this.periods = new ArrayList<>();
    }

    /**
     * Checks if the days are the same.
     *
     * @param day Other day to be checked against.
     * @return {@code True} if it is the same day.
     */
    public boolean isSameDay(Day day) {
        return this.dayOfWeek.equals(day.dayOfWeek);
    }

    /**
     * Adds a {@code Period} into this {@code Day}. The period cannot overlap with another period.
     * Automatically sorts all periods after each addition.
     *
     * @param period a {@code Period} object.
     * @return {@code true} if added successfully.
     */
    public boolean addPeriod(Period period) throws OverlapPeriodException {
        for (Period per : periods) {
            if (per.hasOverlap(period)) {
                throw new OverlapPeriodException();
            }
        }
        this.periods.add(period);
        Collections.sort(this.periods);
        return true;
    }

    /**
     * Check if list of {@code Period} is sorted.
     *
     * @return {@code true} if the list of periods is sorted.
     */
    public boolean isSorted() {
        return this.periods.stream().sorted().collect(Collectors.toList()).equals(this.periods);
    }

    public String convertToCommandString() {
        if (periods.isEmpty()) {
            return ""; // returns empty string as no command specified will result in a day with no periods
        }

        StringBuilder sb = new StringBuilder();
        int index = dayOfWeek.getValue() - 1;
        sb.append(PREFIXES_TIMETABLE_DAYS[index]).append(" ");  // appends the correct prefix

        for (Period period : periods) {
            if (sb.length() != 5) { // 5 because no matter which prefix, with space, the length is always 5
                sb.append(", ");
            }
            sb.append(period.convertToCommandString());
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("For ").append(dayOfWeek).append(", schedule is:\n");
        for (Period per : periods) {
            sb.append(per);
        }
        return sb.toString();
    }
}
