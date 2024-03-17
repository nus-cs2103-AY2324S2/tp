package seedu.address.testutil;


import seedu.address.model.order.Amount;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.Discount;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.Remark;
import seedu.address.model.order.Status;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {

    // Solution below adapted from https://stackoverflow.com/a/29059595
    public static final String DEFAULT_ORDER_ID = "58b76419-f9ff-4aa1-8e70-604993fc1a20";
    public static final String DEFAULT_ORDER_DATE = "10-10-2024 00:00";
    public static final String DEFAULT_DEADLINE = "11-10-2024 23:59";
    public static final String DEFAULT_AMOUNT = "100";
    public static final String DEFAULT_REMARK = "No remark";
    public static final String DEFAULT_STATUS = "PENDING";
    public static final String DEFAULT_DISCOUNT = "0";

    private OrderId orderId;
    private OrderDate orderDate;
    private Deadline deadline;
    private Amount amount;
    private Remark remark;
    private Status status;
    private Discount discount;


    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        orderId = new OrderId(DEFAULT_ORDER_ID);
        orderDate = new OrderDate(DEFAULT_ORDER_DATE);
        deadline = new Deadline(DEFAULT_DEADLINE);
        amount = new Amount(DEFAULT_AMOUNT);
        remark = new Remark(DEFAULT_REMARK);
        status = new Status(DEFAULT_STATUS);
        discount = new Discount(DEFAULT_DISCOUNT);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        orderId = orderToCopy.getOrderId();
        orderDate = orderToCopy.getOrderDate();
        deadline = orderToCopy.getDeadline();
        amount = orderToCopy.getAmount();
        remark = orderToCopy.getRemark();
        status = orderToCopy.getStatus();
        discount = orderToCopy.getDiscount();
    }

    /**
     * Sets the {@code OrderId} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderId(String orderId) {
        this.orderId = new OrderId(orderId);
        return this;
    }

    /**
     * Sets the {@code OrderDate} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderDate(String orderDate) {
        this.orderDate = new OrderDate(orderDate);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Order} that we are building.
     */
    public OrderBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Order} that we are building.
     */
    public OrderBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Order} that we are building.
     */
    public OrderBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Order} that we are building.
     */
    public OrderBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Discount} of the {@code Order} that we are building.
     */
    public OrderBuilder withDiscount(String discount) {
        this.discount = new Discount(discount);
        return this;
    }

    public Order build() {
        return new Order(orderId, orderDate, deadline, amount, remark, status, discount);
    }

}
