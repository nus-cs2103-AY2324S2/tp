package educonnect.model.student.timetable;

import static java.util.Objects.requireNonNull;

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
    Day(DayOfWeek day) {
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
