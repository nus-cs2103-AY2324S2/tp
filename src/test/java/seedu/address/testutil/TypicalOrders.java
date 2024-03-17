package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_COOKIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_CUPCAKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_TWO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.order.Order;
import seedu.address.model.order.OrderList;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {

    public static final Order CUPCAKES_ONLY = new OrderBuilder().withIndex(1)
            .withProductQuantity(VALID_PRODUCT_CUPCAKE, VALID_QUANTITY_THREE).build();

    public static final Order COOKIES_ONLY = new OrderBuilder().withIndex(2)
            .withProductQuantity(VALID_PRODUCT_COOKIE, VALID_QUANTITY_TWO).build();

    public static final Order CUPCAKES_AND_COOKIES = new OrderBuilder().withIndex(3)
            .withProductQuantity(VALID_PRODUCT_CUPCAKE, VALID_QUANTITY_ONE)
            .withProductQuantity(VALID_PRODUCT_COOKIE, VALID_QUANTITY_TWO).build();

    public static OrderList getTypicalOrderList() {
        OrderList ol = new OrderList();
        for (Order order : getTypicalOrders()) {
            ol.addOrder(order, order.getCustomer());
        }
        return ol;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(CUPCAKES_ONLY, COOKIES_ONLY, CUPCAKES_AND_COOKIES));
    }
}
