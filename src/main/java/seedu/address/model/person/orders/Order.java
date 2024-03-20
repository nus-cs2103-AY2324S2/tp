package seedu.address.model.person.orders;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

/**
 * Represents an order made by a Person
 */
public class Order implements Cloneable {

    public static final String MESSAGE_CONSTRAINTS = "Orders items can take any values, and it should not be blank";

    public static final String MESSAGE_INVALID_DATETIME = "Order DateTime is invalid";

    /*
     * The first character of the order must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String items;
    public final LocalDateTime orderDateTime;

    /**
     * Constructs an {@code Order}.
     *
     * @param items A valid order
     */
    public Order(String items) {
        requireNonNull(items);
        checkArgument(isValidItems(items), MESSAGE_CONSTRAINTS);
        this.items = items;
        this.orderDateTime = LocalDateTime.now();
    }

    /**
     * Constructs an {@code Order} with a specific {@code LocalDateTime} ordered.
     *
     * @param items A valid order
     * @param orderDateTime The time of this order
     */
    public Order(String items, LocalDateTime orderDateTime) {
        requireNonNull(items);
        requireNonNull(orderDateTime);
        checkArgument(isValidItems(items), MESSAGE_CONSTRAINTS);
        this.items = items;
        this.orderDateTime = orderDateTime;
    }

    /**
     * Returns true if a given string is a valid item.
     */
    public static boolean isValidItems(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return orderDateTime.toString() + " " + items;
    }

    @Override
    public Order clone() {
        return new Order(items, orderDateTime);
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
        return items.equals(otherOrder.items) && orderDateTime.equals(otherOrder.orderDateTime);
    }
}

