package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.reservation.Reservation;

/**
 * A utility class containing a list of {@code Reservation} objects to be used in tests.
 */
public class TypicalReservations {
    public static final Reservation ALICE_RSV = new ReservationBuilder()
            .withPerson(ALICE)
            .withDate("2024-01-01")
            .withTime("0000")
            .withPax("1").build();
    public static final Reservation BOB_RSV = new ReservationBuilder()
            .withPerson(BOB)
            .withDate("2024-12-31")
            .withTime("2359")
            .withPax("8").build();

    /**
     * Returns an {@code AddressBook} with all the typical reservations AND their persons.
     * This is because a reservation cannot exist without the person who made it.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Reservation reservation : getTypicalReservations()) {
            ab.addPerson(reservation.getPerson());
            ab.addReservation(reservation);
        }
        return ab;
    }

    public static List<Reservation> getTypicalReservations() {
        return new ArrayList<>(Arrays.asList(ALICE_RSV, BOB_RSV));
    }
}
