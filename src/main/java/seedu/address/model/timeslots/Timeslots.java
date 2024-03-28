package seedu.address.model.timeslots;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Timeslot in the address book.
 * Guarantees: immutable; timeslot is valid as declared in {@link #isValidTimeslot(String)}
 */
public class Timeslots {

    public static final String MESSAGE_CONSTRAINTS = "Timeslot should be of the format: "
        + "DayOfWeek StartTime-EndTime, and adhere to the following constraints:"
        + "1. The DayOfWeek is any day from Monday to Sunday.\n"
        + "2. StartTime and EndTime include hours and optional minutes in 12-hour format. "
        + "Minutes, if included, should be separated from hours by a colon. \n"
        + "For example, 'Saturday 4pm-6pm', 'Tuesday 2:30pm-4:30pm'.\n";
    public static final String VALIDATION_REGEX = "^(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday) "
        + "(1[012]|[1-9])(:[0-5][0-9])?(am|pm)-(1[012]|[1-9])(:[0-5][0-9])?(am|pm)$";


    public final String timeslot;

    /**
     * Constructs a {@code Timeslot}.
     *
     * @param timeslot A valid timeslot.
     */
    public Timeslots(String timeslot) {
        requireNonNull(timeslot);
        checkArgument(isValidTimeslot(timeslot), MESSAGE_CONSTRAINTS);
        this.timeslot = timeslot;
    }

    /**
     * Returns true if a given string is a valid timeslot.
     */
    public static boolean isValidTimeslot(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Timeslots)) {
            return false;
        }

        Timeslots otherTimeslot = (Timeslots) other;
        return timeslot.equals(otherTimeslot.timeslot);
    }

    @Override
    public int hashCode() {
        return timeslot.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + timeslot + ']';
    }

}
