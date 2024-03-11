package seedu.address.model.booking;

/**
 * Represents a booking
 */
import seedu.address.commons.util.ToStringBuilder;

import java.util.Objects;

public class Booking {
    private BookingName name;
    private StartTime start;
    private EndTime end;

    public Booking(BookingName name, StartTime start, EndTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public BookingName getName() {
        return name;
    }

    public StartTime getStart() {
        return start;
    }

    public EndTime getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Booking)) return false;
        Booking otherBooking = (Booking) other;
        return Objects.equals(name, otherBooking.name) &&
                Objects.equals(start, otherBooking.start) &&
                Objects.equals(end, otherBooking.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, start, end);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("start", start)
                .add("end", end)
                .toString();
    }
}