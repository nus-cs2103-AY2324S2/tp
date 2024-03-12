package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Product(null));
    }

    @Test
    public void constructor_invalidProduct_throwsIllegalArgumentException() {
        String invalidProduct = "";
        assertThrows(IllegalArgumentException.class, () -> new Product(invalidProduct));
    }

    @Test
    public void isValidProduct() {
        // null Product
        assertThrows(NullPointerException.class, () -> Product.isValidProduct(null));

        // invalid Product
        assertFalse(Product.isValidProduct("")); // empty string
        assertFalse(Product.isValidProduct(" ")); // spaces only

        // valid Product
        assertTrue(Product.isValidProduct("pooch food"));
        assertTrue(Product.isValidProduct("medicine"));
        assertTrue(Product.isValidProduct("pooch-toy"));
    }

    @Test
    public void equals() {
        Product product = new Product("Valid Product");

        // same values -> returns true
        assertTrue(product.equals(new Product("Valid Product")));

        // same object -> returns true
        assertTrue(product.equals(product));

        // null -> returns false
        assertFalse(product.equals(null));

        // different types -> returns false
        assertFalse(product.equals("Valid Product"));

        // different values -> returns false
        assertFalse(product.equals(new Product("Other Valid Product")));
    }
}
