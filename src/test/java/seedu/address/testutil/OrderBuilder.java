package seedu.address.testutil;


import seedu.address.model.order.Deadline;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.Price;
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
    public static final String DEFAULT_PRICE = "100";
    public static final String DEFAULT_REMARK = "No remark";
    public static final String DEFAULT_STATUS = "PENDING";

    private OrderId orderId;
    private OrderDate orderDate;
    private Deadline deadline;
    private Price price;
    private Remark remark;
    private Status status;


    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        orderId = new OrderId(DEFAULT_ORDER_ID);
        orderDate = new OrderDate(DEFAULT_ORDER_DATE);
        deadline = new Deadline(DEFAULT_DEADLINE);
        price = new Price(DEFAULT_PRICE);
        remark = new Remark(DEFAULT_REMARK);
        status = new Status(DEFAULT_STATUS);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        orderId = orderToCopy.getOrderId();
        orderDate = orderToCopy.getOrderDate();
        deadline = orderToCopy.getDeadline();
        price = orderToCopy.getPrice();
        remark = orderToCopy.getRemark();
        status = orderToCopy.getStatus();
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
     * Sets the {@code Price} of the {@code Order} that we are building.
     */
    public OrderBuilder withPrice(String price) {
        this.price = new Price(price);
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


    public Order build() {
        return new Order(orderId, orderDate, deadline, price, remark, status);
    }

}
