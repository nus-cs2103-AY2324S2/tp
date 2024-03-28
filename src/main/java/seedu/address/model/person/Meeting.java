package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.ParserUtil;

/**
 * Represents a Person's meeting object in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Meeting {
    public final String desc;
    public final LocalDate date;
    public final LocalTime start;
    public final LocalTime end;

    /**
     * Constructs a {@code Meeting}.
     *
     * @param description Description of the meeting.
     */
    public Meeting(String description, String date, String start, String end) {
        requireNonNull(description);
        desc = description;
        this.date = date.isEmpty() ? null : ParserUtil.parseDate(date);
        this.start = start.isEmpty() ? null : ParserUtil.parseTime(start);
        this.end = end.isEmpty() ? null : ParserUtil.parseTime(end);
    }

    @Override
    public String toString() {
        if (desc.equals("") || date == null || start == null || end == null) {
            return "";
        }
        String formattedDate = date.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        String formattedStart = start.format(DateTimeFormatter.ofPattern("HHmm"));
        String formattedEnd = end.format(DateTimeFormatter.ofPattern("HHmm"));
        return desc + ": " + formattedDate + " (" + formattedStart + " - " + formattedEnd + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Meeting // instanceof handles nulls
                && desc.equals(((Meeting) other).desc)
                && date.equals(((Meeting) other).date)
                && start.equals(((Meeting) other).start)
                && end.equals(((Meeting) other).end)); // check states
    }

    @Override
    public int hashCode() {
        return desc.hashCode();
    }
}
