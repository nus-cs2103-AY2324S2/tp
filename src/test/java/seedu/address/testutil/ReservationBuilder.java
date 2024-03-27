package seedu.address.testutil;

import seedu.address.model.person.Person;
import seedu.address.model.reservation.Pax;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.RsvDate;
import seedu.address.model.reservation.RsvTime;

/**
 * A utility class to help with building Reservation objects.
 */
public class ReservationBuilder {

    public static final String DEFAULT_DATE = "2024-03-14";
    public static final String DEFAULT_TIME = "1337";
    public static final String DEFAULT_PAX = "4";

    private Person person;
    private RsvDate date;
    private RsvTime time;
    private Pax pax;

    /**
     * Creates a {@code ReservationBuilder} with the default details.
     */
    public ReservationBuilder() {
        person = new PersonBuilder().build();
        date = new RsvDate(DEFAULT_DATE);
        time = new RsvTime(DEFAULT_TIME);
        pax = new Pax(DEFAULT_PAX);
    }

    /**
     * Initializes the ReservationBuilder with the data of {@code reservationToCopy}.
     */
    public ReservationBuilder(Reservation reservationToCopy) {
        person = reservationToCopy.getPerson();
        date = reservationToCopy.getDate();
        time = reservationToCopy.getTime();
        pax = reservationToCopy.getPax();
    }

    /**
     * Sets the {@code Person} of the {@code Reservation} that we are building.
     */
    public ReservationBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    /**
     * Sets the {@code RsvDate} of the {@code Reservation} that we are building.
     */
    public ReservationBuilder withDate(String date) {
        this.date = new RsvDate(date);
        return this;
    }

    /**
     * Sets the {@code RsvTime} of the {@code Reservation} that we are building.
     */
    public ReservationBuilder withTime(String time) {
        this.time = new RsvTime(time);
        return this;
    }

    /**
     * Sets the {@code Pax} of the {@code Reservation} that we are building.
     */
    public ReservationBuilder withPax(String pax) {
        this.pax = new Pax(pax);
        return this;
    }

    public Reservation build() {
        return new Reservation(person, date, time, pax);
    }

}

