package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BoltTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Bolt(null));
    }

    @Test
    public void constructor_invalidBolt_throwsIllegalArgumentException() {
        Integer invalidBolt = -1;
        assertThrows(IllegalArgumentException.class, () -> new Bolt(invalidBolt));
    }

    @Test
    public void isValidBolt() {
        // null bolt
        assertThrows(NullPointerException.class, () -> Bolt.isValidBolt(null));

        // invalid bolts
        assertFalse(Bolt.isValidBolt(-1)); // negative number
        assertFalse(Bolt.isValidBolt(-5)); // negative number

        // valid bolts
        assertTrue(Bolt.isValidBolt(0)); // zero bolts
        assertTrue(Bolt.isValidBolt(1)); // positive number
        assertTrue(Bolt.isValidBolt(5)); // positive number
    }

    @Test
    public void equals() {
        Bolt bolt = new Bolt(1);

        // same values -> returns true
        assertTrue(bolt.equals(new Bolt(1)));

        // same object -> returns true
        assertTrue(bolt.equals(bolt));

        // null -> returns false
        assertFalse(bolt.equals(null));

        // different types -> returns false
        assertFalse(bolt.equals(5.0f));

        // different values -> returns false
        assertFalse(bolt.equals(new Bolt(2)));
    }
}
