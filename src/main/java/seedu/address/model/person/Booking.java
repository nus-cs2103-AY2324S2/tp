package seedu.address.model.person;

import java.time.LocalDateTime;
import java.util.Objects;
import seedu.address.commons.util.ToStringBuilder;


public class Booking {
    private final Name name;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Booking(Name name, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public Name getName() {
        return name;
    }

    public LocalDateTime getStart() {
        return start;
    }
    public LocalDateTime getEnd() {
        return end;
    }

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
