package seedu.address.model.booking;

/**
 * Represents a booking
 */
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

import java.time.LocalDateTime;
import java.util.Objects;

public class Booking {
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

    public Booking(String name, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Booking)) {
            return false;
        }

        Booking otherBooking = (Booking) other;
        return name.equals(otherBooking.name)
                && start.equals(otherBooking.start)
                && end.equals(otherBooking.end);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
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