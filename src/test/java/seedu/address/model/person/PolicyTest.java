package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PolicyTest {

    @Test
    public void isValidExpiryDate() {
        // null expiry date
        assertThrows(NullPointerException.class, () -> Policy.isValidExpiryDate(null));

        // invalid expiry date
        assertFalse(Policy.isValidExpiryDate("")); // empty string
        assertFalse(Policy.isValidExpiryDate(" ")); // spaces only
        assertFalse(Policy.isValidExpiryDate("2020-10-10"));
        assertFalse(Policy.isValidExpiryDate("10/10/2020"));

        // valid addresses
        assertTrue(Policy.isValidExpiryDate("01-01-2040"));
        assertTrue(Policy.isValidExpiryDate("31-12-2028"));
    }
    @Test
    public void isValidPremium() {
        // null address
        assertThrows(NullPointerException.class, () -> Policy.isValidPremium(null));

        // invalid addresses
        assertFalse(Policy.isValidPremium("")); // empty string
        assertFalse(Policy.isValidPremium(" ")); // spaces only
        assertFalse(Policy.isValidPremium("-1"));
        assertFalse(Policy.isValidPremium("invalid"));

        // valid addresses
        assertTrue(Policy.isValidPremium("1.0"));
        assertTrue(Policy.isValidPremium("1"));
        assertTrue(Policy.isValidPremium("1.1")); // uppercase
    }

    @Test
    public void equals() {
        Policy policy = new Policy("Hello", LocalDate.of(2023, 01, 01), 100.0);

        // same object -> returns true
        assertTrue(policy.equals(policy));

        // same values -> returns true
        Policy policyCopy = new Policy(policy.value, policy.expiryDate, policy.premium);
        assertTrue(policy.equals(policyCopy));

        // different types -> returns false
        assertFalse(policy.equals(1));

        // null -> returns false
        assertFalse(policy.equals(null));

        // different policy -> returns false
        Policy differentPolicy = new Policy("Bye");
        assertFalse(policy.equals(differentPolicy));
    }
}
