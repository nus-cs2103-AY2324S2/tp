package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderList;


/**
 * Represents the order book of the address book.
 */
public class OrderBook implements ReadOnlyOrderBook {
    private final OrderList orders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        orders = new OrderList();
    }

    public OrderBook() {}

    /**
     * Creates an OrderBook using the orders in the {@code toBeCopied}.
     */
    public OrderBook(ReadOnlyOrderBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the order list with {@code orders}.
     * {@code orders} must not contain duplicate orders.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Resets the existing data of this {@code OrderBook} with {@code newData}.
     */
    public void resetData(ReadOnlyOrderBook newData) {
        requireNonNull(newData);

        orders.setOrders(newData.getOrderList());
    }

    /**
     * Returns true if an order with the same identity as {@code order} exists in the order book.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);

        return orders.contains(order);
    }

    /**
     * Adds an order to the order book.
     * The order must not already exist in the order book.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Replaces the given order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the order book.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the order book.
     */
    public void setOrder(Order target, Order editedOrder) {
        orders.setOrder(target, editedOrder);
    }

    /**
     * Removes {@code key} from this {@code OrderBook}.
     * {@code key} must exist in the order book.
     */
    public void removeOrder(Order key) {
        orders.remove(key);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("orders", orders)
                .toString();
    }

    /**
     * Returns an unmodifiable view of the order list.
     * This method will return a list containing all the orders in the order book.
     */
    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderBook)) {
            return false;
        }

        OrderBook otherOrderBook = (OrderBook) other;
        return orders.equals(otherOrderBook.orders);
    }

}
