package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Order.
 */
public class Order {
    private final OrderId orderId;
    private final OrderDate orderDate;
    private final Deadline deadline;
    private final Price price;
    private final Remark remark;
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Order(OrderId orderId, OrderDate orderDate, Deadline deadline,
                 Price price, Remark remark, Status status
    ) {
        requireAllNonNull(orderId, orderDate, deadline, price, remark, status);
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deadline = deadline;
        this.price = price;
        this.remark = remark;
        this.status = status;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public OrderDate getOrderDate() {
        return orderDate;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Price getPrice() {
        return price;
    }

    public Remark getRemark() {
        return remark;
    }

    public Status getStatus() {
        return status;
    }

    public boolean checkId(OrderId orderId) {
        return this.getOrderId().equals(orderId);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return otherOrder.getOrderId().equals(getOrderId())
                && otherOrder.getOrderDate().equals(getOrderDate())
                && otherOrder.getDeadline().equals(getDeadline())
                && otherOrder.getPrice().equals(getPrice())
                && otherOrder.getRemark().equals(getRemark())
                && otherOrder.getStatus().equals(getStatus());

    }


    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, deadline, price, remark, status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("orderId", orderId)
                .add("orderDate", orderDate)
                .add("deadline", deadline)
                .add("price", price)
                .add("remark", remark)
                .add("status", status)
                .toString();
    }

}
