package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a meeting with a Person in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(LocalDateTime, LocalDateTime)}
 */
public class Meeting {
    public static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final String MESSAGE_CONSTRAINTS = "TODO";

    public final LocalDateTime start;
    public final LocalDateTime end;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Meeting(LocalDateTime start, LocalDateTime end) {
        requireAllNonNull(start, end);
        checkArgument(isValidDateTime(start, end), MESSAGE_CONSTRAINTS);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidDateTime(LocalDateTime start, LocalDateTime end) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (!start.isAfter(currentDateTime)) {
            return false;
        }
        return start.isBefore(end);
    }

    private static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMAT);
    }

    public String getStartString() {
        return dateTimeToString(start);
    }

    public String getEndString() {
        return dateTimeToString(end);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Start Datetime", start)
                .add("End Datetime", end)
                .toString();
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
        return start.equals(otherMeeting.start)
                && end.equals(otherMeeting.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

}

