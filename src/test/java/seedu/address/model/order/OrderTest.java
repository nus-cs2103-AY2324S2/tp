package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalOrders.COOKIES_ONLY;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_AND_COOKIES;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_ONLY;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

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

        // different type -> returns false
        assertFalse(CUPCAKES_ONLY.equals(5));

        // different order -> returns false;
        assertFalse(CUPCAKES_ONLY.equals(COOKIES_ONLY));

        // different order id -> returns false;
        Order editedCupcakes = new OrderBuilder(CUPCAKES_ONLY).withIndex(3).build();
        assertFalse(CUPCAKES_ONLY.equals(editedCupcakes));

        // different person -> returns false;
        Order aliceCupcakes = new OrderBuilder(CUPCAKES_ONLY).withIndex(1).withPerson(ALICE).build();
        Order bobCupcakes = new OrderBuilder(CUPCAKES_ONLY).withIndex(2).withPerson(BOB).build();
        assertFalse(aliceCupcakes.equals(bobCupcakes));
    }

    @Test
    public void toStringMethod() {
        // single product order
        String expectedCupcakes = "Cupcake,3\n";
        assertEquals(expectedCupcakes, CUPCAKES_ONLY.toString());

        // multiple products order
        String expectedCupcakesAndCookies = "Cookie,2\nCupcake,1\n";
        assertEquals(expectedCupcakesAndCookies, CUPCAKES_AND_COOKIES.toString());
    }
}
