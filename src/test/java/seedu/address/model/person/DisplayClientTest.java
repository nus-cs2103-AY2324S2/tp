package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

public class DisplayClientTest {

    @Test
    public void hasDisplayCLient() {
        DisplayClient displayClientNull = new DisplayClient(null);
        DisplayClient displayClientNonNull = new DisplayClient(ALICE);

        // null person
        assertFalse(displayClientNull.hasDisplayClient());

        // non-null person
        assertTrue(displayClientNonNull.hasDisplayClient());
    }

    @Test
    public void equals() {
        DisplayClient displayClientNull = new DisplayClient(null);
        DisplayClient displayClientNonNull = new DisplayClient(ALICE);

        // non-null - same values -> returns true
        assertTrue(displayClientNonNull.equals(new DisplayClient(ALICE)));

        // non-null - same object -> returns true
        assertTrue(displayClientNonNull.equals(displayClientNonNull));

        // non-null - null -> returns false
        assertFalse(displayClientNonNull.equals(null));

        // non-null - different values -> returns false
        assertFalse(displayClientNonNull.equals(BENSON));

        // null - same values -> returns true
        assertTrue(displayClientNull.equals(new DisplayClient(null)));

        // null - same object -> returns true
        assertTrue(displayClientNull.equals(displayClientNull));

        // null - different values -> returns false
        assertFalse(displayClientNull.equals(BENSON));
    }
}
