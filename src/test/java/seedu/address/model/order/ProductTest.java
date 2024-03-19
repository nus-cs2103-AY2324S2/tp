package seedu.address.model.order;

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
        // null product
        assertThrows(NullPointerException.class, () -> Product.isValidProduct(null));

        // invalid products
        assertFalse(Product.isValidProduct("")); // empty string
        assertFalse(Product.isValidProduct(" ")); // spaces only
        assertFalse(Product.isValidProduct(" Cupcake")); // first character space
        assertFalse(Product.isValidProduct("^")); // only non-alphanumeric characters
        assertFalse(Product.isValidProduct("Cupcake*")); // contain non-alphanumeric characters

        // valid name
        assertTrue(Product.isValidProduct("cupcake")); // alphabets only
        assertTrue(Product.isValidProduct("123")); // numbers only
        assertTrue(Product.isValidProduct("c00kies")); // alphanumeric characters
        assertTrue(Product.isValidProduct("Cupcake")); // with capital letters
        assertTrue(Product.isValidProduct("Cookies and Cupcakes with Ice Cream")); // long product names
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

        // different types -> returns false;
        assertFalse(product.equals(5.0f));

        // different values -> returns false
        assertFalse(product.equals(new Product("Other Valid Product")));
    }
}
