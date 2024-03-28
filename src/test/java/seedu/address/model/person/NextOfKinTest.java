package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NextOfKinTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NextOfKin(null));
    }

    @Test
    public void constructor_invalidNextOfKin_throwsIllegalArgumentException() {
        String invalidNextOfKin = "";
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin(invalidNextOfKin));
    }

    @Test
    public void isValidNextOfKin() {
        // null next of kin
        assertThrows(NullPointerException.class, () -> NextOfKin.isValidNextOfKin(null));

        // invalid next of kins
        assertFalse(NextOfKin.isValidNextOfKin("")); // empty string
        assertFalse(NextOfKin.isValidNextOfKin(" ")); // spaces only

        // valid next of kins
        assertTrue(NextOfKin.isValidNextOfKin("Sam Sung"));
        assertTrue(NextOfKin.isValidNextOfKin("Alexander Maximilian Bartholomew Fitzwilliam III")); // long next of kin
    }

    @Test
    public void equals() {
        NextOfKin nextOfKin = new NextOfKin("Valid NextOfKin");

        // same values -> returns true
        assertTrue(nextOfKin.equals(new NextOfKin("Valid NextOfKin")));

        // same object -> returns true
        assertTrue(nextOfKin.equals(nextOfKin));

        // null -> returns false
        assertFalse(nextOfKin.equals(null));

        // different types -> returns false
        assertFalse(nextOfKin.equals(5.0f));

        // different values -> returns false
        assertFalse(nextOfKin.equals(new NextOfKin("Other Valid NextOfKin")));
    }
}
