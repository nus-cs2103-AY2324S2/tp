package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Represents the date an order was placed.
 */
public class OrderDate {
    private static final String MESSAGE_CONSTRAINTS =
            "Order date should be in the format of DD-MM-YYYY HH:MM, e.g. 01-01-2024 23:59";

    public final LocalDateTime orderDate;

    /**
     * Constructs a {@code OrderDate}.
     *
     * @param orderDate A valid order date.
     */
    public OrderDate(String orderDate) {
        requireNonNull(orderDate);
        checkArgument(isValidOrderDate(orderDate), MESSAGE_CONSTRAINTS);
        this.orderDate = DateTimeUtil.parseDateTime(orderDate);
    }

    private boolean isValidOrderDate(String orderDate) {
        return DateTimeUtil.isValidDate(orderDate);
    }

    @Override
    public String toString() {
        return orderDate.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OrderDate)) {
            return false;
        }

        OrderDate otherOrderDate = (OrderDate) other;
        return orderDate.equals(otherOrderDate.orderDate);
    }

    @Override
    public int hashCode() {
        return orderDate.hashCode();
    }
}
