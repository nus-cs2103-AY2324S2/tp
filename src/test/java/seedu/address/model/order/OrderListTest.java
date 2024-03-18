package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.LILIES;
import static seedu.address.testutil.TypicalOrders.ROSES;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.exceptions.OrderNotFoundException;


class OrderListTest {

    private final OrderList orderList = new OrderList();

    @Test
    public void contains_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.contains(null));
    }

    @Test
    public void contains_orderNotInList_returnsFalse() {
        assertFalse(orderList.contains(ROSES));
    }

    @Test
    public void contains_orderInList_returnsTrue() {
        orderList.add(ROSES);
        assertTrue(orderList.contains(ROSES));
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.add(null));
    }

    @Test
    public void setOrder_nullTargetOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.setOrder(null, ROSES));
    }

    @Test
    public void setOrder_nullEditedOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.setOrder(ROSES, null));
    }

    @Test
    public void setOrder_targetOrderNotInList_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> orderList.setOrder(ROSES, ROSES));
    }

    @Test
    public void setOrder_editedOrderIsSameOrder_success() {
        orderList.add(ROSES);
        orderList.setOrder(ROSES, ROSES);
        OrderList expectedOrderList = new OrderList();
        expectedOrderList.add(ROSES);
        assertEquals(expectedOrderList, orderList);
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> orderList.remove(ROSES));
    }

    @Test
    public void remove_existingOrder_removesOrder() {
        orderList.add(ROSES);
        orderList.remove(ROSES);
        OrderList expectedOrderList = new OrderList();
        assertEquals(expectedOrderList, orderList);
    }

    @Test
    public void setOrders_nullOrderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.setOrders((OrderList) null));
    }

    @Test
    public void setOrders_orderList_replacesOwnListWithProvidedOrderList() {
        orderList.add(ROSES);
        OrderList expectedOrderList = new OrderList();
        expectedOrderList.add(LILIES);
        orderList.setOrders(expectedOrderList);
        assertEquals(expectedOrderList, orderList);
    }

    @Test
    public void setOrders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.setOrders((List<Order>) null));
    }

    @Test
    public void setOrders_list_replacesOwnListWithProvidedList() {
        orderList.add(ROSES);
        List<Order> orderList = Collections.singletonList(LILIES);
        this.orderList.setOrders(orderList);
        OrderList expectedOrderList = new OrderList();
        expectedOrderList.add(LILIES);
        assertEquals(expectedOrderList, this.orderList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> orderList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(orderList.asUnmodifiableObservableList().toString(), orderList.toString());
    }

    @Test
    public void testEquals() {
        OrderList orderList = new OrderList();
        orderList.add(ROSES);
        assertEquals(orderList, orderList);

        OrderList orderList2 = new OrderList();
        orderList2.add(ROSES);
        assertEquals(orderList, orderList2);

        assertNotEquals(orderList, null);

        assertNotEquals(orderList, 1);
    }
}
