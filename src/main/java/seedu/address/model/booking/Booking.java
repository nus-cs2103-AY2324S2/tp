package seedu.address.model.booking;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;

/**
 * Represents a booking made by a person.
 */
public class Booking {
    private final Name name;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a booking with the specified name, start time, and end time.
     *
     * @param name The name of the person associated with this booking.
     * @param start The start time of the booking.
     * @param end The end time of the booking.
     */
    public Booking(Name name, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    /**
     * Retrieves the name associated with this booking.
     *
     * @return The name associated with this booking.
     */
    public Name getName() {
        return name;
    }

    /**
     * Retrieves the start time of this booking.
     *
     * @return The start time of this booking.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Retrieves the end time of this booking.
     *
     * @return The end time of this booking.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two bookings are considered equal if they have the same name, start time, and end time.
     *
     * @param other The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
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


    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, start, end);
    }


    /**
     * Returns a string representation of the object.
     * In general, the toString method returns a string that "textually represents" this object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("start", start)
                .add("end", end)
                .toString();
    }

}
