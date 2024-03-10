package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String invalidPrice = "20/hr";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null Price
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid Price
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // spaces only
        assertFalse(Price.isValidPrice("50"));
        assertFalse(Price.isValidPrice("50/bag"));
        assertFalse(Price.isValidPrice("$50"));

        // valid Price
        assertTrue(Price.isValidPrice("$500/bag")); // exactly 3 numbers
        assertTrue(Price.isValidPrice("$50/piece"));
    }

    @Test
    public void equals() {
        Price price = new Price("$50/bag");

        // same values -> returns true
        assertTrue(price.equals(new Price("$50/bag")));

        // same object -> returns true
        assertTrue(price.equals(price));

        // null -> returns false
        assertFalse(price.equals(null));

        // different types -> returns false
        assertFalse(price.equals("$50/bag"));

        // different values -> returns false
        assertFalse(price.equals(new Price("$50/piece")));
    }
}
