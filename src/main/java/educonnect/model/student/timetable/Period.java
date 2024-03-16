package educonnect.model.student.timetable;

import static educonnect.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;

import educonnect.model.student.timetable.exceptions.InvalidPeriodException;

/**
 * Class for a {@code Period} of time.
 */
public class Period implements Comparable<Period> {
    public static final String VALIDATION_REGEX = "([0-9]|1[0-9]|2[0-3])-([0-9]|1[0-9]|2[0-3])";
    public static final String PERIOD_CONSTRAINTS =
            "Periods should be in the format of time-time, "
            + "where time can be any digit between 0 - 23. \n"
            + "This means that all Period objects are tracked with a 24-hour clock.";
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
    public Period(String periodName, LocalTime timeStart, LocalTime timeEnd) throws InvalidPeriodException {
        requireAllNonNull(periodName, timeStart, timeEnd);

        if (timeEnd.isBefore(timeStart) || periodName.isBlank()) {
            throw new InvalidPeriodException();
        }

        this.periodName = periodName.trim();
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Constructor for this class, accepts {@code String} as inputs.
     *
     * @param periodName the name of this {@code Period}
     * @param periodString the period of this {@code Period}, accepts only "0-23" in this format.
     * @throws InvalidPeriodException if the name is invalid or if the end timing is before the start timing.
     */
    public Period(String periodName, String periodString) throws InvalidPeriodException {
        requireAllNonNull(periodName, periodString);
        String[] args = periodString.split("-");
        LocalTime timeStart = LocalTime.of(Integer.parseInt(args[0]), 0, 0);
        LocalTime timeEnd = LocalTime.of(Integer.parseInt(args[1]), 0, 0);

        if (timeEnd.isBefore(timeStart) || periodName.isBlank()) {
            throw new InvalidPeriodException();
        }

        this.periodName = periodName.trim();
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Constructor for JSON Serialisation, included only for JSON to work, not intended as a constructor to be used!
     */
    private Period() {
        this.periodName = "";
        this.timeStart = null;
        this.timeEnd = null;
    }

    /**
     * Checks if a {@code String} input is in a valid period format.
     * @param period the period of this {@code Period}, accepts only "0-23" in this format.
     * @return {@code true} if the {@code String} is in the correct format.
     */
    public static boolean isValidPeriod(String period) {
        return period.matches(VALIDATION_REGEX);
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Period)) {
            return false;
        }

        Period period = (Period) obj;
        return this.timeStart.equals(period.timeStart) && this.timeEnd.equals(period.timeEnd);
    }

    @Override
    public String toString() {
        return "Period " + this.periodName + ": (" + this.timeStart + " to " + this.timeEnd + ")\n";
    }
}
