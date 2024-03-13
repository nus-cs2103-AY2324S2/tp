package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a time.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time implements Comparable<Time> {

    public static final String MESSAGE_CONSTRAINTS = "Time should be of the format HH:mm "
            + "and adhere to the following constraints:\n"
            + "1. The HH part should only contain integers between 0 to 23 (24-hour clock) \n"
            + "2. The mm part should only contain integers between 0 to 59\n";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    private static final String HOUR_REGEX = "([01]?[0-9]|2[0-3])";
    private static final String MINUTE_REGEX = "[0-5][0-9]";
    private static final String VALIDATION_REGEX = HOUR_REGEX + ":" + MINUTE_REGEX;

    public final LocalTime value;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time string in the format "HH:mm".
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = LocalTime.parse(time);
    }

    /**
     * Returns if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int compareTo(Time otherTime) {
        return value.compareTo(otherTime.value);
    }

    @Override
    public String toString() {
        return value.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Time)) {
            return false;
        }

        Time otherTime = (Time) other;
        return value.equals(otherTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

