package seedu.address.model.reservation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents a Reservation in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reservation {

    private final Person person;
    private final RsvDate date;
    private final RsvTime time;
    private final Pax pax;

    /**
     * Every field must be present and not null.
     */
    public Reservation(Person person, RsvDate date, RsvTime time, Pax pax) {
        requireAllNonNull(person, date, time, pax);
        this.person = person;
        this.date = date;
        this.time = time;
        this.pax = pax;
    }

    public Person getPerson() {
        return this.person;
    }

    public RsvDate getDate() {
        return this.date;
    }

    public RsvTime getTime() {
        return this.time;
    }

    public Pax getPax() {
        return this.pax;
    }

    /**
     * Returns true if both reservations are created by the same person,
     * and have the same date and time.
     * This defines a weaker notion of equality between two reservations.
     */
    public boolean isSameReservation(Reservation otherReservation) {
        if (otherReservation == this) {
            return true;
        }

        return otherReservation != null
                && otherReservation.getPerson().equals(getPerson())
                && otherReservation.getDate().equals(getDate())
                && otherReservation.getTime().equals(getTime());
    }

    /**
     * Returns true if both reservations have the same fields.
     * This defines a stronger notion of equality between two reservations.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Reservation)) {
            return false;
        }

        Reservation otherReservation = (Reservation) other;
        return this.person.equals(otherReservation.person)
                && this.date.equals(otherReservation.date)
                && this.time.equals(otherReservation.time)
                && this.pax.equals(otherReservation.pax);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(person, date, time, pax);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person", person)
                .add("date", date)
                .add("time", time)
                .add("pax", pax)
                .toString();
    }

}
