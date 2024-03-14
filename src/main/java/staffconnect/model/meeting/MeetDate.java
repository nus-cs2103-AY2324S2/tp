package staffconnect.model.meeting;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Meeting's starting time in the staff book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
 */
public class MeetDate {

    public static final String MESSAGE_CONSTRAINTS = "Date should be of the correct format dd/mm/yyyy";
    public static final String VALIDATION_REGEX = "\\d{2}/\\d{2}/\\d{4}";

    private static final DateTimeFormatter PROCESS_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy");

    private final LocalDateTime value;


    /**
     * Constructs a {@code MeetDate}.
     *
     * @param date A valid date.
     */
    public MeetDate(String date) {
        requireNonNull(date);
        checkArgument(isValidStartTime(date), MESSAGE_CONSTRAINTS);
        value = java.time.LocalDateTime.parse(date, PROCESS_FORMAT);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidStartTime(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof staffconnect.model.meeting.MeetDate)) {
            return false;
        }

        staffconnect.model.meeting.MeetDate otherDate = (staffconnect.model.meeting.MeetDate) other;
        return value.equals(otherDate.value);
    }

    @Override
    public String toString() {
        return value.format(PROCESS_FORMAT);
    }

}
