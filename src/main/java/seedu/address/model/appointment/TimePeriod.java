package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
/**
 * Represents a time period with start and end times
 * Guarantees: immutable; is valid as declared in {@link #isValidTimePeriod(Time, Time)}
 */
public class TimePeriod implements Comparable<TimePeriod> {

    public static final String MESSAGE_CONSTRAINTS = "End time of time period "
            + "should be after start time";
    private final Time startTime;
    private final Time endTime;

    /**
     * Constructs a {@code TimePeriod} with the given start and end times.
     *
     * @param startTime The start time of the period.
     * @param endTime   The end time of the period.
     */
    public TimePeriod(Time startTime, Time endTime) {
        requireNonNull(startTime);
        requireNonNull(endTime);
        checkArgument(isValidTimePeriod(startTime, endTime), MESSAGE_CONSTRAINTS);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the start time of the period.
     *
     * @return The start time.
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the period.
     *
     * @return The end time.
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Checks if the given start time is before the end time.
     *
     * @param startTime The start time.
     * @param endTime   The end time.
     * @return True if the start time is before the end time, false otherwise.
     */
    public static boolean isValidTimePeriod(Time startTime, Time endTime) {
        return startTime.compareTo(endTime) < 0;
    }

    @Override
    public String toString() {
        return startTime.toString() + " to " + endTime.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimePeriod)) {
            return false;
        }

        TimePeriod otherTimePeriod = (TimePeriod) other;
        return startTime.equals(otherTimePeriod.startTime)
                && endTime.equals(otherTimePeriod.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    @Override
    public int compareTo(TimePeriod other) {
        return startTime.compareTo(other.startTime);
    }

}
