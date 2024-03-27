package seedu.address.model.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAX_BOB_RSV;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalReservations.ALICE_RSV;
import static seedu.address.testutil.TypicalReservations.BOB_RSV;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.reservation.exceptions.DuplicateReservationException;
import seedu.address.model.reservation.exceptions.ReservationNotFoundException;
import seedu.address.testutil.ReservationBuilder;

class UniqueReservationListTest {

    private final UniqueReservationList uniqueReservationList = new UniqueReservationList();

    @Test
    public void contains_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReservationList.contains(null));
    }

    @Test
    public void contains_reservationNotInList_returnsFalse() {
        assertFalse(uniqueReservationList.contains(ALICE_RSV));
    }

    @Test
    public void contains_reservationInList_returnsTrue() {
        uniqueReservationList.add(ALICE_RSV);
        assertTrue(uniqueReservationList.contains(ALICE_RSV));
    }

    @Test
    public void contains_sameReservationWithDifferentPaxInList_returnsTrue() {
        uniqueReservationList.add(ALICE_RSV);
        Reservation editedAliceRsv = new ReservationBuilder(ALICE_RSV).withPax(VALID_PAX_BOB_RSV).build();
        assertTrue(uniqueReservationList.contains(editedAliceRsv));
    }

    @Test
    public void add_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReservationList.add(null));
    }

    @Test
    public void add_duplicateReservation_throwsDuplicateReservationException() {
        uniqueReservationList.add(ALICE_RSV);
        assertThrows(DuplicateReservationException.class, () -> uniqueReservationList.add(ALICE_RSV));
    }

    @Test
    public void remove_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReservationList.remove(null));
    }

    @Test
    public void remove_reservationDoesNotExist_throwsReservationNotFoundException() {
        assertThrows(ReservationNotFoundException.class, () -> uniqueReservationList.remove(ALICE_RSV));
    }

    @Test
    public void remove_existingReservation_removesReservation() {
        uniqueReservationList.add(ALICE_RSV);
        uniqueReservationList.remove(ALICE_RSV);
        UniqueReservationList expectedUniqueReservationList = new UniqueReservationList();
        assertEquals(expectedUniqueReservationList, uniqueReservationList);
    }

    @Test
    public void setReservations_nullUniqueReservationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> uniqueReservationList.setReservations((UniqueReservationList) null));
    }

    @Test
    public void setReservations_uniqueReservationList_replacesOwnListWithProvidedUniqueReservationList() {
        uniqueReservationList.add(ALICE_RSV);
        UniqueReservationList expectedUniqueReservationList = new UniqueReservationList();
        expectedUniqueReservationList.add(BOB_RSV);
        uniqueReservationList.setReservations(expectedUniqueReservationList);
        assertEquals(expectedUniqueReservationList, uniqueReservationList);
    }

    @Test
    public void setReservations_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> uniqueReservationList.setReservations((List<Reservation>) null));
    }

    @Test
    public void setReservations_list_replacesOwnListWithProvidedList() {
        uniqueReservationList.add(ALICE_RSV);
        List<Reservation> reservationList = Collections.singletonList(BOB_RSV);
        uniqueReservationList.setReservations(reservationList);
        UniqueReservationList expectedUniqueReservationList = new UniqueReservationList();
        expectedUniqueReservationList.add(BOB_RSV);
        assertEquals(expectedUniqueReservationList, uniqueReservationList);
    }

    @Test
    public void setReservations_listWithDuplicateReservations_throwsDuplicateReservationException() {
        List<Reservation> listWithDuplicateReservations = Arrays.asList(ALICE_RSV, ALICE_RSV);
        assertThrows(DuplicateReservationException.class, ()
                -> uniqueReservationList.setReservations(listWithDuplicateReservations));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueReservationList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueReservationList.asUnmodifiableObservableList().toString(),
                uniqueReservationList.toString());
    }

}
