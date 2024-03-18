package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalOrders.COOKIES_ONLY;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_ONLY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OrderBuilder;

public class OrderTest {
    @Test
    public void isSameOrder() {
        // same object -> returns true
        assertTrue(CUPCAKES_ONLY.isSameOrder(CUPCAKES_ONLY));

        // null -> returns false
        assertFalse(CUPCAKES_ONLY.isSameOrder(null));

        // different object -> returns false
        assertFalse(CUPCAKES_ONLY.isSameOrder(COOKIES_ONLY));
    }

    @Test
    public void equals() {
        // same values -> return true
        Order cupcakesCopy = new OrderBuilder(CUPCAKES_ONLY).build();
        assertTrue(CUPCAKES_ONLY.equals(cupcakesCopy));

        // same object -> returns true
        assertTrue(CUPCAKES_ONLY.equals(CUPCAKES_ONLY));

        // null -> returns false
        assertFalse(CUPCAKES_ONLY.equals(null));
    }
}
