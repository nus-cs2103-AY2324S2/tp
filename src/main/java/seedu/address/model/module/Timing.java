package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a ModuleTiming's start or end time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTiming(String)}
 */
public class Timing {
    public static final String MESSAGE_CONSTRAINTS = "Time should be in the HHmm format, i.e. 1845";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
    private final LocalTime time;

    /**
     * Constructs a {@code Timing}.
     *
     * @param time A valid hour in HHmm format.
     */
    public Timing(String time) {
        requireNonNull(time);

        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);

        this.time = LocalTime.parse(time, formatter);
    }

    /**
     * Returns true if a given time string is in the valid HHmm format.
     */
    public static boolean isValidTime(String time) {
        try {
            LocalTime parsedDate = LocalTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalTime getTiming() {
        return time;
    }
}
