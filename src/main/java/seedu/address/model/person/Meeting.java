package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Client's meeting time in the address book.
 * Guarantees: immutable; is always valid
 */
public class Meeting implements Comparable<Meeting> {
    private static String name;
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting has to be in yyyy-MM-dd HH:mm:ss format, and it should not be blank";
    // The VALIDATION_REGEX for meeting time
    public static final String VALIDATION_REGEX =
            "^(\\d{4}-\\d{2}-\\d{2}(T\\d{2}:\\d{2}:\\d{2}| \\d{2}:\\d{2}:\\d{2})?)$";
    public final String value;
    public final LocalDateTime meeting;

    /**
     * Constructs an {@code Meeting}.
     */
    public Meeting(String meeting) {
        requireNonNull(meeting);
        this.value = meeting;
        this.meeting = stringToDateTime(meeting);
    }

    public LocalDateTime getMeeting() {
        return meeting;
    }

    /**
     * Returns true if a given string is a valid meeting.
     */
    public static boolean isValidMeeting(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * This method converts a string into a LocalDateTime object.
     * If the string is in the format "yyyy-MM-dd", it will be treated as a date at the start of the day.
     * If the string is in the format "yyyy-MM-ddTHH:mm:ss", it will be treated as a date with time.
     * Otherwise, the string is expected to be in the format "yyyy-MM-dd HH:mm:ss".
     *
     * @param dateTime The string to be converted into a LocalDateTime object.
     * @return A LocalDateTime object that represents the date and time specified by the input string.
     */
    private LocalDateTime stringToDateTime(String dateTime) {
        if (dateTime.length() <= 10) {
            return LocalDateTime.parse(dateTime + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else if (dateTime.contains("T")) {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
    
    public void setName(String finalName) {
        name = finalName;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return meeting.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return meeting.equals(otherMeeting.meeting);
    }

    @Override
    public int hashCode() {
        return meeting.hashCode();
    }

    @Override
    public int compareTo(Meeting o) {
        return this.meeting.compareTo(o.meeting);
    }
}
