package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalOrders.LILIES;
import static seedu.address.testutil.TypicalOrders.ROSES;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OrderBuilder;

class OrderTest {
    @Test
    public void equals() {
        Order rosesCopy = new OrderBuilder(ROSES).build();
        assertEquals(ROSES, rosesCopy);

        // same object -> returns true
        assertEquals(ROSES, ROSES);

        // null -> returns false
        assertNotEquals(ROSES, null);

        // different type -> returns false
        assertNotEquals(ROSES, 5);

        // different order -> returns false
        assertNotEquals(ROSES, LILIES);

        // EditedRoses
        Order editedRoses;

        // different price -> returns false
        editedRoses = new OrderBuilder(ROSES).withPrice("200").build();
        assertNotEquals(ROSES, editedRoses);

        // different deadline -> returns false
        editedRoses = new OrderBuilder(ROSES)
                .withDeadline("11-05-2024 21:51")
                .build();
        assertNotEquals(ROSES, editedRoses);

        // different order date -> returns false
        editedRoses = new OrderBuilder(ROSES)
                .withOrderDate("11-05-2024 21:51")
                .build();
        assertNotEquals(ROSES, editedRoses);

        // different remark -> returns false
        editedRoses = new OrderBuilder(ROSES)
                .withRemark("Different remark")
                .build();
        assertNotEquals(ROSES, editedRoses);

        // different status -> returns false
        editedRoses = new OrderBuilder(ROSES)
                .withStatus("PENDING")
                .build();
        assertNotEquals(ROSES, editedRoses);


        // different orderId -> returns false
        editedRoses = new OrderBuilder(ROSES)
                .withOrderId("434d72c4-f045-448c-84a7-6d70704e9730")
                .build();
        assertNotEquals(ROSES, editedRoses);

    }

    @Test
    public void toStringMethod() {
        String expected = Order.class.getCanonicalName()
                + "{orderId=" + ROSES.getOrderId()
                + ", orderDate=" + ROSES.getOrderDate()
                + ", deadline=" + ROSES.getDeadline()
                + ", price=" + ROSES.getPrice()
                + ", remark=" + ROSES.getRemark()
                + ", status=" + ROSES.getStatus()
                + "}";
        assertEquals(expected, ROSES.toString());
    }
}


