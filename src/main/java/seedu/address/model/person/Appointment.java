package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.util.HashMap;

/**
 * Represents a Person's appointment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointment(String)}
 */
public class Appointment {
    public static final String MESSAGE_CONSTRAINTS = "Appointment should be of the format 'HH:MM DAY' "
            + "and adhere to the following constraints:\n"
            + "1. HH:MM follows 24 hour time; "
            + "HH is from 00 to 23, "
            + "MM is from 00 to 59."
            + "2. This is followed by a DAY. "
            + "DAY must be one of: 'MON', 'TUE', 'WED','THU', 'FRI', 'SAT','SUN'\n";
    // alphanumeric and special characters
    private static final String HOUR = "[\\d]{2}";
    private static final String MINUTE = "[\\d]{2}";
    private static final String TIME = HOUR + ":" + MINUTE;
    private static final String DAY = "[A-Z]{3}";
    public static final String VALIDATION_REGEX = TIME + "[\\s]+" + DAY;

    private static final HashMap<String, DayOfWeek> dayToDayOfWeek;

    // initialize map from String to DayOfWeek
    static {
        dayToDayOfWeek = new HashMap<>();
        dayToDayOfWeek.put("SUN", DayOfWeek.SUNDAY);
        dayToDayOfWeek.put("MON", DayOfWeek.MONDAY);
        dayToDayOfWeek.put("TUE", DayOfWeek.TUESDAY);
        dayToDayOfWeek.put("WED", DayOfWeek.WEDNESDAY);
        dayToDayOfWeek.put("THU", DayOfWeek.THURSDAY);
        dayToDayOfWeek.put("FRI", DayOfWeek.FRIDAY);
        dayToDayOfWeek.put("SAT", DayOfWeek.SATURDAY);
    }

    public final String value;

    /**
     * Constructs a {@code Appointment}.
     *
     * @param appointment A valid appointment.
     */
    public Appointment(String appointment) {
        requireNonNull(appointment);
        checkArgument(isValidAppointment(appointment), MESSAGE_CONSTRAINTS);
        value = appointment;
    }

    /**
     * Returns true if a given string is a appointment.
     */
    public static boolean isValidAppointment(String test) {
        if (!(test.matches(VALIDATION_REGEX))) {
            return false;
        }

        int hour = Integer.parseInt(test.substring(0, 2));
        int minute = Integer.parseInt(test.substring(3, 5));
        String day = test.substring(6, 9);

        boolean hourValid = -1 < hour && hour < 24;
        boolean minuteValid = -1 < minute && minute < 60;
        boolean dayValid = dayToDayOfWeek.containsKey(day);

        return hourValid && minuteValid && dayValid;
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
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return value.equals(otherAppointment.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
