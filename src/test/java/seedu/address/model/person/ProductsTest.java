package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductsTest {

    private static final List<String> PRODUCT_LIST = Arrays.asList("Product1", "Product2", "Product3");
    private static final List<String> EMPTY_LIST = List.of();
    private static final String PRODUCT_STRING = "Product1 Product2 Product3";
    private static final String EMPTY_STRING = "";

    private Products products;

    @BeforeEach
    public void setUp() {
        products = new Products();
    }

    @Test
    public void constructor_emptyList_success() {
        assertEquals(EMPTY_LIST.size(), products.getProducts().size());
    }

    @Test
    public void constructor_validList_success() {
        products = new Products(PRODUCT_LIST);
        assertEquals(PRODUCT_LIST, products.getProducts());
    }

    @Test
    public void constructor_validString_success() {
        products = new Products(PRODUCT_STRING);
        assertEquals(PRODUCT_LIST, products.getProducts());
    }

    @Test
    public void isValidProducts_validList_success() {
        assertTrue(Products.isValidProducts(PRODUCT_LIST));
    }

    @Test
    public void isValidProducts_invalidList_failure() {
        List<String> productList = Arrays.asList("Product1", "Product2", "Product@3");
        assertFalse(Products.isValidProducts(productList));
    }

    @Test
    public void removeProduct_nonExistingProduct_success() {
        products = new Products(PRODUCT_LIST);
        products.removeProduct("Product4");
        assertEquals(PRODUCT_LIST, products.getProducts());
    }

    @Test
    public void isEmpty_emptyList_success() {
        assertTrue(products.isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_success() {
        products = new Products(PRODUCT_LIST);
        assertFalse(products.isEmpty());
    }

    @Test
    public void toString_emptyList_success() {
        assertEquals(EMPTY_STRING, products.toString());
    }

    @Test
    public void toString_nonEmptyList_success() {
        products = new Products(PRODUCT_LIST);
        assertEquals("Product1, Product2, Product3", products.toString());
    }

    @Test
    public void equals_sameObject_success() {
        assertEquals(products, products);
    }

    @Test
    public void equals_differentObject_success() {
        Products otherProducts = new Products(PRODUCT_LIST);
        assertNotEquals(products, otherProducts);
    }

    @Test
    public void equals_differentType_success() {
        assertNotEquals("Product1", products);
    }

    @Test
    public void equals_sameProducts_success() {
        products = new Products(PRODUCT_LIST);
        Products otherProducts = new Products(PRODUCT_LIST);
        assertEquals(products, otherProducts);
    }

    @Test
    public void hashCode_sameProducts_success() {
        products = new Products(PRODUCT_LIST);
        Products otherProducts = new Products(PRODUCT_LIST);
        assertEquals(products.hashCode(), otherProducts.hashCode());
    }
}
