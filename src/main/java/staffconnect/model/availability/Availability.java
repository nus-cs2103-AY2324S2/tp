package staffconnect.model.availability;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
/**
 * Represents a Person's availability in the staff book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAvailability(String)}
 */
public class Availability {


    public static final String MESSAGE_CONSTRAINTS =
            "Should be a week of the day, the full word or the first syllable of the word";
    public static final String VALIDATION_REGEX = "(?i)((mon|tue(s)?|wed(nes)?|thu(r)?(rs)?|fri|sat(ur)?|sun)(day)?)";

    public final String value;

    /**
     * Constructs a {@code Availability}.
     *
     * @param availability A valid availability.
     */
    public Availability(String availability) {
        requireNonNull(availability);
        checkArgument(isValidAvailability(availability), MESSAGE_CONSTRAINTS);
        value = strToDayOfWeek(availability);
    }

    /**
     * Returns true if a given string is a valid availability.
     */
    public static boolean isValidAvailability(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a standardised Day of the Week.
     *
     * @param validAvailability validatedAvailability to be translated.
     * @return Corresponding Day of Week.
     */
    public static String strToDayOfWeek(String validAvailability) {
        if (validAvailability.matches("(?i)(mon)(day)?")) {
            return DayOfWeek.MONDAY.name();
        } else if (validAvailability.matches("(?i)(tue)(s)?(sday)?")) {
            return DayOfWeek.TUESDAY.name();
        } else if (validAvailability.matches("(?i)(wed)(nes)?(nesday)?")) {
            return DayOfWeek.WEDNESDAY.name();
        } else if (validAvailability.matches("(?i)(thu)(r)?(rs)?(rsday)?")) {
            return DayOfWeek.THURSDAY.name();
        } else if (validAvailability.matches("(?i)(fri)(day)?")) {
            return DayOfWeek.FRIDAY.name();
        } else if (validAvailability.matches("(?i)(sat)(ur)?(urday)?")) {
            return DayOfWeek.SATURDAY.name();
        } else {
            return DayOfWeek.SUNDAY.name();
        }
    }

    @Override
    public String toString() {
        return '[' + value + ']';
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Availability)) {
            return false;
        }

        Availability otherAvailability = (Availability) other;
        return value.equals(otherAvailability.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
