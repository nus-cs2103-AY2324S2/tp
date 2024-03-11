package seedu.findvisor.model.person;

import static seedu.findvisor.commons.util.AppUtil.checkArgument;
import static seedu.findvisor.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.findvisor.commons.util.DateTimeUtil;
import seedu.findvisor.commons.util.ToStringBuilder;

/**
 * Represents a meeting with a Person in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(LocalDateTime, LocalDateTime)}
 */
public class Meeting {
    public static final String MESSAGE_CONSTRAINTS = "Meetings consist of 2 datetimes, the start datetime and "
            + "end date time.\n"
            + "Each input string for datetime must be in the format yyyy-MM-dd'T'HH:mm, for example 2024-02-23T16:00.\n"
            + "The start datetime must be before the end date time.";

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
        return start.isBefore(end);
    }

    public String getStartString() {
        return DateTimeUtil.dateTimeToString(start);
    }

    public String getEndString() {
        return DateTimeUtil.dateTimeToString(end);
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

