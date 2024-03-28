package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class OrderDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrderDate(null));
    }

    @Test
    public void constructor_invalidOrderDate_throwsIllegalArgumentException() {
        String invalidOrderDate = "";
        assertThrows(IllegalArgumentException.class, () -> new OrderDate(invalidOrderDate));
    }

    @Test
    public void isValidOrderDate() {
        // null OrderDate
        assertThrows(NullPointerException.class, () -> OrderDate.isValidOrderDate(null));

        // invalid OrderDates
        assertFalse(OrderDate.isValidOrderDate("")); // empty string
        assertFalse(OrderDate.isValidOrderDate(" ")); // spaces only
        assertFalse(OrderDate.isValidOrderDate("11-15-2024 21:51")); // invalid date format
        assertFalse(OrderDate.isValidOrderDate("41-15-2024 21:51")); // invalid date
        assertFalse(OrderDate.isValidOrderDate("15-41-2024 21:51")); // invalid date

        // valid OrderDates
        assertTrue(OrderDate.isValidOrderDate("15-12-3024 21:51")); // long OrderDate
        assertTrue(OrderDate.isValidOrderDate("11-05-2024 21:51"));
        assertTrue(OrderDate.isValidOrderDate("01-05-2024 01:01"));
    }

    @Test
    public void equals() {
        OrderDate orderDate = new OrderDate("11-05-2024 21:51");

        // same values -> returns true
        assertEquals(orderDate, new OrderDate("11-05-2024 21:51"));

        // same object -> returns true
        assertEquals(orderDate, orderDate);

        // null -> returns false
        assertNotEquals(null, orderDate);

        // different types -> returns false
        assertNotEquals(orderDate, 0.0);

        // different values -> returns false
        assertNotEquals(orderDate, new OrderDate("11-05-2024 21:52"));
    }

}
