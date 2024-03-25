package staffconnect.model.availability;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's availability in the staff book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAvailability(String)}
 */
public class Availability {


    public static final String MESSAGE_CONSTRAINTS = "[DAY_OF_THE_WEEK] [START_TIME] [END_TIME] E.g. mon 09:00 13:00\n"
            + "[DAY_OF_THE_WEEK] Should be a week of the day, the full word or the first syllable of the word\n"
            + "[START_TIME], [END_TIME] Using a 24H digital time format HH:mm\n";
    public static final String VALIDATION_REGEX_DAY = "(?i)((mon|tue(s)?|wed(nes)?|thu(r)?(rs)?|fri|sat(ur)?|sun)"
            + "(day)?)";
    public static final String VALIDATION_REGEX = VALIDATION_REGEX_DAY + " [0-9]{2}:[0-9]{2} [0-9]{2}:[0-9]{2}";

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");


    public final String value;
    private final DayOfWeek day;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructs a {@code Availability}.
     *
     * @param availability A valid availability.
     */
    public Availability(String availability) {
        requireNonNull(availability);
        checkArgument(isValidAvailability(availability), MESSAGE_CONSTRAINTS);
        String [] availabilityFields = availability.split(" ");
        day = parseToDayOfWeek(availabilityFields[0]);
        startTime = parseToLocalTime(availabilityFields[1]);
        endTime = parseToLocalTime(availabilityFields[2]);
        value = day + " " + startTime + " " + endTime;
    }

    /**
     * Returns true if a given string is a valid availability.
     */
    public static boolean isValidAvailability(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        String [] availabilityFields = test.split(" ");

        if (!isValidLocalTime(availabilityFields[1]) || !isValidLocalTime(availabilityFields[2])) {
            return false;
        }

        LocalTime startTime = parseToLocalTime(availabilityFields[1]);
        LocalTime endTime = parseToLocalTime(availabilityFields[2]);
        return startTime.isBefore(endTime);
    }

    /**
     * Returns true if a given string is a valid LocalTime.
     */
    public static boolean isValidLocalTime(String test) {
        try {
            LocalTime.parse(test, timeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns a standardised Day of the Week.
     *
     * @param validDay a valid Day String to be translated.
     * @return Corresponding Day of Week.
     */
    public static DayOfWeek parseToDayOfWeek(String validDay) {
        if (validDay.matches("(?i)(mon)(day)?")) {
            return DayOfWeek.MONDAY;
        } else if (validDay.matches("(?i)(tue)(s)?(sday)?")) {
            return DayOfWeek.TUESDAY;
        } else if (validDay.matches("(?i)(wed)(nes)?(nesday)?")) {
            return DayOfWeek.WEDNESDAY;
        } else if (validDay.matches("(?i)(thu)(r)?(rs)?(rsday)?")) {
            return DayOfWeek.THURSDAY;
        } else if (validDay.matches("(?i)(fri)(day)?")) {
            return DayOfWeek.FRIDAY;
        } else if (validDay.matches("(?i)(sat)(ur)?(urday)?")) {
            return DayOfWeek.SATURDAY;
        } else {
            return DayOfWeek.SUNDAY;
        }
    }

    /**
     * Returns a standardised LocalTime.
     *
     * @param validTime a valid Time String to be translated.
     * @return Corresponding Local Time.
     */
    public static LocalTime parseToLocalTime(String validTime) {
        return LocalTime.parse(validTime, timeFormatter);
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
