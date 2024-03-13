package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderTest {
    private static final String VALID_DATE = "2020-01-01";
    private static final String VALID_DESCRIPTION = "100 chicken wings";

    private static final String INVALID_DATE = "2020-99-99";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Order(null, null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Order(new Date(INVALID_DATE), VALID_DESCRIPTION));
    }

    @Test
    public void constructor_order_success() {
        Order order = new Order(new Date(VALID_DATE), VALID_DESCRIPTION);
        assertEquals(VALID_DATE, order.getDate().toString());
        assertEquals(VALID_DESCRIPTION, order.getDescription());
        assertEquals("Pending", order.getStatus());

        Order orderWithStatus = new Order(new Date(VALID_DATE), VALID_DESCRIPTION, "Delivered");
        assertEquals(VALID_DATE, orderWithStatus.getDate().toString());
        assertEquals(VALID_DESCRIPTION, orderWithStatus.getDescription());
        assertEquals("Delivered", orderWithStatus.getStatus());
    }

    @Test
    public void equals() {
        Order order = new Order(new Date(VALID_DATE), VALID_DESCRIPTION);

        // same object -> returns true
        assertTrue(order.equals(order));

        // same values -> returns true
        Order orderCopy = new Order(order.getDate(), order.getDescription(), order.getStatus());
        assertTrue(order.equals(orderCopy));

        // different types -> returns false
        assertFalse(order.equals(1));

        // null -> returns false
        assertFalse(order.equals(null));

        // different Order -> returns false
        Order differentOrder = new Order(order.getDate(), "200 chicken wings");
        assertFalse(order.equals(differentOrder));
    }

    @Test
    public void hashcode() {
        Order order = new Order(new Date(VALID_DATE), VALID_DESCRIPTION);

        // same order -> returns same hashcode
        assertEquals(order.hashCode(), new Order(new Date(VALID_DATE), VALID_DESCRIPTION).hashCode());

        // different date -> returns different hashcode
        assertNotEquals(order.hashCode(), new Order(new Date("2022-01-01"), VALID_DESCRIPTION).hashCode());

        // different description -> returns different hashcode
        assertNotEquals(order.hashCode(), new Order(new Date(VALID_DATE), "20 chicken wings").hashCode());
    }
}
