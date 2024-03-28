package seedu.address.testutil;

import seedu.address.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {
    public static final Order ROSES = new OrderBuilder()
            .withOrderId("69c25c8d-9e34-4d9d-8bad-e378f203ae73")
            .withOrderDate("01-03-2024 23:59")
            .withDeadline("01-04-2024 23:59")
            .withPrice("10")
            .withRemark("No remark")
            .withStatus("CANCELED")
            .build();
    public static final Order LILIES = new OrderBuilder()
            .withOrderId("69c25c8d-9e34-4d9d-8bad-e378f203ae74")
            .withOrderDate("23-03-2024 11:59")
            .withDeadline("05-12-2024 20:57")
            .withPrice("10")
            .withRemark("Important")
            .withStatus("PENDING")
            .build();

    private TypicalOrders() {
    } // prevents instantiation
}
