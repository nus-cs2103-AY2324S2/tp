package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exceptions.OrderNotFoundException;
import seedu.address.model.person.Person;

/**
 * Represents the list of active orders in the addressbook.
 */
public class OrderList implements Iterable<Order> {
    /**
     * Keeps track of the order ids, ensuring that all ids are unique.
     */
    private int orderIdCounter;
    /**
     * The hashmap with mappings from Orders to their OrderID
     */
    private HashMap<Integer, Order> orderList;
    /**
     * The Lists which stores the Order Objects.
     */
    private final ObservableList<Order> internalList = FXCollections.observableArrayList();
    private final ObservableList<Order> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * OrderList class constructor.
     */
    public OrderList() {
        orderList = new HashMap<>();
        orderIdCounter = 1;
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Order toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOrder);
    }

    /**
     * Getter method for orderIdCounter.
     * @return The current orderId to be used.
     */
    public int getOrderIdCounter() {
        return orderIdCounter;
    }

    /**
     * Adds an order to the order list.
     * @param toAdd The order that is to be added.
     */
    public void addOrder(Order toAdd) {
        requireAllNonNull(toAdd);
        toAdd.setID(orderIdCounter);
        orderList.put(toAdd.getId(), toAdd);
        internalList.add(toAdd);
        orderIdCounter++;
    }

    /**
     * Adds an {@code Order} to the {@code OrderList} of this addressbook with a predefined ID (for storage purposes).
     * @param toAdd Order Object to be added into the Order List.
     * @param iD Order ID to be used in the adding of the Order.
     */
    public void addOrderWithID(Order toAdd, int iD) {
        requireNonNull(toAdd);
        orderList.put(iD, toAdd);
        internalList.add(toAdd);
        orderIdCounter = iD + 1; //when restoring, makes sure that the next order id created is highest.
    }

    /**
     * Replaces the order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the list.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OrderNotFoundException();
        }

        internalList.set(index, editedOrder);
        orderList.put(target.getId(), editedOrder);
    }

    /**
     * Deletes an order from the order list.
     * @param toDelete The order id of the order that is to be deleted.
     */
    public void deleteOrder(int toDelete) {
        requireNonNull(toDelete);
        if (toDelete < 1) {
            throw new NullPointerException();
        }
        Order oldOrder = orderList.get(toDelete);
        if (oldOrder == null) {
            throw new OrderNotFoundException();
        }
        orderList.remove(toDelete);
        internalList.remove(oldOrder);
    }

    /**
     * Edits an order from the order list.
     * @param orderId The order id of the order that is to be edited.
     * @param toEdit The new order to replace the old order.
     */
    public void editOrder(int orderId, Order toEdit) {
        requireAllNonNull(orderId, toEdit);
        if (orderId < 1) {
            throw new NullPointerException();
        }
        Order oldOrder = orderList.get(orderId);
        if (oldOrder == null) {
            throw new OrderNotFoundException();
        }
        Person respectiveCustomer = oldOrder.getCustomer();
        int oldOrderIndex = internalList.indexOf(oldOrder);
        toEdit.setID(oldOrder.getId());
        internalList.set(oldOrderIndex, toEdit);
        orderList.put(orderId, toEdit);
        //respectiveCustomer.editOrder(oldOrder.getId(), toEdit);
    }

    /**
     * For testing purposes.
     */
    public void clearOrders() {
        internalList.clear();
        orderList.clear();
    }

    /**
     * Converts the active order list to be displayed on the GUI.
     * @return The active order list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Order> getOrderList() {
        ObservableList<Order> ls = FXCollections.observableArrayList();
        for (int orderId : orderList.keySet()) {
            ls.add(orderList.get(orderId));
        }
        return ls;
    }

    /**
     * Gives the number of orders in the {@code OrderList}
     *
     * @return number of orders
     */
    public int size() {
        return orderList.size();
    }

    /**
     * Gets the order corresponding to the index
     *
     * @param i the order index
     * @return the order corresponding to the index
     */
    public Order getOrder(int i) {
        if (i < 1) {
            throw new NullPointerException();
        }

        Order order = orderList.get(i);

        if (order == null) {
            throw new OrderNotFoundException();
        }

        return order;
    }

    public void setOrders(List<Order> orders) {
        requireAllNonNull(orders);
        for (Order d: orders) {
            orderList.put(d.getId(), d);
        }
        internalList.setAll(orders);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * @return the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Order> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Order> iterator() {
        return internalList.iterator();
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OrderList)) {
            return false;
        }

        OrderList otherOrderList = (OrderList) other;
        //return orderList.equals(otherOrderList.orderList);
        return orderList.equals(otherOrderList.orderList)
                && internalList.equals(otherOrderList.internalList);
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean ordersAreUnique(List<Order> orders) {
        for (int i = 0; i < orders.size() - 1; i++) {
            for (int j = i + 1; j < orders.size(); j++) {
                if (orders.get(i).isSameOrder(orders.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
