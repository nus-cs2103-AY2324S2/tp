package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class AmountTest {

    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    void constructor_invalidAmountEmpty_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    void constructor_invalidAmountNegative_throwsIllegalArgumentException() {
        String invalidAmount = "-1";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    void constructor_invalidAmountZero_throwsIllegalArgumentException() {
        String invalidAmount = "0";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    void isValidAmount() {

        assertThrows(NumberFormatException.class, () -> Amount.isValidAmount(null));
        assertThrows(NumberFormatException.class, () -> Amount.isValidAmount(""));

        assertFalse(Amount.isValidAmount("-1"));

        assertThrows(NumberFormatException.class, () -> Amount.isValidAmount(" "));
        assertFalse(Amount.isValidAmount("0"));
        assertThrows(NumberFormatException.class, () -> Amount.isValidAmount("1.5"));
        assertThrows(NumberFormatException.class, () -> Amount.isValidAmount("1.0"));

        assertTrue(Amount.isValidAmount("1"));
        assertTrue(Amount.isValidAmount("100"));
    }

    @Test
    void testEquals() {
        Amount amount = new Amount("1");

        // same values -> returns true
        assertEquals(amount, new Amount("1"));

        // same object -> returns true
        assertEquals(amount, amount);

        // null -> returns false
        assertNotEquals(null, amount);

        // different types -> returns false
        assertNotEquals(amount, 0.0);

        // different values -> returns false
        assertNotEquals(amount, new Amount("2"));
    }

}
