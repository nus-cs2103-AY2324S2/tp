package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class RoomNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoomNumber(null));
    }

    @Test
    public void constructor_invalidRoomNumber_throwsIllegalArgumentException() {
        String invalidRoomNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new RoomNumber(invalidRoomNumber));
    }

    @Test
    public void isValidRoomNumber() {
        // null address
        assertThrows(NullPointerException.class, () -> RoomNumber.isValidRoomNumber(null));

        // invalid addresses
        assertFalse(RoomNumber.isValidRoomNumber("")); // empty string
        assertFalse(RoomNumber.isValidRoomNumber(" ")); // spaces only

        // valid addresses
        assertTrue(RoomNumber.isValidRoomNumber("12-12"));
        assertTrue(RoomNumber.isValidRoomNumber("01-02"));
        assertTrue(RoomNumber.isValidRoomNumber("03-01"));
    }

    @Test
    public void isOutdated() {
        RoomNumber roomNumber = new RoomNumber("01-01");
        assertFalse(roomNumber.isOutdated());

        // If updated on the lastResultRelease for this AY
        LocalDate date1 = LocalDate.parse("2020-04-12");
        date1 = date1.withYear(LocalDate.now().getYear());
        if (date1.isAfter(LocalDate.now())) {
            date1 = date1.minusYears(1);
        }
        assertFalse(RoomNumber.isOutdated(date1));

        // If updated on the firstResultRelease for this AY
        LocalDate date2 = LocalDate.parse("2020-04-05");
        date2 = date2.withYear(LocalDate.now().getYear());
        if (date2.isAfter(LocalDate.now())) {
            date2 = date2.minusYears(1);
        }
        assertFalse(RoomNumber.isOutdated(date2));

        // If updated before the firstResultRelease for this AY
        LocalDate date3 = date2.minusDays(1);
        assertTrue(RoomNumber.isOutdated(date3));
    }

    @Test
    public void equals() {
        RoomNumber roomNumber = new RoomNumber("01-01");

        // same values -> returns true
        assertTrue(roomNumber.equals(new RoomNumber("01-01")));

        // same object -> returns true
        assertTrue(roomNumber.equals(roomNumber));

        // null -> returns false
        assertFalse(roomNumber.equals(null));

        // different types -> returns false
        assertFalse(roomNumber.equals(5.0f));

        // different values -> returns false
        assertFalse(roomNumber.equals(new RoomNumber("02-02")));
    }
}
