package educonnect.model.student.timetable;

import static educonnect.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;

import educonnect.model.student.timetable.exceptions.InvalidPeriodException;

/**
 * Class for a {@code Period} of time.
 */
public class Period implements Comparable<Period> {
    private final String periodName;
    private final LocalTime timeStart;
    private final LocalTime timeEnd;

    /**
     * Constructor for this class. All parameters require non-null.
     *
     * @param periodName the name of this {@code Period}
     * @param timeStart the start timing of this {@code Period}
     * @param timeEnd the end timing of this {@code Period}
     * @throws InvalidPeriodException if the name is invalid or if the end timing is before the start timing.
     */
    Period(String periodName, LocalTime timeStart, LocalTime timeEnd) throws InvalidPeriodException {
        requireAllNonNull(periodName, timeStart, timeEnd);

        if (timeEnd.isBefore(timeStart) || periodName.isBlank()) {
            throw new InvalidPeriodException();
        }

        this.periodName = periodName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Checks if this {@code Period} has overlap with another {@code Period} object.
     *
     * @param period Other {@code Period} object to check against.
     * @return {@code true} if there is any overlap.
     */
    public boolean hasOverlap(Period period) {
        return this.timeStart.isBefore(period.timeEnd) && period.timeStart.isBefore(this.timeEnd);
    }

    @Override
    public int compareTo(Period period) {
        return this.timeStart.compareTo(period.timeStart);
    }

    @Override
    public String toString() {
        return "Period " + this.periodName + ": (" + this.timeStart + " to " + this.timeEnd + ")\n";
    }
}
