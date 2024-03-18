package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommissionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Commission(null));
    }

    @Test
    public void constructor_invalidCommission_throwsIllegalArgumentException() {
        String invalidCommission = "20/hr";
        assertThrows(IllegalArgumentException.class, () -> new Commission(invalidCommission));
    }

    @Test
    public void isValidCommission() {
        // null commission
        assertThrows(NullPointerException.class, () -> Commission.isValidCommission(null));

        // invalid commission
        assertFalse(Commission.isValidCommission("")); // empty string
        assertFalse(Commission.isValidCommission(" ")); // spaces only
        assertFalse(Commission.isValidCommission("50"));
        assertFalse(Commission.isValidCommission("50/hr"));
        assertFalse(Commission.isValidCommission("$50"));

        // valid commission
        assertTrue(Commission.isValidCommission("$500/hr")); // exactly 3 numbers
        assertTrue(Commission.isValidCommission("$50/hr"));
    }

    @Test
    public void equals() {
        Commission commission = new Commission("$50/hr");

        // same values -> returns true
        assertTrue(commission.equals(new Commission("$50/hr")));

        // same object -> returns true
        assertTrue(commission.equals(commission));

        // null -> returns false
        assertFalse(commission.equals(null));

        // different types -> returns false
        assertFalse(commission.equals("$50/hr"));

        // different values -> returns false
        assertFalse(commission.equals(new Commission("$60/hr")));
    }
}
