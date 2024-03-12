package staffconnect.model.person;

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
    public static final String VALIDATION_REGEX = "((mon|tues|wed(nes)?|thur(s)?|fri|sat(ur)?|sun)(day)?)";

    public final String value;

    /**
     * Constructs a {@code Availability}.
     *
     * @param availability A valid availability.
     */
    public Availability(String availability) {
        requireNonNull(availability);
        checkArgument(isValidAvailability(availability), MESSAGE_CONSTRAINTS);
        if (availability.matches("(mon)(day)?")) {
            value = DayOfWeek.MONDAY.name();
        } else if (availability.matches("(tues)(day)?")) {
            value = DayOfWeek.TUESDAY.name();
        } else if (availability.matches("(wed)(nes)?(day)?")) {
            value = DayOfWeek.WEDNESDAY.name();
        } else if (availability.matches("(thur)(s)(day)?")) {
            value = DayOfWeek.THURSDAY.name();
        } else if (availability.matches("(fri)(day)?")) {
            value = DayOfWeek.FRIDAY.name();
        } else if (availability.matches("(sat)(ur)?(day)?")) {
            value = DayOfWeek.SATURDAY.name();
        } else {
            value = DayOfWeek.SUNDAY.name();
        }
    }

    /**
     * Returns true if a given string is a valid availability.
     */
    public static boolean isValidAvailability(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
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
