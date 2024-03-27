package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY_RSV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB_RSV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAX_AMY_RSV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAX_BOB_RSV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_AMY_RSV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_BOB_RSV;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalReservations.ALICE_RSV;
import static seedu.address.testutil.TypicalReservations.BOB_RSV;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ReservationBuilder;

class ReservationTest {

    @Test
    void isSameReservation() {
        // same object -> returns true
        assertTrue(ALICE_RSV.isSameReservation(ALICE_RSV));

        // null -> returns false
        assertFalse(ALICE_RSV.isSameReservation(null));

        // different pax, all other attributes same -> returns true
        Reservation editedAliceRsv = new ReservationBuilder(ALICE_RSV).withPax("100").build();
        assertTrue(ALICE_RSV.isSameReservation(editedAliceRsv));

        // different person, all other attributes same -> returns false
        editedAliceRsv = new ReservationBuilder(ALICE_RSV).withPerson(BOB).build();
        assertFalse(ALICE_RSV.isSameReservation(editedAliceRsv));

        // different date, all other attributes same -> returns false
        Reservation editedBobRsv = new ReservationBuilder(BOB_RSV).withDate("2025-01-01").build();
        assertFalse(BOB_RSV.isSameReservation(editedBobRsv));

        // different time, all other attributes same -> returns false
        editedBobRsv = new ReservationBuilder(BOB_RSV).withTime("1234").build();
        assertFalse(BOB_RSV.isSameReservation(editedBobRsv));
    }

    @Test
    void testEquals() {
        // same values -> returns true
        Reservation aliceRsvCopy = new ReservationBuilder(ALICE_RSV).build();
        assertTrue(ALICE_RSV.equals(aliceRsvCopy));

        // same object -> returns true
        assertTrue(ALICE_RSV.equals(ALICE_RSV));

        // null -> returns false
        assertFalse(ALICE_RSV.equals(null));

        // different type -> returns false
        assertFalse(ALICE_RSV.equals(5));

        // different reservation -> returns false
        assertFalse(ALICE_RSV.equals(BOB_RSV));

        // different person -> returns false
        Reservation editedAliceRsv = new ReservationBuilder(ALICE_RSV).withPerson(BOB).build();
        assertFalse(ALICE_RSV.equals(editedAliceRsv));

        // different date -> returns false
        editedAliceRsv = new ReservationBuilder(ALICE_RSV).withDate(VALID_DATE_BOB_RSV).build();
        assertFalse(ALICE_RSV.equals(editedAliceRsv));

        // different time -> returns false
        editedAliceRsv = new ReservationBuilder(ALICE_RSV).withTime(VALID_TIME_BOB_RSV).build();
        assertFalse(ALICE_RSV.equals(editedAliceRsv));

        // different pax -> returns false
        editedAliceRsv = new ReservationBuilder(ALICE_RSV).withPax(VALID_PAX_BOB_RSV).build();
        assertFalse(ALICE_RSV.equals(editedAliceRsv));
    }

    @Test
    public void toStringMethod() {
        System.out.println(ALICE_RSV.toString());
        String expected = Reservation.class.getCanonicalName() + "{person=" + ALICE_RSV.getPerson().toString()
                        + ", date=" + ALICE_RSV.getDate() + ", time=" + ALICE_RSV.getTime()
                        + ", pax=" + ALICE_RSV.getPax() + "}";
        assertEquals(expected, ALICE_RSV.toString());
    }

    @Test
    void getPersonMethod() {
        assertEquals(ALICE, ALICE_RSV.getPerson());
    }

    @Test
    void getDateMethod() {
        assertEquals(new RsvDate(VALID_DATE_AMY_RSV), ALICE_RSV.getDate());
    }

    @Test
    void getTimeMethod() {
        assertEquals(new RsvTime(VALID_TIME_AMY_RSV), ALICE_RSV.getTime());
    }

    @Test
    void getPaxMethod() {
        assertEquals(new Pax(VALID_PAX_AMY_RSV), ALICE_RSV.getPax());
    }
}
