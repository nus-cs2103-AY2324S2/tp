package seedu.address.model.timeslot;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.timeslots.Timeslots;

public class TimeslotTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Timeslots(null));
    }

    @Test
    public void constructor_invalidTimeslot_throwsIllegalArgumentException() {
        String invalidTimeslot = "";
        assertThrows(IllegalArgumentException.class, () -> new Timeslots(invalidTimeslot));
    }

    @Test
    public void isValidTimeslot() {
        // null timeslot name
        assertThrows(NullPointerException.class, () -> Timeslots.isValidTimeslot(null));
    }

}
