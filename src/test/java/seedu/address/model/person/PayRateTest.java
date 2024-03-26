package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PayRateTest {

    @Test
    public void isValidPayRate() {

        // invalid pay rates
        assertFalse(PayRate.isValidPayRate("")); // empty string
        assertFalse(PayRate.isValidPayRate(" ")); // spaces only
        assertFalse(PayRate.isValidPayRate("abc")); // non-numeric string
        assertFalse(PayRate.isValidPayRate("-5.0")); // negative pay rate

        // valid pay rates
        assertTrue(PayRate.isValidPayRate("5.0"));
        assertTrue(PayRate.isValidPayRate("10.5"));
        assertTrue(PayRate.isValidPayRate("100.75"));
    }


    @Test
    public void equals() {
        PayRate payRate = new PayRate(15);

        // same values -> returns true
        assertTrue(payRate.equals(new PayRate(15)));

        // same object -> returns true
        assertTrue(payRate.equals(payRate));

        // different types -> returns false
        assertFalse(payRate.equals("abc"));

        // different values -> returns false
        assertFalse(payRate.equals(new PayRate(14)));
    }
}
