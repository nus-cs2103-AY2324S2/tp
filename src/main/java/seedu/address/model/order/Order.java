package seedu.address.model.order;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Order.
 */
public class Order {
    private final OrderId orderId;
    private final OrderDate orderDate;
    private final Deadline deadline;
    private final Amount amount;
    private final Remark remark;
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Order(OrderId orderId, OrderDate orderDate, Deadline deadline,
                 Amount amount, Remark remark, Status status
    ) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deadline = deadline;
        this.amount = amount;
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

    public Amount getAmount() {
        return amount;
    }

    public Remark getRemark() {
        return remark;
    }

    public Status getStatus() {
        return status;
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
                && otherOrder.getAmount().equals(getAmount())
                && otherOrder.getRemark().equals(getRemark())
                && otherOrder.getStatus().equals(getStatus());

    }


    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, deadline, amount, remark, status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("orderId", orderId)
                .add("orderDate", orderDate)
                .add("deadline", deadline)
                .add("amount", amount)
                .add("remark", remark)
                .add("status", status)
                .toString();
    }
}
