package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Products;

public class JsonAdaptedProductsTest {

    @Test
    public void constructor_noArgs_success() {
        JsonAdaptedProducts adaptedProducts = new JsonAdaptedProducts();
        assertEquals(new ArrayList<>(), adaptedProducts.getProducts());
    }

    @Test
    public void constructor_withProducts_success() {
        List<String> products = Arrays.asList("Product 1", "Product 2", "Product 3");
        JsonAdaptedProducts adaptedProducts = new JsonAdaptedProducts(products);
        assertEquals(products, adaptedProducts.getProducts());
    }

    @Test
    public void toModelType_validProducts_success() {
        List<String> products = Arrays.asList("Product 1", "Product 2", "Product 3");
        JsonAdaptedProducts adaptedProducts = new JsonAdaptedProducts(products);
        try {
            Products modelProducts = adaptedProducts.toModelType();
            assertEquals(products, modelProducts.getProducts());
        } catch (IllegalValueException e) {
            // Unexpected exception
            assert false;
        }
    }

    @Test
    public void toModelType_invalidProducts_throwsIllegalValueException() {
        List<String> products = Arrays.asList("Product 1", "Invalid Product!!", "Product 3");
        JsonAdaptedProducts adaptedProducts = new JsonAdaptedProducts(products);
        assertThrows(IllegalValueException.class, adaptedProducts::toModelType);
    }
}
