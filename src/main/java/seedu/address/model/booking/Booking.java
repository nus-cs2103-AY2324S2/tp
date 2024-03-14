package seedu.address.model.booking;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a booking
 */
public class Booking {
    /** Name of the booking */
    private Description description;
    /** Start time of the booking */
    private StartTime start;
    /** End time of the booking */
    private EndTime end;

    /**
     * Constructs a booking
     * @param description Description of the booking
     * @param start Start time in ISO_LOCAL_DATE_TIME format (2023-12-31T19:00)
     * @param end End time in ISO_LOCAL_DATE_TIME format
     */
    public Booking(Description description, StartTime start, EndTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public Description getDescription() {
        return description;
    }

    public StartTime getStart() {
        return start;
    }

    public EndTime getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Booking)) {
            return false;
        }
        Booking otherBooking = (Booking) other;

        return Objects.equals(description, otherBooking.description)
                && Objects.equals(start, otherBooking.start)
                && Objects.equals(end, otherBooking.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, start, end);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("description", description)
                .add("start", start)
                .add("end", end)
                .toString();
    }
}
