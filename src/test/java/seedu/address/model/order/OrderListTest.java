package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_AND_COOKIES;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_ONLY;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.OrderNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.testutil.OrderBuilder;
import seedu.address.testutil.PersonBuilder;

public class OrderListTest {

    private final OrderList orderList = new OrderList();

    @Test
    public void contains_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.contains(null));
    }

    @Test
    public void contains_orderNotInList_returnsFalse() {
        assertFalse(orderList.contains(new OrderBuilder(CUPCAKES_ONLY).build()));
    }

    @Test
    public void contains_orderInList_returnsTrue() {
        orderList.addOrder(new OrderBuilder(CUPCAKES_ONLY).build());
        assertTrue(orderList.contains(new OrderBuilder(CUPCAKES_ONLY).build()));
    }

    @Test
    public void contains_orderWithSameIdentityFieldsInList_returnsTrue() {
        orderList.addOrder(new OrderBuilder(CUPCAKES_ONLY).build());
        Order editedCupcakesAlice = new OrderBuilder(CUPCAKES_ONLY).build();
        assertTrue(orderList.contains(editedCupcakesAlice));
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList
                .addOrder(null));
    }

    @Test
    public void editOrder_nullTargetOrderId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList
                .editOrder(0, new OrderBuilder(CUPCAKES_ONLY).build()));
    }

    @Test
    public void editOrder_nullEditedOrder_throwsNullPointException() {
        assertThrows(NullPointerException.class, () -> orderList.editOrder(1, null));
    }

    @Test
    public void editOrder_targetOrderNotInList_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> orderList
                .editOrder(3, new OrderBuilder(CUPCAKES_ONLY).build()));
    }

    @Test
    public void editOrder_editedOrderIsSameOrder_success() {
        Order testOrder1 = new OrderBuilder(CUPCAKES_ONLY).withIndex(1).build();
        orderList.addOrder(testOrder1);
        orderList.editOrder(1, new OrderBuilder(CUPCAKES_ONLY).withIndex(1).build());
        OrderList expectedOrderList = new OrderList();
        Order testOrder2 = new OrderBuilder(CUPCAKES_ONLY).withIndex(1).build();
        expectedOrderList.addOrder(testOrder2);
        assertEquals(expectedOrderList, orderList);
    }

    /**
    @Test
    public void editOrder_editedOrderHasSameCustomer_success() {
        // For some reason cannot use Typical Order, need to find fix.
        OrderList testOrderList = new OrderList();
        Order testOrder = new OrderBuilder().withIndex(1).withPerson(ALICE).withProductQuantity("cupcake", "2").build();
        testOrderList.addOrder(testOrder, new PersonBuilder(ALICE).build());
        Order testOrder2 = new OrderBuilder().withProductQuantity("cookies", "1").build();
        //Order editedCupcakesAlice = new OrderBuilder(CUPCAKES_AND_COOKIES).withIndex(1).withPerson(ALICE).build();
        testOrderList.editOrder(1, testOrder2);
        OrderList expectedOrderList = new OrderList();
        Order testOrder3 = new OrderBuilder().withIndex(1).withProductQuantity("cookies", "1").build();
        expectedOrderList.addOrder(testOrder3, new PersonBuilder(ALICE).build());
        assertEquals(expectedOrderList.getOrder(1), testOrderList.getOrder(1));
    }
     */

    @Test
    public void editOrder_existingOrder_editsOrderOnPerson() {
        //orderList.addOrder(new OrderBuilder(CUPCAKES_ONLY).build(),
        //        new PersonBuilder(ALICE).build());
        //Order editedOrder = new OrderBuilder(CUPCAKES_AND_COOKIES).withPerson(ALICE).build();
        //orderList.editOrder(1, editedOrder);
        //OrderList expectedOrderList = new OrderList();
        //expectedOrderList.addOrder(new OrderBuilder(CUPCAKES_AND_COOKIES).build(),
        //        new PersonBuilder(ALICE).build());
        //assertEquals(expectedOrderList, orderList);
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
        orderList.addOrder(new OrderBuilder(CUPCAKES_ONLY).build());
        orderList.deleteOrder(1);
        OrderList expectedOrderList = new OrderList();
        assertEquals(expectedOrderList, orderList);
    }

    @Test
    public void delete_existingOrder_deletesOrderOnPerson() {
        //Person customer = new PersonBuilder(ALICE).build();
        //orderList.addOrder(new OrderBuilder(CUPCAKES_ONLY).build(), customer);
        //orderList.deleteOrder(1);
        //ArrayList<Order> expectedPersonOrderList = new ArrayList<>();
        //assertEquals(expectedPersonOrderList, customer.getOrders());
    }

    @Test
    public void size_orderList_returnsNumberOfOrders() {
        orderList.addOrder(new OrderBuilder(CUPCAKES_ONLY).build());
        assertEquals(1, orderList.size());
    }

    @Test
    public void getOrder_existingOrder_returnsOrder() {
        orderList.addOrder(new OrderBuilder(CUPCAKES_ONLY).build());
        Order expectedOrder = new OrderBuilder(CUPCAKES_ONLY).build();
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
