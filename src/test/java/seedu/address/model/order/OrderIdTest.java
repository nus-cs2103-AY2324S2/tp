package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.OrderBuilder.DEFAULT_ORDER_ID;

import org.junit.jupiter.api.Test;

class OrderIdTest {
    @Test
    void constructor_invalidOrderId_throwsIllegalArgumentException() {
        String invalidOrderId = "";
        assertThrows(IllegalArgumentException.class, () -> new OrderId(invalidOrderId));
    }

    @Test
    void testEquals() {
        OrderId orderId = new OrderId();

        // different object -> negligible chance to be equals
        assertNotEquals(orderId, new OrderId());

        // same values -> returns true
        OrderId fixedOrderId1 = new OrderId(DEFAULT_ORDER_ID);
        OrderId fixedOrderId2 = new OrderId(DEFAULT_ORDER_ID);

        assertEquals(fixedOrderId1, fixedOrderId2);

        // same object -> returns true
        assertEquals(orderId, orderId);

        // same object representation -> returns true
        assertEquals(orderId, orderId.toString());

        // null -> returns false
        assertNotEquals(orderId, null);

    }


}
