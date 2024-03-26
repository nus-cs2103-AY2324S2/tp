package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        String invalidQuantity = "";
        assertThrows(IllegalArgumentException.class, () -> new Quantity(Integer.parseInt(invalidQuantity)));
    }

    @Test
    public void isValidQuantity() {
        // null quantity number
        assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(null));

        // invalid phone numbers
        assertFalse(Quantity.isValidQuantity("")); // empty string
        assertFalse(Quantity.isValidQuantity(" ")); // spaces only
        assertFalse(Quantity.isValidQuantity("quantity")); // non-numeric
        assertFalse(Quantity.isValidQuantity("93be23")); // alphabets within digits
        assertFalse(Quantity.isValidQuantity("12 15")); // spaces within digits
        assertFalse(Quantity.isValidQuantity("12.15")); // dots within digits
        assertFalse(Quantity.isValidQuantity("-8392")); // negative numbers

        // valid quantity numbers
        assertTrue(Quantity.isValidQuantity("0"));
        assertTrue(Quantity.isValidQuantity("93"));
        assertTrue(Quantity.isValidQuantity("0093")); // beginning zeros
        assertTrue(Quantity.isValidQuantity("124293842033123")); // long numbers
    }

    @Test
    public void equals() {
        Quantity quantity = new Quantity(10);

        // same values -> returns true
        assertTrue(quantity.equals(new Quantity(10)));

        // same object -> returns true
        assertTrue(quantity.equals(quantity));

        // null -> returns false
        assertFalse(quantity.equals(null));

        // different types -> returns false
        assertFalse(quantity.equals(5.0f));

        // same type, different value -> returns false
        assertFalse(quantity.equals(new Quantity(5)));
    }
}
