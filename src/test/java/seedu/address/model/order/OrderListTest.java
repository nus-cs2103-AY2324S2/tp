package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_ONLY;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.OrderNotFoundException;
import seedu.address.testutil.OrderBuilder;

public class OrderListTest {

    private final OrderList orderList = new OrderList();

    @Test
    public void contains_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.contains(null));
    }

    @Test
    public void contains_orderNotInList_returnsFalse() {
        assertFalse(orderList.contains(CUPCAKES_ONLY));
    }

    @Test
    public void contains_orderInList_returnsTrue() {
        orderList.addOrder(CUPCAKES_ONLY, ALICE);
        assertTrue(orderList.contains(CUPCAKES_ONLY));
    }

    @Test
    public void contains_orderWithSameIdentityFieldsInList_returnsTrue() {
        orderList.addOrder(CUPCAKES_ONLY, ALICE);
        Order editedCupcakesAlice = new OrderBuilder(CUPCAKES_ONLY).withIndex(1).withPerson(ALICE).build();
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.addOrder(null, ALICE));
    }

    @Test
    public void editOrder_nullTargetOrderId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.editOrder(0, CUPCAKES_ONLY));
    }

    @Test
    public void editOrder_nullEditedOrder_throwsNullPointException() {
        assertThrows(NullPointerException.class, () -> orderList.editOrder(1, null));
    }

    @Test
    public void editOrder_targetOrderNotInList_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> orderList.editOrder(3, CUPCAKES_ONLY));
    }

    @Test
    public void editOrder_editedOrderIsSameOrder_success() {
        //orderList.addOrder(CUPCAKES_ONLY, ALICE);
        //orderList.editOrder(1, CUPCAKES_ONLY);
        //OrderList expectedOrderList = new OrderList();
        //expectedOrderList.addOrder(CUPCAKES_ONLY, ALICE);
        //assertEquals(expectedOrderList, orderList);
    }
}
