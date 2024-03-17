package staffconnect.model.meeting;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Meeting's starting time in the staff book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMeetDateTime(String)}
 */
public class MeetDateTime {

    public static final String MESSAGE_CONSTRAINTS = "DateTime should be of the correct format dd/mm/yyyy HH:mm";
    public static final String VALIDATION_REGEX = "\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}";

    private static final DateTimeFormatter PROCESS_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final LocalDateTime value;


    /**
     * Constructs a {@code MeetDateTime}.
     *
     * @param date A valid date.
     */
    public MeetDateTime(String date) {
        requireNonNull(date);
        checkArgument(isValidMeetDateTime(date), MESSAGE_CONSTRAINTS);
        value = java.time.LocalDateTime.parse(date, PROCESS_FORMAT);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidMeetDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public LocalDateTime getDateTime() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetDateTime)) {
            return false;
        }

        MeetDateTime otherDate = (MeetDateTime) other;
        return value.equals(otherDate.value);
    }

    @Override
    public String toString() {
        return value.format(PROCESS_FORMAT);
    }

}
