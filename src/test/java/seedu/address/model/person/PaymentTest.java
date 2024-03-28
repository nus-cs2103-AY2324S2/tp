package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PaymentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Payment(null));
    }

    @Test
    public void constructor_invalidPayment_throwsIllegalArgumentException() {
        String invalidPayment = "Unpaid";
        assertThrows(IllegalArgumentException.class, () -> new Payment(invalidPayment));
    }

    @Test
    public void isValidPayment() {
        // null payment
        assertThrows(NullPointerException.class, () -> Payment.isValidPayment(null));

        // invalid payment
        assertFalse(Payment.isValidPayment("Unpaid")); // Invalid status

        // valid payment
        assertTrue(Payment.isValidPayment("Paid"));
        assertTrue(Payment.isValidPayment("Not Paid"));
    }

    @Test
    public void equals() {
        Payment payment = new Payment("Paid");

        // same values -> returns true
        assertTrue(payment.equals(new Payment("Paid")));

        // same object -> returns true
        assertTrue(payment.equals(payment));

        // null -> returns false
        assertFalse(payment.equals(null));

        // different types -> returns false
        assertFalse(payment.equals(5.0f));

        // different values -> returns false
        assertFalse(payment.equals(new Payment("Not Paid")));
    }
}
