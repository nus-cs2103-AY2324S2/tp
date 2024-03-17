package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_AND_COOKIES;
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
        Order editedCupcakesAlice = new OrderBuilder(CUPCAKES_ONLY).withIndex(1).withPerson(ALICE)
                .build();
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
        orderList.addOrder(CUPCAKES_ONLY, ALICE);
        orderList.editOrder(1, CUPCAKES_ONLY);
        OrderList expectedOrderList = new OrderList();
        expectedOrderList.addOrder(CUPCAKES_ONLY, ALICE);
        assertEquals(expectedOrderList, orderList);
    }

    @Test
    public void editOrder_editedOrderHasSameCustomer_success() {
        orderList.addOrder(CUPCAKES_ONLY, ALICE);
        Order editedCupcakesAlice = new OrderBuilder(CUPCAKES_AND_COOKIES).withIndex(1).withPerson(ALICE)
                .build();
        orderList.editOrder(1, editedCupcakesAlice);
        OrderList expectedOrderList = new OrderList();
        expectedOrderList.addOrder(CUPCAKES_AND_COOKIES, ALICE);
        assertEquals(expectedOrderList, orderList);
    }

    @Test
    public void delete_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.deleteOrder(0));
    }

    @Test
    public void delete_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> orderList.deleteOrder(1));
    }

    @Test
    public void delete_existingOrder_deletesOrder() {
        //orderList.addOrder(CUPCAKES_ONLY, ALICE);
        //orderList.deleteOrder(1);
        //OrderList expectedOrderList = new OrderList();
        //assertEquals(expectedOrderList, orderList);
    }

    @Test
    public void size_orderList_returnsNumberOfOrders() {
        orderList.addOrder(CUPCAKES_ONLY, ALICE);
        assertEquals(1, orderList.size());
    }

    @Test
    public void getOrder_existingOrder_returnsOrder() {
        orderList.addOrder(CUPCAKES_ONLY, ALICE);
        Order expectedOrder = CUPCAKES_ONLY;
        assertEquals(expectedOrder, orderList.getOrder(1));
    }

    @Test
    public void getOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.getOrder(0));
    }

    @Test
    public void getOrder_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> orderList.getOrder(1));
    }
}
