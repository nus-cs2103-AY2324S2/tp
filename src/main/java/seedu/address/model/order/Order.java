package seedu.address.model.order;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Order in the address book.
 */
public class Order {
    private final Date arrivalDate;
    private final String remark;
    private final String status;

    /**
     * Constructs a {@code Order}.
     *
     * @param arrivalDate a valid date of the order.
     * @param remark a valid remark of the order.
     */
    public Order(Date arrivalDate, String remark) {
        requireNonNull(arrivalDate);
        requireNonNull(remark);

        this.arrivalDate = arrivalDate;
        this.remark = remark;
        this.status = "Pending";
    }

    /**
     * Constructs a {@code Order}.
     *
     * @param arrivalDate a valid date of the order.
     * @param remark a valid remark of the order.
     * @param status a valid status of the order.
     */
    public Order(Date arrivalDate, String remark, String status) {
        requireNonNull(arrivalDate);
        requireNonNull(remark);

        this.arrivalDate = arrivalDate;
        this.remark = remark;
        this.status = status;
    }

    /**
     * Copy constructor for creating a copy of an existing Order.
     * @param order The order to copy.
     */
    public Order(Order order) {
        this.arrivalDate = order.arrivalDate;
        this.remark = order.remark;
        this.status = order.status;
    }

    public Date getDate() {
        return arrivalDate;
    }

    public String getRemark() {
        return remark;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return arrivalDate.equals(otherOrder.arrivalDate) && remark.equals(otherOrder.remark)
                && status.equals(otherOrder.status);
    }

    @Override
    public int hashCode() {
        return arrivalDate.hashCode() + remark.hashCode() + status.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return String.format("[%s (by: %s, status: %s)]", remark, arrivalDate, status);
    }

}
